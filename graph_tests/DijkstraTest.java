/*-----------Class to test Dikstra's Algorithm-----------*/

package graph_tests;

import java.util.HashMap;
// import java.util.Scanner;
import graph.Edge;
import graph.Graph;

public class DijkstraTest<T> {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge(new Edge<String>("A", "B", 10));
        graph.addEdge(new Edge<String>("A", "B", 10));
        graph.addEdge(new Edge<String>("A", "C", 40));
        graph.addEdge(new Edge<String>("B", "C", 20));

        HashMap<String, Integer> map = graph.getShortestPaths("A");
        for (String vertex : map.keySet()) {
            System.out.println("Minimum Cost From A To " + vertex + ": " + map.get(vertex));
        }
        System.out.println();

        Graph<Integer> graph2 = new Graph<>();

        // To get edges information from the user and build the graph
        // Ensure that user input matches the edges type in graph

        // Scanner sc = new Scanner(System.in);
        // String next = "y";
        // while (next.equals("y")) {
        //     try {
        //         System.out.print("Enter source: ");
        //         int src = sc.nextInt();
        //         System.out.print("Enter Destination: ");
        //         int dest = sc.nextInt();
        //         System.out.print("Enter Cost: ");
        //         int wt = sc.nextInt();
        //         graph2.addEdge(new Edge<Integer>(src, dest, wt));
        //         System.out.println("Edge: " + src + " --> " + dest + " (" + wt + ")" + " Added !");
        //         System.out.print("\nWant to add more? (y/n): ");
        //         next = sc.next();

        //     } catch (Exception e) {
        //         System.out.println("Invalid Input !");
        //         System.out.println(e);
        //     }
        // }
        // sc.close();

        // For manually building graph

        // A sample graph
        // graph2.addEdge(new Edge<Integer>(1, 6, 14));
        // graph2.addEdge(new Edge<Integer>(1, 3, 9));
        // graph2.addEdge(new Edge<Integer>(1, 2, 7));

        // graph2.addEdge(new Edge<Integer>(6, 1, 14));
        // graph2.addEdge(new Edge<Integer>(6, 5, 9));
        // graph2.addEdge(new Edge<Integer>(6, 3, 2));

        // graph2.addEdge(new Edge<Integer>(3, 6, 2));
        // graph2.addEdge(new Edge<Integer>(3, 4, 11));
        // graph2.addEdge(new Edge<Integer>(3, 2, 10));
        // graph2.addEdge(new Edge<Integer>(3, 1, 9));

        // graph2.addEdge(new Edge<Integer>(2, 1, 7));
        // graph2.addEdge(new Edge<Integer>(2, 3, 10));
        // graph2.addEdge(new Edge<Integer>(2, 4, 15));

        // graph2.addEdge(new Edge<Integer>(5, 6, 9));
        // graph2.addEdge(new Edge<Integer>(5, 4, 6));

        // graph2.addEdge(new Edge<Integer>(4, 5, 6));
        // graph2.addEdge(new Edge<Integer>(4, 3, 11));
        // graph2.addEdge(new Edge<Integer>(4, 2, 15));

        // The graph that is in DijkstraGraph.png
        graph2.addEdge(new Edge<Integer>(0, 1, 2));
        graph2.addEdge(new Edge<Integer>(0, 2, 4));
        
        graph2.addEdge(new Edge<Integer>(1, 2, 1));
        graph2.addEdge(new Edge<Integer>(1, 3, 7));

        graph2.addEdge(new Edge<Integer>(2, 4, 3));
        
        graph2.addEdge(new Edge<Integer>(4, 3, 2));
        graph2.addEdge(new Edge<Integer>(4, 5, 5));

        graph2.addEdge(new Edge<Integer>(3, 5, 1));

        HashMap<Integer, Integer> map2 = graph2.getShortestPaths(0);
        for (Integer vertex : map2.keySet()) {
            System.out.println("Minimum Cost From 0 To " + vertex + ": " + map2.get(vertex));
        }

        // If there is no path from source to destination, value of Integer.MAX_VALUE will be printed
    }

}

// Reference for dijkstra's algorithm complete explanation 
// https://youtu.be/Gd92jSu_cZk?feature=shared