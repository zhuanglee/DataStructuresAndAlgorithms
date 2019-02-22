package cn.lzh.algorithm;

import cn.lzh.utils.Log;

import java.util.Arrays;

/**
 * 利用归并排序计算逆序数（分治算法）
 */
public final class ReverseNumber {

    public static void main(String[] args) {
        int[] a = {1, 5, 6, 2, 3, 4};
        Log.info("%s 的逆序数：%d，排序结果：%s",
                Arrays.toString(a), count(a), Arrays.toString(a));
    }

    /**
     * 逆序数
     */
    private static int num = 0;

    public static int count(int[] data) {
        num = 0;
        mergeSortCounting(data, 0, data.length - 1);
        return num;
    }

    private static void mergeSortCounting(int[] data, int low, int high) {
        if (low >= high) return;
        int middle = (low + high) / 2;
        mergeSortCounting(data, low, middle);
        mergeSortCounting(data, middle + 1, high);
        merge(data, low, middle, high);
    }

    private static void merge(int[] data, int low, int middle, int high) {
        int i = low, j = middle + 1, k = 0;
        int[] tmp = new int[high - low + 1];
        while (i <= middle && j <= high) {
            if (data[i] <= data[j]) {
                tmp[k++] = data[i++];
            } else {
                num += (middle - i + 1); // 统计 p-q 之间，比 a[j] 大的元素个数
                tmp[k++] = data[j++];
            }
        }
        while (i <= middle) { // 处理剩下的
            tmp[k++] = data[i++];
        }
        while (j <= high) { // 处理剩下的
            tmp[k++] = data[j++];
        }
        for (i = 0; i <= high - low; ++i) { // 从 tmp 拷贝回 a
            data[low + i] = tmp[i];
        }
    }

}
