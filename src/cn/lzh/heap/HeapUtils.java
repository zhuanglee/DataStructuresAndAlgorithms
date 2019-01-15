package cn.lzh.heap;

/**
 * 堆应用：
 * https://time.geekbang.org/column/article/70187
 */
public class HeapUtils {

    /**
     * 堆排序，利用 data 存储排序结果
     *
     * @param data  源数据
     * @param isAsc true-升序，false-降序
     * @return 堆排序结果（即data）
     */
    public static Integer[] sort(Integer[] data, boolean isAsc) {
        Object[] sortedData = sortKeepData(data, isAsc);
        System.arraycopy(sortedData, Heap.DEFAULT_START_INDEX, data, 0, data.length);
        return data;
    }

    /**
     * 堆排序，保持源数据（即传入的data）不变
     *
     * @param data  源数据
     * @param isAsc true-升序，false-降序
     * @return 堆排序结果
     */
    public static Object[] sortKeepData(Integer[] data, boolean isAsc) {
        Heap<Integer> heap = isAsc ? new BigTopHeap<>(data) : new SmallTopHeap<>(data);
        heap.sort();
        return heap.toArray();
    }

    /**
     * 获取前k个数据
     *
     * @param data  数据源
     * @param k     前k个数据
     * @param isAsc 是否为升序
     */
    public static Integer[] top(Integer[] data, int k, boolean isAsc) {
        Integer[] topK = new Integer[k];
        System.arraycopy(data, 0, topK, 0, k);
        Heap<Integer> heap = isAsc ? new BigTopHeap<>(topK) : new SmallTopHeap<>(topK);
        int n;
        for (int i = k; i < data.length; i++) {
            n = data[i];
            Integer top = heap.getTop();
            if (top == null) {
                throw new IllegalStateException("必须先初始化堆容量为" + k);
            }
            if (isAsc) {
                if (n < top) {
                    heap.deleteTop();
                    heap.insert(n);
                }
            } else {
                if (n > top) {
                    heap.deleteTop();
                    heap.insert(n);
                }
            }
        }
        System.arraycopy(heap.toArray(), Heap.DEFAULT_START_INDEX, topK, 0, k);
        return topK;
    }

    /**
     * 获取中位数
     *
     * @param data  数据源
     * @param isAsc 是否升序
     */
    public static Integer center(Integer[] data, boolean isAsc) {
        int length = data.length;
        return pick(data, length / 2 + length%2, isAsc);
    }

    /**
     * 获取 data 中排序后的第 n 个数据
     *
     * @param data  数据源
     * @param n     第n个
     * @param isAsc 是否升序
     */
    public static Integer pick(Integer[] data, int n, boolean isAsc) {
        Object[] sortedData = sortKeepData(data, isAsc);
        Integer[] preData = new Integer[n];// 用于存储前n个数据
        Integer[] remainData = new Integer[data.length - n];// 用于存储剩余数据
        System.arraycopy(sortedData, Heap.DEFAULT_START_INDEX, preData, 0, n);
        System.arraycopy(sortedData, n + Heap.DEFAULT_START_INDEX, remainData, 0, remainData.length);
        Heap<Integer> bigTopHeap = new BigTopHeap<>(isAsc ? preData : remainData);
        Heap<Integer> smallTopHeap = new SmallTopHeap<>(isAsc ? remainData : preData);
        return isAsc ? bigTopHeap.getTop() : smallTopHeap.getTop();
    }

}
