package cn.lzh.algorithm.greedy;


import cn.lzh.utils.Log;
import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * 贪心算法的应用：区间覆盖（任务调度、排课等问题），覆盖区间越多越好
 */
public final class IntervalTest {

    private IntervalTest(){
        throw new AssertionError("cannot instantiation");
    }

    static {
        Log.isDebug = true;
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(new Interval(6, 8), new Interval(2, 4),
                new Interval(3, 5), new Interval(5, 9), new Interval(8, 10));
        coverage(intervals, new Interval(1, 9));
        List<Interval> list = new ArrayList<>(intervals);
        list.add(new Interval(5,6));
        coverage(list, new Interval(1, 9));
    }

    /**
     * 区间覆盖
     */
    private static void coverage(List<Interval> intervals, Interval maxInterval) {
        Log.info("\nintervals=%s\nmaxInterval=%s\nresult:\n", Arrays.toString(intervals.toArray()), maxInterval.toString());
        Collections.sort(intervals);
        Interval lastInterval = null;
        for (Interval interval : intervals) {
            if (lastInterval == null) {
                if (interval.start >= maxInterval.start) {
                    System.out.println(interval);
                    lastInterval = interval;
                }
            } else if (interval.start >= lastInterval.end && interval.end <= maxInterval.end) {
                System.out.println(interval);
                lastInterval = interval;
            }
        }
    }

    /**
     * 区间
     */
    public static class Interval implements Comparable<Interval> {
        private final int start;
        private final int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(@NotNull Interval o) {
            int i = start - o.start;
            if (i == 0) {
                i = end - o.end;
            }
            return i;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

    }

}
