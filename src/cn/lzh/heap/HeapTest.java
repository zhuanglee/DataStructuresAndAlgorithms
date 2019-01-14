package cn.lzh.heap;

import cn.lzh.utils.CommonUtils;

import java.util.Arrays;

public class HeapTest {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        System.out.println("\ntest1:初始化空堆");
        Heap<Integer> heap = new BigTopHeap<>(9);
        System.out.println("\ninit heap：" + heap.toString());
        sort(heap);
        // 删除+排序
        if (heap.deleteTop() != null) {
            System.out.println("\ndeleteTop：" + heap.toString());
            sort(heap);
        } else {
            System.out.println("\ndeleteTop failed");
        }
        // 插入+排序
        Integer[] data = new Integer[]{7, 5, 19, 8, 4, 1, 20, 13, 16, 2};
        for (Integer i : data) {
            if (heap.insert(i)) {
                System.out.printf("\ninsert(%d)：%s\n", i, heap.toString());
                sort(heap);
            } else {
                System.out.printf("\ninsert(%d)：failed\n", i);
            }
        }
        // 删除+排序
        if (heap.deleteTop() != null) {
            System.out.println("\ndeleteTop：" + heap.toString());
            sort(heap);
        } else {
            System.out.println("\ndeleteTop failed");
        }
        // 插入+排序
        int i = 2;
        if (heap.insert(i)) {
            System.out.printf("\ninsert(%d)：%s\n", i, heap.toString());
            sort(heap);
        }else {
            System.out.printf("\ninsert(%d)：failed\n", i);
        }
    }

    private static void test2() {
        System.out.println("\ntest2:根据数组数据创建堆");
        Integer[] data = new Integer[]{7, 5, 19, 8, 4, 1, 20, 13, 16};
        System.out.println("init data：" + Arrays.toString(data));
        // 初始化+排序
        Heap<Integer> heap = new BigTopHeap<>(data);
        System.out.println("\ninit heap：" + heap.toString());
        sort(heap);
        // 插入+排序
        int i = 21;
        if (heap.insert(i)) {
            System.out.printf("\ninsert(%d)：%s\n", i, heap.toString());
            sort(heap);
        }else {
            System.out.printf("\ninsert(%d)：failed\n", i);
        }
        // 删除+排序
        if (heap.deleteTop() != null) {
            System.out.println("\ndeleteTop：" + heap.toString());
            sort(heap);
        } else {
            System.out.println("\ndeleteTop failed");
        }
        // 插入+排序
        if (heap.insert(i)) {
            System.out.printf("\ninsert(%d)：%s\n", i, heap.toString());
            sort(heap);
        }else {
            System.out.printf("\ninsert(%d)：failed\n", i);
        }
        // 插入+排序
        i = 2;
        if (heap.insert(i)) {
            System.out.printf("\ninsert(%d)：%s\n", i, heap.toString());
            sort(heap);
        }else {
            System.out.printf("\ninsert(%d)：failed\n", i);
        }
    }

    private static void sort(Heap<Integer> heap) {
        heap.sort();
        System.out.println("heap.sort：" + CommonUtils.toString(heap.getData(), true));
    }

}
