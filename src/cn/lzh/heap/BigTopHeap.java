package cn.lzh.heap;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 大顶堆
 * @param <E>
 * @see #getTop() 获取最大元素
 * @see #sort() 升序
 */
public class BigTopHeap<E> extends AbstractHeap<E> {
    public BigTopHeap() {
        super();
    }

    public BigTopHeap(int initialCapacity) {
        super(initialCapacity);
    }

    public BigTopHeap(E[] initData) {
        super(initData);
    }

    public BigTopHeap(E[] initData, int initialCapacity) {
        super(initData, initialCapacity);
    }

    /**
     * 比较大小
     *
     * @param item1 E
     * @param item2 E
     * @return -1：大于，0：等于，1：小于
     */
    @Override
    protected int compare(@NotNull E item1, @NotNull E item2) {
        return ((Comparable<? super E>)item2).compareTo(item1);
    }

    @Override
    public String toString() {
        return "BigTopHeap{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                ", size=" + size +
                '}';
    }
}
