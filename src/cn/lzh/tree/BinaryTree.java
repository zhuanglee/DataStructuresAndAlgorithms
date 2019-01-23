package cn.lzh.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree<T> {
    BinaryNode<T> root;

    public BinaryTree() {
    }

    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "height=" + height(root) +
                ", root=" + root +
                '}';
    }

    public static int height(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 层遍历
     *
     * @param node BinaryNode
     */
    public static void levelOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        Queue<BinaryNode> queue = new ArrayDeque<>();
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
     * @param node BinaryNode
     */
    public static void preOrder(BinaryNode node) {
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
     * @param node BinaryNode
     */
    public static void inOrder(BinaryNode node) {
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
     * @param node BinaryNode
     */
    public static void postOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

}
