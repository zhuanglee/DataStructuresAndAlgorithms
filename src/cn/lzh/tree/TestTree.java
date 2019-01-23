package cn.lzh.tree;

import org.junit.Test;

/**
 * Created by lzh on 2018/12/20.<br/>
 * 二叉树测试
 */
public class TestTree {

    private static final BinaryTree<Character> LETTER_TREE = new BinaryTree<>(new BinaryNode<>('A',
            new BinaryNode<>('B',
                    new BinaryNode<>('D',
                            new BinaryNode<>('H', new BinaryNode<>('P'), new BinaryNode<>('Q')),
                            new BinaryNode<>('I', new BinaryNode<>('R'), new BinaryNode<>('S'))),
                    new BinaryNode<>('E',
                            new BinaryNode<>('J', new BinaryNode<>('T'), new BinaryNode<>('U')),
                            new BinaryNode<>('K', new BinaryNode<>('V'), new BinaryNode<>('W')))),
            new BinaryNode<>('C',
                    new BinaryNode<>('F',
                            new BinaryNode<>('L', new BinaryNode<>('X'), new BinaryNode<>('Y')),
                            new BinaryNode<>('M', new BinaryNode<>('Z'))),
                    new BinaryNode<>('G',
                            new BinaryNode<>('N'), new BinaryNode<>('O')))));

    @Test
    public void testPrintTree() {
        System.out.println(LETTER_TREE);
        BinaryNode<Character> node = LETTER_TREE.root;
        System.out.println("\n层遍历：");
        BinaryTree.levelOrder(node);
        System.out.println("\n前序遍历：");
        BinaryTree.preOrder(node);
        System.out.println("\n中序遍历：");
        BinaryTree.inOrder(node);
        System.out.println("\n后序遍历：");
        BinaryTree.postOrder(node);
    }

    @Test
    public void testBinarySearch() {
        BinarySearchTree tree = new BinarySearchTree(new BinaryNode<>(33,
                new BinaryNode<>(17,
                        new BinaryNode<>(13, null, new BinaryNode<>(16)),
                        new BinaryNode<>(18, null,
                                new BinaryNode<>(25, new BinaryNode<>(19), new BinaryNode<>(27)))),
                new BinaryNode<>(50,
                        new BinaryNode<>(34),
                        new BinaryNode<>(58, new BinaryNode<>(51), new BinaryNode<>(66)))));

        System.out.println("\n初始化：");
        BinaryTree.inOrder(tree.root);

        tree.insert(5);
        System.out.println("\n插入5之后：");
        BinaryTree.inOrder(tree.root);

        tree.delete(17);
        System.out.println("\n删除17之后：");
        BinaryTree.inOrder(tree.root);

        tree.delete(33);
        System.out.println("\n删除33之后：");
        BinaryTree.inOrder(tree.root);

        tree.delete(16);
        System.out.println("\n删除16之后：");
        BinaryTree.inOrder(tree.root);

        int value = 25;
        System.out.println("\n查找" + value + "：");
        BinaryNode<Integer> node = tree.find(value);
        System.out.println(node + ",height=" + BinaryTree.height(node));

    }

}
