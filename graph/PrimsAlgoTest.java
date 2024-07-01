/*
 * This class is to test prims algorithm that is implemented in graph.mst()
 * This Algorithm is used to build a minimum spanning tree (MST) out of weighted graph
 * Refer Graph.java => mst(T start) to see the implementation
 * Refer MSTGraph.png for visual diagram of graph and MST formed by it using Prim's Algorithm
 */ 

package graph;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimsAlgoTest {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        System.out.println("\n--------------Add Edges to the graph------------------\n");
        Scanner sc = new Scanner(System.in);
        // String next = "y";
        // while (next.equals("y")) {
        //     System.out.print("Source: ");
        //     int src = sc.nextInt();
        //     System.out.print("Destination: ");
        //     int dest = sc.nextInt();
        //     System.out.print("Weight: ");
        //     int wt = sc.nextInt();

        //     graph.addEdge(new Edge<Integer>(src, dest, wt));
        //     System.out.print("Add More? (y/n): ");
        //     next = sc.next();
        //     System.out.println();
        // }

        // System.out.print("Enter Source to get paths: ");
        // int src = sc.nextInt();
        // System.out.println();

        // Add all edges to the graph, from MSTGraph.png
        graph.addEdge(new Edge<String>("A", "B", 4));
        graph.addEdge(new Edge<String>("A", "D", 1));
        graph.addEdge(new Edge<String>("B", "C", 4));
        graph.addEdge(new Edge<String>("B", "D", 4));
        graph.addEdge(new Edge<String>("B", "J", 10));
        graph.addEdge(new Edge<String>("C", "E", 2));
        graph.addEdge(new Edge<String>("C", "F", 1));
        graph.addEdge(new Edge<String>("D", "H", 5));
        graph.addEdge(new Edge<String>("D", "J", 6));
        graph.addEdge(new Edge<String>("E", "G", 2));
        graph.addEdge(new Edge<String>("F", "G", 3));
        graph.addEdge(new Edge<String>("F", "I", 5));
        graph.addEdge(new Edge<String>("G", "J", 4));
        graph.addEdge(new Edge<String>("G", "I", 3));
        graph.addEdge(new Edge<String>("H", "J", 2));
        graph.addEdge(new Edge<String>("I", "J", 3));

        // Since it's an undirected graph, add the reverse edges as well
        graph.addEdge(new Edge<String>("B", "A", 4));
        graph.addEdge(new Edge<String>("D", "A", 1));
        graph.addEdge(new Edge<String>("C", "B", 4));
        graph.addEdge(new Edge<String>("D", "B", 4));
        graph.addEdge(new Edge<String>("J", "B", 10));
        graph.addEdge(new Edge<String>("E", "C", 2));
        graph.addEdge(new Edge<String>("F", "C", 1));
        graph.addEdge(new Edge<String>("H", "D", 5));
        graph.addEdge(new Edge<String>("J", "D", 6));
        graph.addEdge(new Edge<String>("G", "E", 2));
        graph.addEdge(new Edge<String>("G", "F", 3));
        graph.addEdge(new Edge<String>("I", "F", 5));
        graph.addEdge(new Edge<String>("J", "G", 4));
        graph.addEdge(new Edge<String>("I", "G", 3));
        graph.addEdge(new Edge<String>("J", "H", 2));
        graph.addEdge(new Edge<String>("J", "I", 3));


        // Another test graph
        // graph.addEdge(new Edge<Integer>(0, 2, 15));
        // graph.addEdge(new Edge<Integer>(0, 3, 30));

        // graph.addEdge(new Edge<Integer>(1, 0, 10));
        // graph.addEdge(new Edge<Integer>(1, 3, 40));

        // graph.addEdge(new Edge<Integer>(3, 1, 40));
        // graph.addEdge(new Edge<Integer>(3, 2, 50));
        // graph.addEdge(new Edge<Integer>(3, 0, 30));

        // graph.addEdge(new Edge<Integer>(2, 0, 15));
        // graph.addEdge(new Edge<Integer>(2, 3, 50));


        // Test-1 for source = A
        String src = "A";
        System.out.println("Edges Includes in MST:\n");
        int cost = 0;
        ArrayList<Edge<String>> edges = graph.mst(src);
        for (Edge<String> edge : edges) {
            System.out.println(edge.src + " --> " + edge.dest + "\t(" + edge.wt + ")");
            cost += edge.wt;
        }
        System.out.println("Total Cost of MST: " + cost);
        System.out.println();
        
        // Test-2 for source = B
        String src2 = "B"; // by changing source, the MST can be changed
        System.out.println("Edges Includes in MST:\n");
        int cost2 = 0;
        ArrayList<Edge<String>> edges2 = graph.mst(src2);
        for (Edge<String> edge : edges2) {
            System.out.println(edge.src + " --> " + edge.dest + "\t(" + edge.wt + ")");
            cost2 += edge.wt;
        }
        System.out.println("Total Cost of MST: " + cost2);
        sc.close();
    }

}
