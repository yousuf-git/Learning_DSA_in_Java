// This class is to test Kosarajus algorithm to find strongly connected components in a directed graph

package graph;

import java.util.ArrayList;

class KosarajuTest {
    public static void main(String[] args) {
        // Buildin graph
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(new Edge<Integer>(1, 0));
        graph.addEdge(new Edge<Integer>(0, 2));
        graph.addEdge(new Edge<Integer>(2, 1));
        // graph.addEdge(new Edge<Integer>(0, 3));
        // graph.addEdge(new Edge<Integer>(3, 4));
        System.out.println("\nEdges in Graph:");
        graph.printEdges();

        // Applying Kosaraju's algorithm
        ArrayList<ArrayList<Integer>> scc = graph.getStronglyConComps(0); // start doesn't matter
        System.out.println("\nTotal Strongly Component(s): " + scc.size() + "\n");
        for (ArrayList<Integer> component : scc) {
            System.out.println(component);
        }
    }

}