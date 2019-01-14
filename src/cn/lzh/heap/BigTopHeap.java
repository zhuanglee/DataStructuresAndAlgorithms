package cn.lzh.heap;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 大顶堆
 * @param <T>
 */
public class BigTopHeap<T extends Comparable<T>> implements Heap<T> {
    /**
     * 设当前节点的下标是 i，父节点的下标是 father, 左子树节点的下标是 left，右子树节点的下标是 right：<br/>
     * 当 DEFAULT_START_INDEX = 0 时，father = (i-1)/2，left = 2∗i+1，right = left + 1；
     * 当 DEFAULT_START_INDEX = 1 时，father = i/2，left = 2∗i，right = left + 1；
     */
    private static final int DEFAULT_START_INDEX = 0;
    private static final int DEFAULT_START_INDEX_OFFSET = 1 - DEFAULT_START_INDEX;
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
    protected int currentMaxIndex = - DEFAULT_START_INDEX_OFFSET;
    /**
     * 是否需要构建堆
     */
    protected boolean mIsNeedBuild;

    public BigTopHeap() {
        this(16);
    }

    /**
     * @param initialCapacity 堆的容量
     */
    public BigTopHeap(int initialCapacity) {
        if(initialCapacity <= 0){
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
    public BigTopHeap(@NotNull T[] initData) {
        this(initData, initData.length);
    }

    /**
     * 构建指定容量的堆，并初始化堆数据
     *
     * @param initData        初始数据（会自动根据该数组构建堆）
     * @param initialCapacity 堆的容量
     */
    public BigTopHeap(@NotNull T[] initData, int initialCapacity) {
        this(initialCapacity);
        int initDataLength = initData.length;
        if (initDataLength > this.length) {
            throw new IllegalArgumentException("初始堆容量小于初始数据的长度");
        }
        System.arraycopy(initData, 0, this.data, DEFAULT_START_INDEX, initDataLength);
        this.currentMaxIndex = initDataLength - DEFAULT_START_INDEX_OFFSET;
        mIsNeedBuild = true;
        build();
    }

    /**
     * 构建堆
     */
    private void build() {
        if (mIsNeedBuild) {
            for (int i = currentMaxIndex / 2; i >= DEFAULT_START_INDEX; i--) {
                buildFromTop(data, currentMaxIndex, i);
            }
            mIsNeedBuild = false;
        }
    }

    /**
     * 自上而下堆化
     *
     * @param data  堆数据
     * @param count 当前数据个数
     * @param i     从第几个元素开始向下构建堆
     */
    private void buildFromTop(Object[] data, int count, int i) {
        int maxPos = i;
        int left, right;
        while (true) {
            left = 2 * i + DEFAULT_START_INDEX_OFFSET;
            right = left + 1;
            try {
                if (left <= count && compare((T)data[i], (T)data[left]) == -1) maxPos = left;
                if (right <= count && compare((T)data[maxPos], (T)data[right]) == -1) maxPos = right;
            } catch (Exception e) {
                System.out.printf("currentMaxIndex=%d,i=%d,left=%d,right=%d,maxPos=%d\n", count, i, left, right, maxPos);
            }
            if (maxPos == i) break;
            swap(data, i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 自下而上堆化
     *
     * @param data 堆数据
     * @param i    从第几个元素开始向上构建堆
     */
    private void buildFromBottom(Object[] data, int i) {
        int father = (i - DEFAULT_START_INDEX_OFFSET) / 2;
        while (father >= DEFAULT_START_INDEX && compare((T)data[i], (T)data[father]) == 1) {
            swap(data, i, father);
            i = father;
            father = (i - DEFAULT_START_INDEX_OFFSET) / 2;
        }
    }

    @Override
    public boolean insert(@NotNull T item) {
        if (currentMaxIndex >= length - 1) {
            // 堆满了
            return false;
        }
        build();
        currentMaxIndex++;
        data[currentMaxIndex] = item;
        // 自下往上堆化
        buildFromBottom(data, currentMaxIndex);
        return true;
    }

    @Override
    public T deleteTop() {
        if (currentMaxIndex <= 0) {
            return null;
        }
        build();
        Object topItem = data[DEFAULT_START_INDEX];
        data[DEFAULT_START_INDEX] = data[currentMaxIndex];
        data[currentMaxIndex] = null;
        --currentMaxIndex;
        buildFromTop(data, currentMaxIndex, DEFAULT_START_INDEX);
        return (T) topItem;
    }

    @Override
    public void sort() {
        build();
        sort(data, currentMaxIndex);
        mIsNeedBuild = true;
    }

    /**
     * 排序：时间复杂度是 O(nlogn)
     *
     * @param data  堆数据
     * @param count 当前数据个数
     */
    private void sort(Object[] data, int count) {
        while (count > DEFAULT_START_INDEX) {
            swap(data, count, DEFAULT_START_INDEX);
            count--;
            buildFromTop(data, count, DEFAULT_START_INDEX);
        }
    }

    @Override
    public Object[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BigTopHeap{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                ", currentMaxIndex=" + currentMaxIndex +
                '}';
    }

    /**
     * 比较大小
     *
     * @param item1 T
     * @param item2 T
     * @return 1-大于，0-等于，-1 小于
     */
    private static <T extends Comparable<T>> int compare(@NotNull T item1, @NotNull T item2) {
        return item1.compareTo(item2);
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
