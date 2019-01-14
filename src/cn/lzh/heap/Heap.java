package cn.lzh.heap;

/**
 * 堆（https://time.geekbang.org/column/article/69913）
 * @param <T>
 */
public interface Heap<T extends Comparable<T>> {

    /**
     * 插入元素
     * @param item T
     * @return boolean
     */
    boolean insert(T item);

    /**
     * 删除堆顶元素
     */
    T deleteTop();

    /**
     * 堆排序：<br/>
     * 1、时间复杂度是 O(nlogn)；<br/>
     * 2、原地排序；<br/>
     * 3、不稳定排序（每次循环都会首尾互换，相同元素位置会发生变化）；<br/>
     */
    void sort();

    /**
     * 获取堆内数据（含有空元素）
     */
    Object[] getData();
}
