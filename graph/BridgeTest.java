package graph;

// simport java.util.List;

public class BridgeTest {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 0);
        graph.addEdge(0, 1);

        graph.addEdge(1, 2);
        graph.addEdge(2, 1);

        graph.addEdge(0, 2);
        graph.addEdge(2, 0);

        // graph.addEdge(4, 2);
        // graph.addEdge(2, 4);

        graph.addEdge(3, 0);
        graph.addEdge(0, 3);

        graph.addEdge(3, 4);
        graph.addEdge(4, 3);

        graph.addEdge(5, 4);
        graph.addEdge(4, 5);

        graph.addEdge(3, 5);
        graph.addEdge(5, 3);

        // List<Edge<Integer>> bridges = graph.getBridges(0);
        // for (Edge<Integer> bridge : bridges) {
        //     if (bridge != null) {
        //         System.out.println(bridge.src + " ---- " + bridge.dest);
        //     } else {
        //         System.out.println("No bridge found in graph !");
        //     }
        // }

        Edge<Integer> bridge = graph.getBridge(0);
        if (bridge != null) {
            System.out.println(bridge.src + " ---- " + bridge.dest);
        } else {
            System.out.println("No bridge found in graph !");
        }
    }

}
