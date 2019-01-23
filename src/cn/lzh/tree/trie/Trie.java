package cn.lzh.tree.trie;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trie树<br/>
 * 最有优势的是查找前缀匹配的字符串，可应用于搜索提示，输入自动补全等场景<br/>
 * https://time.geekbang.org/column/article/72414
 */
public abstract class Trie<T> {

    protected Node<T> root;

    public Trie() {
        this(new Node<>());
    }

    public Trie(@NotNull Node<T> root) {
        this.root = root;
    }

    /**
     * 添加文本
     * @param text String
     */
    public abstract void add(@NotNull String text);

    /**
     * 查找前缀匹配的字符串
     * @param prefix 文本前缀
     * @return 匹配到的字符串集合
     */
    public abstract List<String> search(@NotNull String prefix);

    public static class Node<T>{
        private T data;
        private Map<T, Node<T>> children;
        private boolean isEnd;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Map<T, Node<T>> getChildren() {
            return children;
        }

        public void setChildren(Map<T, Node<T>> children) {
            this.children = children;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public void add(@NotNull Node<T> node){
            if(children == null){
                children = new HashMap<>();
            }
            children.put(node.getData(), node);
        }

        public boolean remove(@NotNull Node<T> node){
            if(children == null){
                return false;
            }
            return children.remove(node.getData()) != null;
        }

        public Node<T> get(T key) {
            return children == null ? null : children.get(key);
        }

        public boolean hasChildren() {
            return children != null && children.size() > 0;
        }
    }

}
