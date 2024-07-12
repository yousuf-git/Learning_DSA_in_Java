/*-----------Class to test Bellman Ford Algorithm-----------*/
// See BellmanFordGraph.png to see the graph and then input edges accordingly

package graph_tests;

import java.util.HashMap;
import java.util.Scanner;
import graph.Edge;
import graph.Graph;

public class BellmanFord {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();

        System.out.println("\n--------------Add Edges to the graph------------------\n");
        Scanner sc = new Scanner(System.in);
        String next = "y";
        while (next.equals("y")) {
            System.out.print("Source: ");
            int src = sc.nextInt();
            System.out.print("Destination: ");
            int dest = sc.nextInt();
            System.out.print("Weight: ");
            int wt = sc.nextInt();

            graph.addEdge(new Edge<Integer>(src, dest, wt));
            System.out.print("Add More? (y/n): ");
            next = sc.next();
            System.out.println();
        }

        System.out.print("Enter Source to get paths: ");
        int src = sc.nextInt();
        System.out.println();

        HashMap<Integer, Integer> paths = graph.shortestPaths(src);
        for (Integer veretx : paths.keySet()) {
            System.out.println("Shortest Distance from " + src + " to " + veretx + " = " + paths.get(veretx));
        }
        System.out.println();
        sc.close();
    }
}

