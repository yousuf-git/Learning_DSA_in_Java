// See bridgesGraph.png where graph
// Initially there is a single graphical component but it has 2 bridges: 0 -- 3 and 0 -- 8
// If we remove these edges then graph has 3 components (no. of components increased)

package graph_tests;
import graph.*;
import java.util.List;

public class BridgeTest {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 0);
        graph.addEdge(0, 1);

        graph.addEdge(1, 2);
        graph.addEdge(2, 1);

        graph.addEdge(0, 2);
        graph.addEdge(2, 0);

        graph.addEdge(3, 0); // bridge
        graph.addEdge(0, 3);

        graph.addEdge(3, 4);
        graph.addEdge(4, 3);

        graph.addEdge(5, 4);
        graph.addEdge(4, 5);

        graph.addEdge(3, 5);
        graph.addEdge(5, 3);

        graph.addEdge(0, 8); // bridge
        graph.addEdge(8, 0);

        graph.addEdge(6, 7);
        graph.addEdge(7, 6);

        graph.addEdge(7, 8);
        graph.addEdge(8, 7);

        graph.addEdge(8, 6);
        graph.addEdge(6, 8);

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

        List<Edge<Integer>> bridges = graph.getBridges();
        System.out.println("\nBridges: " + bridges.size());

        for (Edge<Integer> bridge : bridges) {
            if (bridge != null) {
                System.out.println(bridge.src + " ――― " + bridge.dest);
            } else {
                System.out.println("No bridge found in graph !");
            }
        }
    }
}
