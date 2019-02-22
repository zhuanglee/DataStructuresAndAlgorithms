package cn.lzh.algorithm;

import cn.lzh.graph.BaseGraph;

import java.util.*;

/**
 * 最短路径<br/>
 */
public class ShortestPath extends BaseGraph<ShortestPath.Edge> {

    /**
     * 构造图
     *
     * @param size 顶点的个数
     */
    protected ShortestPath(int size) {
        super(size);
    }

    /**
     * 添加一条边
     *
     * @param s 起点
     * @param t 终点
     * @param w 权重
     */
    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    /**
     * Dijkstra算法求解单源最短路径（时间复杂度：O(E*logV)）<br/>
     * https://time.geekbang.org/column/article/76468
     *
     * @param s 起点
     * @param t 终点
     */
    public Integer[] dijkstra(int s, int t) {
        int[] predecessor = new int[this.v]; // 用来还原最短路径
        Vertex[] vertexes = new Vertex[this.v];// 用来记录起点到下标对应的顶点的距离
        MyPriorityQueue<Vertex> queue = new MyPriorityQueue<>(this.v,
                Comparator.comparingInt(o -> o.dist));// 小顶堆
        boolean[] inQueue = new boolean[this.v]; // 标记是否进入过队列
        for (int i = 0; i < this.v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        vertexes[s].dist = 0;
        queue.add(vertexes[s]);
        inQueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll(); // 取堆顶元素并删除
            if (minVertex.id == t) break; // 最短路径产生了
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                Edge e = adj[minVertex.id].get(i); // 取出一条 minVertex 相连的边
                Vertex nextVertex = vertexes[e.tid]; // minVertex-->nextVertex
                if (minVertex.dist + e.w < nextVertex.dist) { // 更新 next 的 dist
                    nextVertex.dist = minVertex.dist + e.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inQueue[nextVertex.id]) {
                        queue.update(nextVertex); // 更新队列中的 dist 值
                    } else {
                        queue.add(nextVertex);
                        inQueue[nextVertex.id] = true;
                    }
                }
            }
        }
        return getPath(s, t, predecessor);
    }

    /**
     * 获取最短路径（从终点向起点倒推）
     *
     * @param s           起点
     * @param t           终点
     * @param predecessor 存储最短路径各节点的前序节点
     */
    private Integer[] getPath(int s, int t, int[] predecessor) {
        Integer[] path = new Integer[v];
        path[v - 1] = t;
        for (int i = v - 2; i >= 0; i--) {
            path[i] = t = predecessor[t];
            if (s == t) {
                break;// 到达起点，结束
            }
        }
        if(s != t){
            return null;// 路径不存在
        }
        return path;
    }

    /**
     * 带有权重的有向边
     */
    static class Edge {
        int sid; // 边的起始顶点编号
        int tid; // 边的终止顶点编号
        int w; // 权重

        Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    /**
     * 用于实现 dijkstra 算法
     */
    static class Vertex {
        private int id; // 顶点编号 ID
        private int dist; // 从起始顶点到这个顶点的距离

        Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    /**
     * TODO 扩展优先级队列
     *
     * @param <E>
     */
    static class MyPriorityQueue<E> extends PriorityQueue<E> {
        public MyPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
            super(initialCapacity, comparator);
        }

        public void update(E e) {
            remove(e);
            add(e);
        }
    }
}
