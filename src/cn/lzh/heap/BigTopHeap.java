package cn.lzh.heap;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 大顶堆
 * @param <T>
 * @see #getTop() 获取最大元素
 * @see #sort() 升序
 */
public class BigTopHeap<T extends Comparable<T>> extends AbstractHeap<T> {
    public BigTopHeap() {
    }

    public BigTopHeap(int initialCapacity) {
        super(initialCapacity);
    }

    public BigTopHeap(T[] initData) {
        super(initData);
    }

    public BigTopHeap(T[] initData, int initialCapacity) {
        super(initData, initialCapacity);
    }

    /**
     * 比较大小
     *
     * @param item1 C
     * @param item2 C
     * @return -1：大于，0：等于，1：小于
     */
    @Override
    protected <C extends Comparable<C>> int compare(@NotNull C item1, @NotNull C item2) {
        return item2.compareTo(item1);
    }

    @Override
    public String toString() {
        return "BigTopHeap{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                ", currentMaxIndex=" + currentMaxIndex +
                '}';
    }
}
