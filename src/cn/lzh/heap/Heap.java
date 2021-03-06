package cn.lzh.heap;

import com.sun.istack.internal.Nullable;

/**
 * 堆（https://time.geekbang.org/column/article/69913）
 * @param <E>
 */
public interface Heap<E> {

    /**
     * 初始有效索引：即指向数组中第一个非空数据<br/>
     * 设当前节点的下标是 i，父节点的下标是 father, 左子树节点的下标是 left，右子树节点的下标是 right：<br/>
     * 当 DEFAULT_START_INDEX = 0 时，father = (i-1)/2，left = 2∗i+1，right = left + 1；<br/>
     * 当 DEFAULT_START_INDEX = 1 时，father = i/2，left = 2∗i，right = left + 1；<br/>
     */
    int DEFAULT_START_INDEX = 0;

    /**
     * 索引的偏移量
     */
    int DEFAULT_START_INDEX_OFFSET = 1 - DEFAULT_START_INDEX;

    /**
     * 插入元素
     * @param item E
     * @return true-插入成功，false-插入失败（堆满导致）
     */
    boolean insert(E item);

    /**
     * 删除堆顶元素
     * @see #getTop()
     * @return 堆顶元素
     */
    @Nullable
    E deleteTop();

    /**
     * 获取堆顶元素
     * @see #deleteTop()
     */
    @Nullable
    E getTop();

    /**
     * 导出堆内数据（可能含有空元素）<br/>
     * @see #DEFAULT_START_INDEX
     * @return 在此之前调用{@link #sort()}，则返回数据有序，否则可能无序
     */
    Object[] toArray();

    /**
     * 导出堆内数据<br/>
     * @return 在此之前调用{@link #sort()}，则返回数据有序，否则可能无序
     */
    <T> T[] toArray(T[] a);

    /**
     * 堆排序：<br/>
     * 1、时间复杂度是 O(nlogn)；<br/>
     * 2、原地排序；<br/>
     * 3、不稳定排序（每次循环都会首尾互换，相同元素位置会发生变化）；<br/>
     * @return 排序后的数据（大顶堆-升序，小顶堆-降序）
     */
    Object[] sort();

}
