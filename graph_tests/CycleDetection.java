package graph_tests;

import graph.Edge;
import graph.Graph;

// import java.util.HashSet;
class CycleDetection {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        // Building an undirected graph
        /*
         * 1 ----- 2
         * / |
         * 0 |
         * \ |
         * 4----5
         */
        graph.addEdge(new Edge<Integer>(0, 1));
        graph.addEdge(new Edge<Integer>(1, 0));
        graph.addEdge(new Edge<Integer>(0, 4));
        graph.addEdge(new Edge<Integer>(4, 0));
        graph.addEdge(new Edge<Integer>(1, 4));
        graph.addEdge(new Edge<Integer>(4, 1));
        graph.addEdge(new Edge<Integer>(4, 5));
        graph.addEdge(new Edge<Integer>(5, 4));
        graph.addEdge(new Edge<Integer>(1, 2));
        graph.addEdge(new Edge<Integer>(2, 1));
        // graph.addEdge(new Edge<Integer>(5, 0));
        // graph.addEdge(new Edge<Integer>(5, 0));
        // graph.bfs(0); // to test that vertices are added successfully
        // we know that there is a cycle so hasCycle() method should return true
        if (graph.hasCycleUnd()) {
            System.out.println("Yes, there is a cycle in graph 1 !");
        } else {
            System.out.println("No, there is no cycle in graph 1 !");
        }
        Graph<Integer> graph2 = new Graph<>();
        // Building a directed graph
        /*
         * (0, 1), (0, 4), (1, 4), (4, 5), (2, 1), it has no cycle, you can draw these edges on paper and check
         * 1 <----- 2
         * / |
         * 0 |
         * \ |
         * 4 ---- >5
         */
        graph2.addEdge(new Edge<Integer>(0, 1));
        graph2.addEdge(new Edge<Integer>(0, 4));
        graph2.addEdge(new Edge<Integer>(1, 4));
        graph2.addEdge(new Edge<Integer>(4, 5));
        graph2.addEdge(new Edge<Integer>(2, 1));
        // graph2.addEdge(new Edge<Integer>(5, 2)); // if we add this edge, there will be cycle

        // you can test the hasCycleUnd() to check that it is not valid for directed
        // graphs
        if (graph2.hasCycleDir()) {
            System.out.println("There is a cycle in Graph 2 !");
        } else {
            System.out.println("There is no cycle in Graph 2 !");
        }
    }
}