package cn.lzh.tree;

public class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node<T> left) {
            this.data = data;
            this.left = left;
        }

        Node(T data, Node<T> left, Node<T> right) {
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
