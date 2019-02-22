package cn.lzh.graph;

import cn.lzh.utils.Log;

import java.util.LinkedList;

/**
 * TODO 拓扑排序<br/>
 * https://time.geekbang.org/column/article/76207<br/>
 */
public class TopologySort extends DirectedGraph {

    /**
     * 构造图
     *
     * @param size 顶点的个数
     */
    public TopologySort(int size) {
        super(size);
    }

    /**
     * Kahn 算法求解拓扑排序：<br/>
     * 可以用来检测有向图是否存在环，如果最后输出的节点个数少于实际节点个数，则说明有环
     */
    public void sortByKahn() {
        sortByKahn(adj, size);
    }
    /**
     * 深度优先遍历求解拓扑排序
     */
    public void sortByDFS() {
        sortByDFS(adj, size);
    }

    /**
     * Kahn 算法求解拓扑排序：<br/>
     * 可以用来检测有向图是否存在环，如果最后输出的节点个数少于实际节点个数，则说明有环
     * @param adj 图的邻接表
     * @param v 顶点个数
     */
    public static void sortByKahn(LinkedList<Integer>[] adj, int v) {
        int[] inDegree = new int[v]; // 统计每个顶点的入度
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (inDegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queue.add(k);
            }
        }
    }

    /**
     * 深度优先遍历求解拓扑排序：<br/>
     * @param adj 图的邻接表
     * @param v 顶点个数
     */
    public static void sortByDFS(LinkedList<Integer>[] adj, int v) {
        // 先构建逆邻接表，边 s->t 表示，s 依赖于 t，t 先于 s
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];
        for (int i = 0; i < v; ++i) { // 申请空间
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; ++i) { // 通过邻接表生成逆邻接表
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inverseAdj[w].add(i); // w->i
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; ++i) { // 深度优先遍历图
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private static void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w]) continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        } // 先把 vertex 这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.print("->" + vertex);
    }

    public static void main(String[] args) {
        TopologySort graph = new TopologySort(6);
//        graph.addEdge(0,5);// 添加后，有向图中有环
        graph.addEdge(1,0);
        graph.addEdge(2,1);
        graph.addEdge(3,2);
        graph.addEdge(4,1);
        graph.addEdge(5,4);
        Log.info("sortByDFS：\n");
        graph.sortByDFS();
        Log.info("\nsortByKahn：\n");
        graph.sortByKahn();
    }
}
