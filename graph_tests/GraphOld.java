/* 
 * Did some mistakes while implementing graph, this class contains those mistakes
 * One major was index issue. At the same index more edges of multiple vertices were being stored
 * and hence throwing errors sometime even if vertex exists in the graph
 * 
 * Note: Don't let go your mistakes vain, always learn from them :)
 */

package graph_tests;

import java.util.ArrayList;
import graph.Edge;

public class GraphOld<T> {
    int V; // number of vertices
    ArrayList<Edge<T>>[] graph;

    // constructor
    // @param
    // V => Number of Vertices
    @SuppressWarnings("unchecked") // to remove warning of using array list without type

    GraphOld(int V) {
        graph = new ArrayList[V];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>(); // creating an empty ArrayList for each edge that will contain edges
        }
        this.V = V;
        // for (ArrayList<Edge<T>> edge : graph) {
        // edge = new ArrayList<>();
        // }
    }

    public void buildGraph(ArrayList<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            // System.out.println(edge.src.hashCode());
            int hashCode = edge.src.hashCode();
            // int hashIdx = (hashCode == 0) ? hashCode : hashCode % V;

            int hashIdx = Math.abs(hashCode % V);
            System.out.print(hashIdx + " ");
            graph[hashIdx].add(edge);
        }
        System.out.println();
    }

    public void findNbrs(T vertex) {
        int hashIdx = Math.abs(vertex.hashCode() % V);
        // check if source of 1st edge against hashcode of given vertex is same as given
        // vertex or not
        if (!graph[hashIdx].get(0).src.equals(vertex)) {
            System.out.println("Invalid Vertex");
            return;
        }
        for (Edge<T> edge : graph[hashIdx]) {
            System.out.println(edge.src + " : " + edge.dest);
        }
    }

    public static void main(String[] args) {
        System.out.println();

        // Graph<Integer> graph = new Graph<>(7); // initialize a graph with 5 vertices
        // ArrayList<Edge<Integer>> list = new ArrayList<>();

        GraphOld<String> graph = new GraphOld<>(7); // initialize a graph with 5 vertices
        ArrayList<Edge<String>> list = new ArrayList<>();
        // Adding edges to the list
        // list.add(new Edge<Integer>(0, 1));
        // list.add(new Edge<Integer>(0, 2));

        // list.add(new Edge<Integer>(1, 0));
        // list.add(new Edge<Integer>(1, 3));

        // list.add(new Edge<Integer>(2, 0));
        // list.add(new Edge<Integer>(2, 4));

        // list.add(new Edge<Integer>(3, 1));
        // list.add(new Edge<Integer>(3, 4));
        // list.add(new Edge<Integer>(3, 5));

        // list.add(new Edge<Integer>(4, 2));
        // list.add(new Edge<Integer>(4, 3));
        // list.add(new Edge<Integer>(4, 5));

        // list.add(new Edge<Integer>(5, 3));
        // list.add(new Edge<Integer>(5, 4));
        // list.add(new Edge<Integer>(5, 6));

        // list.add(new Edge<Integer>(7, 5));

        list.add(new Edge<String>("Multan", "Murree"));
        list.add(new Edge<String>("Multan", "Lahore"));

        list.add(new Edge<String>("Murree", "Multan"));
        list.add(new Edge<String>("Murree", "Peshawar"));

        list.add(new Edge<String>("Peshawar", "Murree"));
        list.add(new Edge<String>("Peshawar", "Quetta"));

        list.add(new Edge<String>("Quetta", "Peshawar"));
        list.add(new Edge<String>("Quetta", "Lahore"));
        list.add(new Edge<String>("Quetta", "Karachi"));

        list.add(new Edge<String>("Lahore", "Karachi"));
        list.add(new Edge<String>("Lahore", "Multan"));
        list.add(new Edge<String>("Lahore", "Karachi"));

        list.add(new Edge<String>("Karachi", "Lahore"));
        list.add(new Edge<String>("Karachi", "Quetta"));
        list.add(new Edge<String>("Karachi", "Islamabad"));

        list.add(new Edge<String>("Islamabad", "Karachi"));

        // Building graph out of these edges
        graph.buildGraph(list);

        // System.out.println(graph); // for testing that graph has been built
        // successfully
        graph.findNbrs("Karachi");

        System.out.println();
    }

}
