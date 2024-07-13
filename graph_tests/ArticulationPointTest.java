package graph_tests;

import java.util.List;
import graph.Graph;

public class ArticulationPointTest {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 0);
        graph.addEdge(0, 1);

        graph.addEdge(1, 2);
        graph.addEdge(2, 1);

        graph.addEdge(0, 2);
        graph.addEdge(2, 0);

        // graph.addEdge(3, 0); // bridge 1
        // graph.addEdge(0, 3);

        graph.addEdge(3, 4);
        graph.addEdge(4, 3);

        graph.addEdge(5, 4);
        graph.addEdge(4, 5);

        graph.addEdge(3, 5);
        graph.addEdge(5, 3);

        // graph.addEdge(0, 8); // bridge 2
        // graph.addEdge(8, 0);

        // graph.addEdge(6, 7);
        // graph.addEdge(7, 6);

        // graph.addEdge(7, 8);
        // graph.addEdge(8, 7);

        // graph.addEdge(8, 6);
        // graph.addEdge(6, 8);

        // Another separate component which has its own bridge (9 ―― 10)
        // graph.addEdge(10, 11);
        // graph.addEdge(11, 10);

        // graph.addEdge(11, 12);
        // graph.addEdge(12, 11);
        
        // graph.addEdge(12, 10);
        // graph.addEdge(10, 12);
        
        // graph.addEdge(10, 9); // bridge
        // graph.addEdge(9, 10);

        System.out.println("\nTotal Vertices in Graph: " + graph.size());

        List<Integer> points = graph.articulationPoints();
        if (points.size() == 0) {
            System.out.println("\nNo Articulation Point found in graph !");
        } else {
            System.out.println("\nTotal Articulation Points: " + points.size());
            points.forEach((point) -> {
                System.out.println(point + " ");
            });
        }
    }
    
}
