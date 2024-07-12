// See MainGraph.png in ./graph_images to see the graph and information
/**
 * This class performs following tests for graph.java and Edge.java: 
 *  - Graph()                   constructor
 *  - Edge()                    constructor
 *  - buildGraph(ArrayList<T>)  to build graph by list of edges
 *  - findNbrs(T vertex)        to find all neighbours of a vertex
 *  - size()                    to get total number of vertices in graph
 *  - bfsCon() / dfsCon()       for traversal on fully connected graph
 *  - dfs() / bfs()             for traversal on un-connected graph
 */

package graph_tests;

import java.util.ArrayList;
import graph.Edge;
import graph.Graph;

public class Main {
    // Main method for testing
    public static void main(String[] args) {
        System.out.println();

        /*-----------For Building a graph of String type----------*/
        // Graph<String> graph = new Graph<>(); // initialize a graph with 5 vertices
        // ArrayList<Edge<String>> list = new ArrayList<>();

        // // See Cities graph from graph.txt file
        // list.add(new Edge<String>("Multan", "Murree"));
        // list.add(new Edge<String>("Multan", "Lahore"));

        // list.add(new Edge<String>("Murree", "Multan"));
        // list.add(new Edge<String>("Murree", "Peshawar"));

        // list.add(new Edge<String>("Peshawar", "Murree"));
        // list.add(new Edge<String>("Peshawar", "Quetta"));

        // list.add(new Edge<String>("Quetta", "Peshawar"));
        // list.add(new Edge<String>("Quetta", "Lahore"));
        // list.add(new Edge<String>("Quetta", "Karachi"));

        // list.add(new Edge<String>("Lahore", "Karachi"));
        // list.add(new Edge<String>("Lahore", "Multan"));
        // list.add(new Edge<String>("Lahore", "Quetta"));

        // list.add(new Edge<String>("Karachi", "Lahore"));
        // list.add(new Edge<String>("Karachi", "Quetta"));
        // list.add(new Edge<String>("Karachi", "Islamabad"));

        // list.add(new Edge<String>("Islamabad", "Karachi"));

        /*-----------For Building a graph of integer type----------*/
        Graph<Integer> graph = new Graph<>(); // initialize a graph with 5 vertices
        ArrayList<Edge<Integer>> list = new ArrayList<>();

        list.add(new Edge<Integer>(0, 1));
        list.add(new Edge<Integer>(0, 2));

        list.add(new Edge<Integer>(1, 0));
        list.add(new Edge<Integer>(1, 3));

        list.add(new Edge<Integer>(2, 0));
        list.add(new Edge<Integer>(2, 4));

        list.add(new Edge<Integer>(3, 1));
        list.add(new Edge<Integer>(3, 4));
        list.add(new Edge<Integer>(3, 5));

        list.add(new Edge<Integer>(4, 2));
        list.add(new Edge<Integer>(4, 3));
        list.add(new Edge<Integer>(4, 5));

        list.add(new Edge<Integer>(5, 3));
        list.add(new Edge<Integer>(5, 4));
        list.add(new Edge<Integer>(5, 6));

        list.add(new Edge<Integer>(6, 5));

        // For unconnected graph testing, these vertices are not connected to above ones
        list.add(new Edge<Integer>(8, 9));
        list.add(new Edge<Integer>(8, 10));

        // Building graph out of these edges
        graph.buildGraph(list);
        System.out.println("\nGraph Built Successfully !");

        // System.out.print("Neighbours of Multan: ");
        // graph.findNbrs("Multan");
        // graph.findNbrs("Lahore");
        // graph.findNbrs("Hogwards"); // invalid vertex

        System.out.println("Number of Vertices: " + graph.size());

        System.out.println("Neighbours of 1"); // for integer type graph
        graph.findNbrs(1);

        System.out.println("\n----------Connected---------");
        System.out.print("BFS Traversal: ");
        // graph.bfsCon("Murree");
        graph.bfsCon(0);
        // System.out.println();

        System.out.print("DFS Traversal: ");
        // graph.dfsCon("Murree");
        graph.dfsCon(0);
        System.out.println();

        System.out.println("\n----------Unconnected---------");
        System.out.print("DFS: ");
        // graph.dfs("Murree");
        graph.dfs(0);
        System.out.println();

        System.out.print("BFS: ");
        // graph.bfs("Murree");
        graph.bfs(0);
        System.out.println();
        // Q: Print All paths from given source to destination
        // System.out.println("\n----------All Paths from Multan to Murree are below---------");
        // graph.printPaths("Multan", "Murree");
        int src = 0;
        int dest = 5;
        System.out.println("\n----------All Paths from " + src + " to " + dest + " are below---------");
        graph.printPaths(src, dest);

    }

}
