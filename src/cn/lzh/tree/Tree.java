package cn.lzh.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree<T> {
    Node<T> root;

    public Tree() {
    }

    public Tree(Node<T> root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "height=" + height(root) +
                ", root=" + root +
                '}';
    }

    public static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 层遍历
     *
     * @param node Node
     */
    public static void levelOrder(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node == null) {
                break;
            }
            System.out.print(node.data);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 前序遍历
     *
     * @param node Node
     */
    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     *
     * @param node Node
     */
    public static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     *
     * @param node Node
     */
    public static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

}
