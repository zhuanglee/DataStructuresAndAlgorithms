package cn.lzh.tree;

public class BinarySearchTree extends BinaryTree<Integer> {

        public BinarySearchTree() {
        }

        BinarySearchTree(BinaryNode<Integer> root) {
            super(root);
        }

        public BinaryNode<Integer> find(int value) {
            BinaryNode<Integer> node = root;
            while (node != null) {
                if (node.data == value) return node;
                else if (node.data > value) node = node.left;
                else node = node.right;
            }
            return null;
        }

        /**
         * 插入新值
         *
         * @param value 新值
         * @return true-成功，false-已存在
         */
        public boolean insert(int value) {
            if (root == null) {
                root = new BinaryNode<>(value);
                return true;
            }
            BinaryNode<Integer> node = root;
            while (true) {
                if (node.data > value) {
                    if (node.left == null) {
                        node.left = new BinaryNode<>(value);
                        break;
                    }
                    node = node.left;
                } else if (node.data < value) {
                    if (node.right == null) {
                        node.right = new BinaryNode<>(value);
                        break;
                    }
                    node = node.right;
                } else {
                    return false;
                }
            }
            return true;
        }

        public boolean delete(int value) {
            BinaryNode<Integer> node = root, parent = null;
            while (node != null && node.data != value) {
                parent = node;
                if (node.data > value) node = node.left;
                else node = node.right;
            }
            if (node == null) {
                return false;
            }
            BinaryNode<Integer> child;
            if (node.left != null && node.right != null) {
                // 查找右子树中最小节点
                BinaryNode<Integer> min = node.right;
                BinaryNode<Integer> minParent = node;
                while (min.left != null) {
                    minParent = min;
                    min = min.left;
                }
                node.data = min.data;
                node = min;
                parent = minParent;
            }
            if (node.left != null) {
                child = node.left;
            } else if (node.right != null) {
                child = node.right;
            } else {
                child = null;
            }
            if (parent == null) {
                root = child;
            } else if (parent.left == node) {
                parent.left = child;
            } else if (parent.right == node) {
                parent.right = child;
            }
            return false;
        }
    }
