package cn.lzh.graph;

import cn.lzh.utils.Log;
import com.sun.istack.internal.Nullable;

import java.util.*;

/**
 * 抽象图<br/>
 */
public abstract class AbstractGraph implements Graph {
    /**
     * 顶点个数
     */
    protected int size;
    /**
     * 邻接表
      */
    protected LinkedList<Integer>[] adj;

    /**
     * 构造图
     *
     * @param size 顶点的个数
     */
    protected AbstractGraph(int size) {
        this.size = size;
        this.adj = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 广度优先搜索（Breadth-First-Search）
     *
     * @param start  起始顶点
     * @param target 目标顶点
     * @return s到t的最短路径
     */
    @Override
    @Nullable
    public Integer[] bfs(int start, int target) {
        if (start == target) {
            return null;
        }
        Log.debug("\nbfs: start=%d, target=%d\n\n", start, target);
        int[] prev = new int[size];
        for (int i = 0; i < size; i++) {
            prev[i] = DEFAULT_PRE_NODE;
        }
        boolean[] visited = new boolean[size];
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean found = false;
        while (!(found || queue.isEmpty())) {
            found = checkNext(target, prev, visited, queue, queue.poll());
            Log.debug("\nqueue=%s\n", Arrays.toString(queue.toArray()));
        }
        return getPath(prev, target);
    }

    /**
     * 深度优先搜索（Depth-First-Search）
     *
     * @param start  起始顶点
     * @param target 目标顶点
     * @return s到t的路径
     */
    @Override
    @Nullable
    public Integer[] dfs(int start, int target) {
        if (start == target) {
            return null;
        }
        Log.debug("\ndfs: start=%d, target=%d\n", start, target);
        int[] prev = new int[size];
        for (int i = 0; i < size; i++) {
            prev[i] = DEFAULT_PRE_NODE;
        }
        boolean[] visited = new boolean[size];
        visited[start] = true;
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        boolean found = false;
        while (!(found || stack.isEmpty())) {
            found = checkNext(target, prev, visited, stack, stack.pop());
            Log.debug("stack=%s\n", Arrays.toString(stack.toArray()));
        }
        return getPath(prev, target);
    }

    /**
     * 遍历当前节点（v）的相邻节点
     *
     * @param target     目标顶点
     * @param prev       记录某顶点的前一个顶点
     * @param visited    记录某顶点是否已访问
     * @param collection bfs时为{@link Queue}，dfs时为{@link Stack}
     * @param v          当前节点
     * @return 是否查找到
     */
    private boolean checkNext(int target, int[] prev, boolean[] visited, Collection<Integer> collection, Integer v) {
        boolean found = false;
        Log.debug("v=%d: \n", v);
        int size = adj[v].size();
        for (int i = 0; i < size; i++) {
            Integer next = adj[v].get(i);
            Log.debug("next=%d, visited=%s, prev=%s\n",
                    next, visited[next], Arrays.toString(prev));
            if (!visited[next]) {
                prev[next] = v;
                visited[next] = true;
                if (next == target) {
                    found = true;
                    Log.debug("found %d\n", target);
                    break;
                }
                collection.add(next);
            }
        }
        return found;
    }

    /**
     * 倒序的伪路径转为正序路径
     *
     * @param prev   倒序的伪路径（下标为顶点序号，值为前序顶点的序号）
     * @param target 目标顶点
     * @return 正序路径
     */
    @Nullable
    private Integer[] getPath(int[] prev, int target) {
        Log.debug("\ngetPath: prev=%s\n", Arrays.toString(prev));
        Stack<Integer> stack = new Stack<>();
        int n = target;
        while (n != DEFAULT_PRE_NODE) {
            stack.add(n);
            n = prev[n];
        }
        int size = stack.size();
        if(size == 1){
            return null;
        }
        Integer[] path = new Integer[size];
        Collections.reverse(stack);
        return stack.toArray(path);
    }

    @Override
    public String toString() {
        return "AbstractGraph{" +
                "size=" + size +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }
}
