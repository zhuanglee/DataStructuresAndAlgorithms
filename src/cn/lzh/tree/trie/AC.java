package cn.lzh.tree.trie;

import cn.lzh.utils.Log;
import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * AC 自动机算法，全称是 Aho-Corasick 算法，属于多模式串匹配算法，<br/>
 * AC 自动机是基于 {@link Trie} 树的一种改进算法，它跟Trie 树的关系，就像单模式串中，KMP 算法与 BF 算法的关系一样。<br/>
 * https://time.geekbang.org/column/article/72810<br/>
 *
 * @see #build()
 */
public class AC {

    private List<String> list;
    private ACNode root;

    public AC() {
        this(null);
    }

    public AC(List<String> list) {
        this.list = list == null ? new ArrayList<>() : list;
        this.root = new ACNode();
        build();
    }


    /**
     * 添加模式串
     *
     * @param pattern 模式串
     */
    public void add(@NotNull String pattern) {
        if (this.list.contains(pattern)) {
            return;
        }
        this.list.add(pattern);
        addNode(pattern);
    }

    /**
     * 将多个模式串构建成 AC 自动机
     */
    private void build() {
        if (list.isEmpty()) {
            this.root.clear();
            return;
        }
        buildTree();
        buildFailPointer();
    }

    /**
     * 将模式串构建成 Trie 树
     */
    private void buildTree() {
        for (String s : list) {
            addNode(s);
        }
    }

    /**
     * 添加模式串
     *
     * @param pattern 模式串
     */
    private void addNode(@NotNull String pattern) {
        pattern = pattern.trim();
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("pattern is empty");
        }
        if (root == null) {
            return;
        }
        ACNode node = root;
        Map<Character, Trie.Node<Character>> children;
        ACNode newNode;
        char[] chars = pattern.toCharArray();
        for (char c : chars) {
            children = node.getChildren();
            if (children == null) {
                newNode = new ACNode(c);
                node.add(newNode);
            } else {
                newNode = (ACNode) children.get(c);
                if (newNode == null) {
                    newNode = new ACNode(c);
                    node.add(newNode);
                }
            }
            node = newNode;
        }
        node.setEnd(true);
        node.setLength(pattern.length());
    }

    /**
     * 在 Trie 树上构建失败指针
     */
    private void buildFailPointer() {
        if (root == null) {
            return;
        }
        ACNode node = root;
        Queue<ACNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            ACNode parent = queue.poll();
            if (!parent.hasChildren()) continue;
            Collection<Trie.Node<Character>> children = parent.getChildren().values();
            for (Trie.Node<Character> c : children) {
                ACNode child = (ACNode) c;
                if (parent == root) {
                    child.fail = root;
                } else {
                    ACNode pf = parent.fail;
                    while (pf != null) {
                        Trie.Node<Character> pfc = pf.get(child.getData());
                        if (pfc != null) {
                            child.fail = (ACNode) pfc;
                            break;
                        }
                        pf = pf.fail;
                    }
                    if (pf == null) {
                        child.fail = root;
                    }
                }
                queue.add(child);
            }
        }
    }

    /**
     * 在 AC 自动机中匹配主串
     *
     * @param text 主串
     */
    public List<MatchResult> match(@NotNull String text) {
        if (root == null) {
            return null;
        }
        List<MatchResult> list = new ArrayList<>();
        ACNode node = root;
        char[] chars = text.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            while (node != root && node.get(c) == null){
                node = node.fail;
            }
            node = (ACNode) node.get(c);
            if(node == null) node = root;
            ACNode tmp = node;
            while (tmp != root){
                if(tmp.isEnd()){
                    int length = tmp.getLength();
                    int position = i - length + 1;
                    list.add(new MatchResult(position, length));
                }
                tmp = tmp.fail;
            }
        }
        return list;
    }

    /**
     * 过滤敏感词，将其替换为星号
     * @param text 待过滤文本
     * @return 过滤敏感词之后的文本
     */
    public String filter(String text){
        return filter(text, '*');
    }

    /**
     * 过滤敏感词，将其替换为指定的字符
     * @param text 待过滤文本
     * @param replace 替换字符
     * @return 过滤敏感词之后的文本
     */
    public String filter(String text, char replace){
        List<MatchResult> results = match(text);
        if(results == null || results.isEmpty()){
            return text;
        }
        char[] chars = text.toCharArray();
        for (MatchResult result : results) {
            Log.debug(result.toString());
            for (int i = result.position; i < result.position + result.length; i++) {
                chars[i] = replace;
            }
        }
        return new String(chars);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        Character data = root.getData();
        CharTrie.dfs(root, list, data == null ? "" : String.valueOf(data));
        return Arrays.toString(list.toArray());
    }

    private static class ACNode extends Trie.Node<Character> {
        private ACNode fail;
        private int length = -1; // 当 isEndingChar=true 时，记录模式串的长度

        public ACNode() {
        }

        public ACNode(Character data) {
            super(data);
        }

        public ACNode getFail() {
            return fail;
        }

        public void setFail(ACNode fail) {
            this.fail = fail;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }

    public static class MatchResult{
        private int position;
        private int length;

        public MatchResult(int position, int length) {
            this.position = position;
            this.length = length;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return "MatchResult{" +
                    "position=" + position +
                    ", length=" + length +
                    '}';
        }
    }
}
