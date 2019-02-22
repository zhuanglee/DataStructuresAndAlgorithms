package cn.lzh.graph;

import java.util.Arrays;

/**
 * 有向图
 */
public class DirectedGraph extends AbstractGraph {

    /**
     * 构造图
     *
     * @param size 顶点的个数
     */
    public DirectedGraph(int size) {
        super(size);
    }

    /**
     * 添加一条边（s --> t）
     *
     * @param s 顶点1
     * @param t 顶点2
     * @throws IllegalStateException 超出容量
     */
    @Override
    public void addEdge(int s, int t) {
        if (s >= size || t >= size) {
            throw new IllegalStateException("s or t out of size");
        }
        adj[s].add(t);
    }

    @Override
    public String toString() {
        return "DirectedGraph{" +
                "size=" + size +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }
}
