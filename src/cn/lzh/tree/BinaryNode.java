package cn.lzh.tree;

/**
 * 二叉树节点
 *
 * @param <T>
 */
public class BinaryNode<T> {
    T data;
    BinaryNode<T> left;
    BinaryNode<T> right;

    BinaryNode(T data) {
        this.data = data;
    }

    BinaryNode(T data, BinaryNode<T> left) {
        this.data = data;
        this.left = left;
    }

    BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                (left == null ? "" : ", left=" + left) +
                (right == null ? "" : ", right=" + right) +
                '}';
    }
}
