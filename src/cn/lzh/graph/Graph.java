package cn.lzh.graph;

import com.sun.istack.internal.Nullable;

/**
 * 图<br/>
 * 参考: https://time.geekbang.org/column/article/70891<br/>
 */
public interface Graph {
    int DEFAULT_PRE_NODE = -1;

    /**
     * 添加一条边（s-t）
     *
     * @param s 顶点1
     * @param t 顶点2
     * @throws IllegalStateException 超出容量
     */
    void addEdge(int s, int t);

    /**
     * 广度优先搜索（Breadth-First-Search）
     *
     * @param start  起始顶点
     * @param target 目标顶点
     * @return s到t的最短路径
     */
    @Nullable
    Integer[] bfs(int start, int target);

    /**
     * 深度优先搜索（Depth-First-Search）
     *
     * @param start  起始顶点
     * @param target 目标顶点
     * @return s到t的路径
     */
    @Nullable
    Integer[] dfs(int start, int target);
}
