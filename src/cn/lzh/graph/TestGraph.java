package cn.lzh.graph;

import cn.lzh.utils.Log;

import java.util.Arrays;

public class TestGraph {

    static {
        Log.isDebug = false;
    }

    public static void main(String[] args) {
        Log.info("无向图：");
        Graph graph = new UndirectedGraph(8);
        addUndirectedGraphEdge(graph);
        printPath(graph);

        Log.info("有向图：");
        graph = new DirectedGraph(8);
        addDirectedGraphEdge(graph);
        printPath(graph);
    }

    private static void addUndirectedGraphEdge(Graph graph) {
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
    }

    private static void addDirectedGraphEdge(Graph graph) {
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 1);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        graph.addEdge(5, 4);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.addEdge(7, 5);
        graph.addEdge(7, 6);
    }

    private static void printPath(Graph graph) {
        Log.info(graph.toString());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                bfs(graph, i, j);
                dfs(graph, i, j);
            }
        }
    }

    private static void bfs(Graph graph, int start, int target) {
        Log.info("bfs (%d-->%d) path : %s\n", start, target, Arrays.toString(graph.bfs(start, target)));
    }

    private static void dfs(Graph graph, int start, int target) {
        Log.info("dfs (%d-->%d) path : %s\n", start, target, Arrays.toString(graph.dfs(start, target)));
    }

}
