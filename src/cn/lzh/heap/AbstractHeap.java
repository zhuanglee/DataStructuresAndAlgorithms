package cn.lzh.heap;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 堆的抽象类：<br/>
 * 子类重写{@link #compare(E, E)}方法，可分别实现大顶堆和小顶堆
 *
 * @param <E>
 */
public abstract class AbstractHeap<E> implements Heap<E> {
    /**
     * 堆数据（有null元素）
     */
    protected Object[] data;
    /**
     * 堆容量
     */
    protected int length;
    /**
     * 当前最大索引值（即堆内元素个数）
     */
    protected int size;
    /**
     * 是否需要构建堆
     */
    protected boolean mIsNeedReBuild;

    protected AbstractHeap() {
        this(16);
    }

    /**
     * @param initialCapacity 堆的容量
     */
    protected AbstractHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity is small than zero");
        }
        this.length = initialCapacity + DEFAULT_START_INDEX;
        this.data = new Object[length];
    }

    /**
     * 构建与初始数据相同容量的堆
     *
     * @param initData 初始数据（会自动根据该数组构建堆）
     */
    protected AbstractHeap(@NotNull E[] initData) {
        this(initData, initData.length);
    }

    /**
     * 构建指定容量的堆，并初始化堆数据
     *
     * @param initData        初始数据（会自动根据该数组构建堆）
     * @param initialCapacity 堆的容量
     */
    protected AbstractHeap(@NotNull E[] initData, int initialCapacity) {
        this(initialCapacity);
        this.size = initData.length;
        if (this.size > this.length) {
            throw new IllegalArgumentException("初始堆容量小于初始数据的长度");
        }
        System.arraycopy(initData, 0, this.data, DEFAULT_START_INDEX, this.size);
        this.mIsNeedReBuild = true;
        build();
    }

    @Override
    public boolean insert(@NotNull E item) {
        if (size >= length) {
            // 堆满了
            return false;
        }
        build();
        int i = size;
        size++;
        if (i == DEFAULT_START_INDEX) {
            data[DEFAULT_START_INDEX] = item;
        } else {
            siftUp(i, item);
        }
        return true;
    }

    @Override
    public E deleteTop() {
        if (size <= 0) {
            return null;
        }
        build();
        --size;
        Object topItem = data[DEFAULT_START_INDEX];
        data[DEFAULT_START_INDEX] = data[size];// 把堆底元素放到堆顶
        data[size] = null;// 将堆底元素置空
        siftDown(data, size - DEFAULT_START_INDEX_OFFSET, DEFAULT_START_INDEX);
        return (E) topItem;
    }

    @Override
    public E getTop() {
        return (E) data[DEFAULT_START_INDEX];
    }

    @Override
    public Object[] toArray() {
        if (size <= 0) {
            return null;
        }
        return Arrays.copyOf(data, this.size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        final int size = this.size;
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public Object[] sort() {
        if (size <= 0) {
            return null;
        } else if (size > 1) {
            build();
            sort(data, size);
            mIsNeedReBuild = true;
        }
        return toArray();
    }

    /**
     * 排序：时间复杂度是 O(nlogn)
     *
     * @param data 堆数据
     * @param size 当前数据个数
     */
    private void sort(Object[] data, int size) {
        size -= DEFAULT_START_INDEX_OFFSET;
        while (size > DEFAULT_START_INDEX) {
            swap(data, size, DEFAULT_START_INDEX);
            size--;
            siftDown(data, size, DEFAULT_START_INDEX);
        }
    }

    /**
     * 比较大小
     *
     * @param item1 C
     * @param item2 C
     * @return 1, 0，-1
     */
    protected abstract int compare(@NotNull E item1, @NotNull E item2);

    /**
     * 自下而上堆化：<br/>
     * 优化参考{@link java.util.PriorityQueue#offer(Object)}中的siftUp方法
     *
     * @param i    从第几个元素开始向上构建堆
     * @param item 新增数据
     */
    private void siftUp(int i, E item) {
        int parent;
        while (i > DEFAULT_START_INDEX) {
            parent = (i - DEFAULT_START_INDEX_OFFSET) >>> 1;
            Object e = data[parent];
            if (compare(item, (E) e) >= 0)
                break;
            data[i] = e;
            i = parent;
        }
        data[i] = item;
    }

    /**
     * 构建堆
     */
    private void build() {
        mIsNeedReBuild = mIsNeedReBuild && size > 1;
        if (mIsNeedReBuild) {
            int maxIndex = size - DEFAULT_START_INDEX_OFFSET;
            int lastParent = maxIndex >>> 1;
            for (int i = lastParent; i >= DEFAULT_START_INDEX; i--) {
                siftDown(data, maxIndex, i);
            }
            mIsNeedReBuild = false;
        }
    }

    /**
     * 自上而下堆化
     *
     * @param data 堆数据
     * @param maxIndex 最大有效索引
     * @param i    从第几个元素开始向下构建堆
     */
    private void siftDown(Object[] data, int maxIndex, int i) {
        if (maxIndex <= 0) {
            // 最多只有一个数据，无需调整
            return;
        }
        int maxPos = i;
        int left, right;
        while (true) {
            left = 2 * i + DEFAULT_START_INDEX_OFFSET;
            right = left + 1;
            if (left <= maxIndex && compare((E) data[i], (E) data[left]) == 1) maxPos = left;
            if (right <= maxIndex && compare((E) data[maxPos], (E) data[right]) == 1) maxPos = right;
            if (maxPos == i) break;
            swap(data, i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 交换元素
     *
     * @param data 数据
     * @param i    索引1
     * @param j    索引2
     */
    private static void swap(Object[] data, int i, int j) {
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
