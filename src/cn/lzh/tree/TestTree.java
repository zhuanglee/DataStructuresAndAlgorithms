package cn.lzh.tree;

import org.junit.Test;

/**
 * Created by lzh on 2018/12/20.<br/>
 * 二叉树测试
 */
public class TestTree {

    public static final String LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Tree<Character> LETTER_TREE = new Tree<>(new Node<>('A',
            new Node<>('B',
                    new Node<>('D',
                            new Node<>('H', new Node<>('P'), new Node<>('Q')),
                            new Node<>('I', new Node<>('R'), new Node<>('S'))),
                    new Node<>('E',
                            new Node<>('J', new Node<>('T'), new Node<>('U')),
                            new Node<>('K', new Node<>('V'), new Node<>('W')))),
            new Node<>('C',
                    new Node<>('F',
                            new Node<>('L', new Node<>('X'), new Node<>('Y')),
                            new Node<>('M', new Node<>('Z'))),
                    new Node<>('G',
                            new Node<>('N'), new Node<>('O')))));

    @Test
    public void testPrintTree() {
        System.out.println(LETTER_TREE);
        Node<Character> node = LETTER_TREE.root;
        System.out.println("\n层遍历：");
        Tree.levelOrder(node);
        System.out.println("\n前序遍历：");
        Tree.preOrder(node);
        System.out.println("\n中序遍历：");
        Tree.inOrder(node);
        System.out.println("\n后序遍历：");
        Tree.postOrder(node);
    }

    @Test
    public void testBinarySearch() {
        BinarySearchTree tree = new BinarySearchTree(new Node<>(33,
                new Node<>(17,
                        new Node<>(13, null, new Node<>(16)),
                        new Node<>(18, null,
                                new Node<>(25, new Node<>(19), new Node<>(27)))),
                new Node<>(50,
                        new Node<>(34),
                        new Node<>(58, new Node<>(51), new Node<>(66)))));

        System.out.println("\n初始化：");
        Tree.inOrder(tree.root);

        tree.insert(5);
        System.out.println("\n插入5之后：");
        Tree.inOrder(tree.root);

        tree.delete(17);
        System.out.println("\n删除17之后：");
        Tree.inOrder(tree.root);

        tree.delete(33);
        System.out.println("\n删除33之后：");
        Tree.inOrder(tree.root);

        tree.delete(16);
        System.out.println("\n删除16之后：");
        Tree.inOrder(tree.root);

        int value = 25;
        System.out.println("\n查找" + value + "：");
        Node<Integer> node = tree.find(value);
        System.out.println(node + ",height=" + Tree.height(node));

    }

}
