package cn.lzh.heap;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 小顶堆
 * @param <T>
 * @see #getTop() 获取最小元素
 * @see #sort() 降序
 */
public class SmallTopHeap<T extends Comparable<T>> extends AbstractHeap<T> {
    public SmallTopHeap() {
    }

    public SmallTopHeap(int initialCapacity) {
        super(initialCapacity);
    }

    public SmallTopHeap(T[] initData) {
        super(initData);
    }

    public SmallTopHeap(T[] initData, int initialCapacity) {
        super(initData, initialCapacity);
    }

    /**
     * 比较大小
     *
     * @param item1 C
     * @param item2 C
     * @return 1：大于，0：等于，-1：小于
     */
    protected <C extends Comparable<C>> int compare(@NotNull C item1, @NotNull C item2) {
        return item1.compareTo(item2);
    }

    @Override
    public String toString() {
        return "SmallTopHeap{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                ", currentMaxIndex=" + currentMaxIndex +
                '}';
    }
}
