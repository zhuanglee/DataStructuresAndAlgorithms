package cn.lzh.heap;

/**
 * 堆（https://time.geekbang.org/column/article/69913）
 * @param <T>
 */
public interface Heap<T> {

    /**
     * 设当前节点的下标是 i，父节点的下标是 father, 左子树节点的下标是 left，右子树节点的下标是 right：<br/>
     * 当 DEFAULT_START_INDEX = 0 时，father = (i-1)/2，left = 2∗i+1，right = left + 1；
     * 当 DEFAULT_START_INDEX = 1 时，father = i/2，left = 2∗i，right = left + 1；
     */
    int DEFAULT_START_INDEX = 0;
    int DEFAULT_START_INDEX_OFFSET = 1 - DEFAULT_START_INDEX;
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

    /**
     * 获取堆顶元素
     */
    T getTop();
}
