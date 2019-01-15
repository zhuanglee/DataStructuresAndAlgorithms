package cn.lzh.heap;

import cn.lzh.utils.CommonUtils;

import java.util.Arrays;

public class TestHeapUtils {

    public static void main(String[] args) {
        Integer[] data = new Integer[]{7, 5, 19, 8, 4, 1, 20, 13, 16};
        test(data);
        System.out.println("\n**********************************************\n");
        data = new Integer[]{7, 5, 19, 8, 4, 1, 20, 13, 16, 3, 21, 2};
        test(data);
    }

    private static void test(Integer[] data) {
        Integer[] sortedData = HeapUtils.sort(data, true);
        System.out.println("ASC:" + Arrays.toString(sortedData));
        sortedData = HeapUtils.sort(data, false);
        System.out.println("DESC:" + Arrays.toString(sortedData));
        testTopK(data, 3);
        testPick(data, 6);
    }

    private static void testPick(Integer[] data, int n) {
        Integer result = HeapUtils.pick(data, n, true);
        System.out.printf("\nasc pick %d:%s\n", n, result);
        result = HeapUtils.pick(data, n, false);
        System.out.printf("desc pick %d:%s\n", n, result);
        result = HeapUtils.center(data, true);
        System.out.printf("\nasc center:%s\n", result);
        result = HeapUtils.center(data,false);
        System.out.printf("desc center:%s\n", result);
    }

    private static void testTopK(Integer[] data, int k) {
        Integer[] topAsc = HeapUtils.top(data, k, true);
        System.out.printf("\nasc top%d:%s\n", k, CommonUtils.toString(topAsc, false));
        Integer[] topDesc = HeapUtils.top(data, k, false);
        System.out.printf("desc top%d:%s\n", k, CommonUtils.toString(topDesc, false));
    }

}
