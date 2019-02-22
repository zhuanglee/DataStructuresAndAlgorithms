package cn.lzh.graph;

import java.util.LinkedList;

public abstract class BaseGraph<T> {
    /**
     * 顶点个数
     */
    protected int v;
    /**
     * 邻接表
     */
    protected LinkedList<T>[] adj;

    /**
     * 构造图
     *
     * @param size 顶点的个数
     */
    protected BaseGraph(int size) {
        this.v = size;
        this.adj = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new LinkedList<>();
        }
    }

}
