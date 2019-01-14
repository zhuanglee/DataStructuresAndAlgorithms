package cn.lzh.tree;

/**
 * 红黑树<br/>
 * https://time.geekbang.org/column/article/68976
 */
public class RedBlackTree extends BinarySearchTree {

    @Override
    public boolean insert(int value) {
        boolean insert = super.insert(value);
        if (insert) {

        }
        return insert;
    }

    @Override
    public boolean delete(int value) {
        return super.delete(value);
    }

    @Override
    public Node<Integer> find(int value) {
        return super.find(value);
    }
}