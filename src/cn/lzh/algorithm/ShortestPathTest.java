package cn.lzh.algorithm;

import cn.lzh.utils.CommonUtils;
import cn.lzh.utils.Log;

public class ShortestPathTest {
    public static void main(String[] args) {
        ShortestPath shortestPath = new ShortestPath(6);
        shortestPath.addEdge(0, 1, 10);
        shortestPath.addEdge(0, 4, 15);
        shortestPath.addEdge(1, 2, 15);
        shortestPath.addEdge(1, 3, 2);
        shortestPath.addEdge(2, 5, 5);
        shortestPath.addEdge(3, 2, 1);
        shortestPath.addEdge(3, 5, 12);
        shortestPath.addEdge(4, 5, 10);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == j) {
                    continue;
                }
                Log.info("%d->%d的最短路径：%s\n", i, j, CommonUtils.toString(shortestPath.dijkstra(i, j), true));
            }
        }
    }
}
