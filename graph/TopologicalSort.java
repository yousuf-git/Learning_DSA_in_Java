/* This is a test class test the topological sort method of my graph class */

package graph;

import java.util.ArrayList;
// import java.util.HashSet;

public class TopologicalSort {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        // Refer to graph.txt file to see the details of topological sort and graph I
        // have used here
        graph.addEdge(new Edge<String>("B", "A"));
        graph.addEdge(new Edge<String>("A", "D"));
        graph.addEdge(new Edge<String>("D", "C"));

        // graph.dfs("A", new HashSet<>()); // for testing - to see all vertices are
        // added successfully
        ArrayList<String> order = graph.topologicalSort("A"); // Output: B A D C
        System.out.print("G1 Topological Sort: ");
        for (String string : order) {
            System.out.print(string + " ");
        }
        System.out.println();
        graph.

        // Test-2 on Another graph

        Graph<Character> graph2 = new Graph<>();
        graph2.addEdge(new Edge<Character>('B', 'A'));
        graph2.addEdge(new Edge<Character>('B', 'D'));

        graph2.addEdge(new Edge<Character>('A', 'D'));

        graph2.addEdge(new Edge<Character>('D', 'C'));
        graph2.addEdge(new Edge<Character>('D', 'E'));

        graph2.addEdge(new Edge<Character>('G', 'D'));
        graph2.addEdge(new Edge<Character>('G', 'F'));

        graph2.addEdge(new Edge<Character>('F', 'E'));

        graph2.addEdge(new Edge<Character>('H', 'F'));

        // graph2.dfs('A', new HashSet<>()); // for testing - to see all vertices are
        // added successfully

        System.out.print("G2 Topological Sort: "); // Output: H G F B A D E C
        for (Character vertex : graph2.topologicalSort('A')) {
            System.out.print(vertex + " ");
        }
        System.out.println();
    }
}
// For representation of graph and complete explanation of algorithm (hindi tutorial)
// https://youtu.be/3tkcfvCNtM8?feature=shared
// -----------------------------------------------------------------------------------------------------------------