package cn.lzh.tree.trie;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Trie树<br/>
 * 最有优势的是查找前缀匹配的字符串，可应用于搜索提示，输入自动补全等场景<br/>
 * https://time.geekbang.org/column/article/72414
 */
public class CharTrie extends Trie<Character> {

    public CharTrie() {
    }

    public CharTrie(Node<Character> root) {
        super(root);
    }

    public CharTrie(List<String> list) {
        for (String s : list) {
            add(s);
        }
    }

    @Override
    public void add(@NotNull String text) {
        text = text.trim();
        if (text.length() == 0) {
            throw new IllegalArgumentException("text is empty");
        }
        Node<Character> node = root;
        Map<Character, Node<Character>> children;
        Node<Character> newNode;
        char[] chars = text.toCharArray();
        for (char c : chars) {
            children = node.getChildren();
            if (children == null) {
                newNode = new Node<>(c);
                node.add(newNode);
            } else {
                newNode = children.get(c);
                if (newNode == null) {
                    newNode = new Node<>(c);
                    node.add(newNode);
                }
            }
            node = newNode;
        }
        node.setEnd(true);
    }

    @Override
    public List<String> search(@NotNull String prefix) {
        Node<Character> node = root;
        prefix = prefix.trim();
        if (prefix.length() == 0 || node == null || !node.hasChildren()) {
            return null;
        }
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            node = node.get(c);
            if (node == null) {
                return null;
            }
        }
        List<String> list = new ArrayList<>();
        dfs(node, list, prefix);
        return list;
    }

    /**
     * 深度遍历
     *
     * @param node   继续遍历该节点的子节点
     * @param list   结果集
     * @param prefix 文本前缀
     */
    public static void dfs(@NotNull Node<Character> node, @NotNull List<String> list, @NotNull String prefix) {
        if (node.hasChildren()) {
            String newPrefix;
            Collection<Node<Character>> nodes = node.getChildren().values();
            for (Node<Character> n : nodes) {
                newPrefix = prefix + n.getData();
                if (n.isEnd()) {
                    list.add(newPrefix);
                }
                if (n.hasChildren()) {
                    dfs(n, list, newPrefix);
                }
            }
        } else {
            list.add(prefix);
        }
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        Character data = root.getData();
        dfs(root, list, data == null ? "" : String.valueOf(data));
        return Arrays.toString(list.toArray());
    }
}
