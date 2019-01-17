package cn.lzh.heap;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 小顶堆
 * @param <E>
 * @see #getTop() 获取最小元素
 * @see #sort() 降序
 */
public class SmallTopHeap<E> extends AbstractHeap<E> {
    public SmallTopHeap() {
        super();
    }

    public SmallTopHeap(int initialCapacity) {
        super(initialCapacity);
    }

    public SmallTopHeap(E[] initData) {
        super(initData);
    }

    public SmallTopHeap(E[] initData, int initialCapacity) {
        super(initData, initialCapacity);
    }

    /**
     * 比较大小
     *
     * @param item1 E
     * @param item2 E
     * @return 1：大于，0：等于，-1：小于
     */
    @Override
    protected int compare(@NotNull E item1, @NotNull E item2) {
        return ((Comparable<? super E>)item1).compareTo(item2);
    }


    @Override
    public String toString() {
        return "SmallTopHeap{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                ", size=" + size +
                '}';
    }
}
