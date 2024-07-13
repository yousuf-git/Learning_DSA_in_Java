package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import hashmap.HmNode; // my own built HashMap Node class

/**
- I've implemented graph in this class with generic type of vertices (V)
Fully connected / unconnected, directed / undirected graphs are handled in this single class in a general way
You just have to ensure that you are passing the right edges while building the graph.

- Implemented some methods both for connected and un-connected graphs separately, in order to let you understand the difference between them.

- General methods work well for both connected and unconncted graph so try to use them in most cases

_______________Contains following methods______________

01. buildConGraph(ArrayList<Edge<V>> edges) : Specially for connected graphs building with list of edges

02. buildGraph(ArrayList<Edge<V>> edges) : For general connected / unconnected graphs building

03. addVertex(V vertex): Vo add a vertex of generic type 

04. addEdge(Edges<V> edge) : Vo add an edge and ensure that source and destination are themselves added as vertex 

05. findNbrs(V vertex) : Vo Display All the neighbours of a given vertex

06. bfsCon(V start) / dfsCon(V start, Set<V> visited) : Special for traversal in connected graphs 

07. bfs(V start) / dfs(V start, Set<V> visited) : Vraversal in general graphs 

08. printPath(V src, V dest) : Vo print all possible paths from given source to destination vertex 

09. topologicalSort(V start) : Returns ArrayList of vertices in topologically sorted order 

10. hasCycleUnd() : Returns true if there is cycle in the undirected graph, false otherwise 

11. hasCycleDir() : Returns true if there is cycle in the directed graph, false otherwise 

12. getShortestPaths(V source) : Returns a HashMap<V, Integer> that contains vertices as keys and value refers to minimum cost/weights from source to those vertices using dijkstra's algorithm

13. getShortestPaths(V source) : Returns a HashMap<V, Integer> that contains vertices as keys and value refers to minimum cost/weights from source to those vertices using Bellman Ford algorithm

14. mst(V start) : Returns ArrayList of edges included in MSV for the given graph, using Prim's Algorithm

15. getStronglyConComps(V start) : Returns ArrayList<ArrayList<V>> List of List of stromgly component vise vertices by implementing Kosaraju's Algorithm

16. reverse() : Returns Graph<V>, that is transpose / reverse of original one (reverse the direction of all edges)

17. printEdges() : Simply prints all edges in graph in the form of source --> destination

18. getBridges() : Returns ArrayList<Edge<V>> that is list bridges in the graph, by implementing Varjan's Algorithm

19. articulationPoints() : Returns ArrayList<T> that is list of articulation points in the graph, by implementing Tarjan's Algorithm

- Other than these, there are some private methods also, that are to support the mentioned methods

* 
 */
public class Graph<V> {
    private int V; // number of vertices
    // A HashMap where key is vertex and value is an ArrayList of all edges directly connected to it
    private HashMap<V, ArrayList<Edge<V>>> graph;

    /**
     * @constructors
     */
    public Graph() {
        graph = new HashMap<>(); // initializing the graph as an empty HashMap
        this.V = 0; // initially, there will be 0 vertices
    }
    // public Graph(List<Edge<V>> edges) {
    //     graph = new HashMap<>();
    //     buildGraph(edges);
    // }
    // public Graph(List<V> vertices) {
    //     graph = new HashMap<>();
    //     vertices.forEach((vertex) -> {
    //         addVertex(vertex);
    //     });
    // }
    // Existing class members...

    public static <V> Graph<V> fromEdges(List<Edge<V>> edges) {
        Graph<V> graph = new Graph<>();
        graph.buildGraph(edges);
        return graph;
    }

    public static <V> Graph<V> fromVertices(List<V> vertices) {
        Graph<V> graph = new Graph<>();
        vertices.forEach(graph::addVertex);
        return graph;
    }

    /*---------------Build a graph by list of edges---------------- */
    public void buildGraph(List<Edge<V>> edges) {
        for (Edge<V> edge : edges) {
            V src = edge.src;
            V dest = edge.dest;
            // Check if src exist in the Map as a key or not
            // if (!graph.containsKey(src))
            //      graph.put(src, new ArrayList<>()); // if not exists, add an empty array list as its value
            // Vhe above if statement for adding new key, can be replaced by method putIfAbsent():
            if (graph.putIfAbsent(src, new ArrayList<>()) == null) { // returns valid value if key exists already
                V++; // if null is returned, it means key / vertex is newly added
            }
            if (graph.putIfAbsent(dest, new ArrayList<>()) == null) { // returns valid value if key exists already
                V++; // if null is returned, it means key / vertex is newly added
            }
            graph.get(src).add(edge); // get the vertex and attach new edge with it

            /*---------Shorter Form---------- */
            // this.addVertex(edge.src);
            // this.addVertex(edge.dest);
            // this.addEdge(edge);
        }
    }

    /*------------------Add a new vertex in graph---------- */
    public void addVertex(V vertex) {
        // add the vertex as a key, if it doesn't exist already
        if (graph.putIfAbsent(vertex, new ArrayList<>()) == null) {
            V++; // null is returned only if vertex is being added as a new key
        }
    }

    /*----------Add a new edge in the graph---------
     * Case-1: src and dest both exist in the graph => connect them
     * Case-2: src exists but dest doesn't => First add the dest as a vertex then connect them
     * Case-3: src and dest, both doesn't exist => Add both as vertex then connect them
     */
    public void addEdge(Edge<V> edge) {
        // Add source and destination of edge as individual vertices in the graph if they are not already there
        this.addVertex(edge.src); // add source
        this.addVertex(edge.dest); // add destination

        // Adding the given edge in the edges list of source
        graph.get(edge.src).add(edge); // Generally: ArrayList<Edges<V>>.add(Edge<V>)
    }

    /*----------Add a new edge if directly source and destination are given---------*/
    public void addEdge(V src, V dest) {
        this.addEdge(new Edge<V>(src, dest));
    }

    /*------Display All the neighbors of given vertex if it exists------- */
    public void findNbrs(V vertex) {
        if (!graph.containsKey(vertex)) {
            System.out.println(vertex + " is an invalid Vertex !");
            return;
        }
        for (Edge<V> edge : graph.get(vertex)) {
            // System.out.println(edge.src + " : " + edge.dest); // to display as an edge
            System.out.print(edge.dest + " ");
        }
        System.out.println();
    }

    // Below DFS and BFS are suitable if all the vertices are connected, i.e, graph is fully connected

    /*-----------For BFS traversal - in connected graph------------------ */
    public void bfsCon(V start) {
        if (!graph.containsKey(start)) {
            System.out.println("Start Point doesn't exist !");
            return;
        }
        // boolean[] visited = new boolean[graph.size()]; // for generic type, using index is a problem
        // That's why I'll be using a set to track visited vertices
        Set<V> visited = new HashSet<>();

        Queue<V> queue = new LinkedList<>(); // using a queue, implemented by linked list
        queue.add(start); // Add the starting vertex in the queue
        while (!queue.isEmpty()) {
            V vertex = queue.poll(); // get the veretx from front of queue
            if (visited.contains(vertex)) {
                continue;
            }
            System.out.print(vertex + " ");
            visited.add(vertex);
            // Add the neighbours of current vertex in the queue
            ArrayList<Edge<V>> edges = graph.get(vertex); // get all the edges connected to vertex
            for (Edge<V> edge : edges) { // iterate over the edges
                queue.add(edge.dest); // the destination of edges will give the neighbours
            }
        }
        System.out.println();
    }

    /*-------------DFS Traversal in recursive way - for connected graph ----------- */
    /**
     * @param start
     */
    public void dfsCon(V start) {
        // If any invalid start point is passed
        if (!graph.containsKey(start)) {
            System.out.println("Invalid Start point !");
            return;
        }
        dfsHelper(start, new HashSet<>());
    }

    /** A private method to help dfs traversal for connected graph
     * @param start
     * @param visited
     */
    private void dfsHelper(V start, Set<V> visited) {
        visited.add(start); // Add the current vertex in set of visited
        System.out.print(start + " ");
        for (Edge<V> edge : graph.get(start)) {
            if (!visited.contains(edge.dest)) {
                dfsHelper(edge.dest, visited);
            }
        }
    }

    // Below DFS and BFS are suitable if all the vertices are not connected, i.e, graph is not fully connected
    /*-------------DFS Traversal in recursive way - for general graph ----------- */
    /** Time Complexity: O (V+E)
     * @param start
     * @param visited
     */
    public void dfs(V start) {
        if (!graph.containsKey(start)) {
            System.out.println("Invalid Start Point !");
            return;
        }
        Set<V> visited = new HashSet<>();
        // start the dfs traversal from given start point
        dfsHelper(start, visited);
        // When control returns and there are some other vertices left
        for (V vertex : graph.keySet()) { // get all vertices
            if (!visited.contains(vertex)) { // start dfs from all those vertices which aren't visited yet
                dfsHelper(vertex, visited); // call to dfs for non-visited vertices
            }
        }

    }

    /*-------------BFS Traversal - for general graph ----------- */
    public void bfs(V start) {
        if (!graph.containsKey(start)) {
            System.out.println("Invalid Start Point !");
            return;
        }
        Set<V> visited = new HashSet<>();
        bfsHelper(start, visited);
        for (V vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                bfsHelper(vertex, visited);
            }
        }
    }

    /*------------Private method to help support bfs traversal in graph------------- */
    /**
     * @param start
     * @param visited
     */
    private void bfsHelper(V start, Set<V> visited) {
        Queue<V> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            V vertex = queue.poll(); // remove vertex from front of queue
            if (!visited.contains(vertex)) {
                System.out.print(vertex + " "); // display the non-visited vertex
                visited.add(vertex); // mark it as visited now
                ArrayList<Edge<V>> edges = graph.get(vertex); // get edges connected to that vertex
                // Add its neighbours into the queue
                for (Edge<V> edge : edges) {
                    queue.add(edge.dest);
                }
            }
        }
    }

    /*-----------Get Number of Vertices in the graph---------- */
    public int size() {
        return this.V;
    }

    /*-----------Display All paths from source to destination---------- */
    /**
     * @param src
     * @param dest
     */
    public void printPaths(V src, V dest) {
        // Some base conditions for validation of src and dest
        if (!graph.containsKey(src)) {
            System.out.println("Invalid Source !");
            return;
        }
        if (!graph.containsKey(dest)) {
            System.out.println("Invalid Destination !");
            return;
        }
        ArrayList<V> paths = new ArrayList<>();
        int[] pathsCount = {0};
        pathHelper(src, dest, paths, new HashSet<>(), pathsCount);

        if (pathsCount[0] == 0) {
            System.out.println("No path from " + src + " to " + dest);
        } else {
            System.out.println("Votal Path(s): " + pathsCount[0]);
        }
    }

    /*---------Recursive Method to support all paths finding method---------- */
    /**
     * @param src
     * @param dest
     * @param path
     * @param visited
     */
    private void pathHelper(V src, V dest, ArrayList<V> path, Set<V> visited, int[] pCount) {
        path.add(src);
        visited.add(src); // Visited the source vertex

        if (src.equals(dest)) {
            pCount[0]++;
            printPath(path);
            // return; // use return statement to print one path only
        } else {
            // DFS Traversal
            ArrayList<Edge<V>> edges = graph.get(src); // Get all edges directly connected to src
            for (Edge<V> edge : edges) { // Iterate over the neighbors by destination of these edges
                if (!visited.contains(edge.dest)) {
                    pathHelper(edge.dest, dest, path, visited, pCount);
                }
            }
        }

        // Backtrack
        path.remove(path.size() - 1); // Remove the last element added to the path
        visited.remove(src); // Remove src from visited after processing its neighbors
    }

    /*--------A private method to print the path containing vertices' ArrayList------ */
    private void printPath(ArrayList<V> vertices) {
        System.out.print(vertices.get(0));
        for (int i = 1; i < vertices.size(); i++) {
            System.out.print(" --> " + vertices.get(i));
        }
        System.out.println();
    }

    /*---------Vo print Vopological Sorted Order of vertices in graph------------ */
    /**
     * @param start
     * @return ArrayList of vertices in topological sorted order
     */
    public ArrayList<V> topologicalSort(V start) {
        /*
         * 1. Apply dfs on start edge 
         * - I have made a topoSortHelper() method that uses modified dfs for topological sorting
         */
        Set<V> visited = new HashSet<>(); // to track visited vertices
        Stack<V> stack = new Stack<>(); // to store vertices in sorted order
        topoSortHelper(start, visited, stack); // dfs on start vertex
        // Get all vertices of the graph and apply dfs if any is not visited, (specially for unconnected graph)
        for (V vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                topoSortHelper(vertex, visited, stack);
            }
        }
        // When all the vertices are visited, just pop all elements from the stack and return it as an ArrayList
        ArrayList<V> vertices = new ArrayList<>();
        while (!stack.isEmpty()) {
            vertices.add(stack.pop());
        }
        return vertices;
    }

    /*-----------Private Method to support Topological Sort------- */
    /**
     * @param start
     * @param visited
     * @param stack
     */
    private void topoSortHelper(V start, Set<V> visited, Stack<V> stack) {
        visited.add(start);
        // Get neighbours of current start vertex
        for (Edge<V> edge : graph.get(start)) {
            if (!visited.contains(edge.dest)) {
                topoSortHelper(edge.dest, visited, stack); // dfs on all neighbours
            }
        }
        // when neighbours are finished, just push the current vertex in stack
        stack.push(start);
    }

    /*---------To Check if there is a cycle in undirected graph---------- 
     * Time Complexity = O(V+E)
    */
    public boolean hasCycleUnd() {
        Set<V> visited = new HashSet<>();
        // Get the vertices of graph
        for (V vertex : graph.keySet()) {
            // System.out.println("Vertex: " + vertex);
            if (!visited.contains(vertex)) {
                if (cycleHelperUnd(vertex, visited, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*---------A private method to help dfs traversal for hasCycleUnd()------- */
    /**
     * @param curr
     * @param visited
     * @param parent
     * @return boolean
     */
    private boolean cycleHelperUnd(V curr, Set<V> visited, V parent) {
        visited.add(curr);
        // System.out.println("Current: " + curr);
        // Get all neighbours of the current vertex
        for (Edge<V> edge : graph.get(curr)) {
            // If current neighbour is not the parent
            if (!edge.dest.equals(parent)) {
                // System.out.println("Neighbour: " + edge.dest);
                // If non-parent neighbour is already visited
                if (visited.contains(edge.dest)) {
                    return true;
                }
                // Neighbour is nor visited neither parent
                if (cycleHelperUnd(edge.dest, visited, curr)) {
                    return true;
                }
                // else, check for next neighbour
            }
        }
        return false;
    }

    /*-----------To check cycle in directed graph-------- */
    /**
     * @return boolean
     */
    public boolean hasCycleDir() {
        // Get all the vertices and apply dfs approach on them
        Set<V> visited = new HashSet<>();
        for (V vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                if (cycleHelperDir(vertex, visited, new HashSet<>())) {
                    return true;
                }
            }
        }
        return false;
    }

    /*---------Helper for hasCycleDir() that applies dfs----- */
    /**
     * @param curr    => current node
     * @param visited => to track visited vertices
     * @param stack   => recursion stack, to check if any vertex has been in the recursion stack already
     * @return boolean
     */
    private boolean cycleHelperDir(V curr, Set<V> visited, Set<V> stack) {
        visited.add(curr);
        stack.add(curr);
        // get all neighbours of current vertex
        for (Edge<V> edge : graph.get(curr)) { // edge.dest will be neighbour
            if (stack.contains(edge.dest)) { // if neigbour is already in the recursion stack there is a cycle
                return true;
            }
            // if any neigbour returns true, cycle exists, simply return true
            if (cycleHelperDir(edge.dest, visited, stack)) { // recursive call to neighbour
                return true;
            }
            // otherwise do nothing just continue to next neighbour
        }
        // backtrack => remove current vertex from recursion stack
        stack.remove(curr);
        return false;
    }

    /*--------For Dijkstra's Algorithm-----------*/
    /**
     * @param source => Shortest Path finding from this single source to all vertices
     * @return HashMap containing vertices and minimum cost from start point to that vertices
     */ 
    public HashMap<V, Integer> getShortestPaths(V source) {
        // A graph holding distance from source to specific vertex
        HashMap<V, Integer> costs = new HashMap<>(); // keys : Vertices, Values : Distance / cost to that vertex
        // Get all vertices and add cost from source to destination as infinity, execpt for source itself, because source to source cost is 0
        // Building the cost storage graph
        for (V vertex : graph.keySet()) { // get all vertices of graph
            if (vertex.equals(source)) {
                costs.put(source, 0); // source to souce cost is 0
            } else {
                costs.put(vertex, Integer.MAX_VALUE); // Integer.MAX_VALUE => +infinity
            }
        }
        /**
         * Create a priority queue to insert the key-value pair
         * I've used my HmNode class that has 2 attributes: key and value
         * I'll store vertex as a key and cost to that vertex as value, in the HmNode 
         * This queue will internally sort the nodes on the basis of minimum cost / distance
         * Means the vertex having minimum cost will be removed first
         */
        PriorityQueue<HmNode<V, Integer>> queue = new PriorityQueue<>(new Comparator<HmNode<V, Integer>>() {
            @Override
            public int compare(HmNode<V, Integer> node1, HmNode<V, Integer> node2) {
                return Integer.compare(node1.value, node2.value);
            }
        });
        queue.add(new HmNode<V, Integer>(source, 0));

        // A vertex will be marked visited if all of its neighbours' distance has been updated
        Set<V> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            HmNode<V, Integer> node = queue.remove(); // remove the max priority (min cost) vertex
            V vertex = node.key;
            // Integer cost = node.value;

            // Get all neighbours of current vertex
            for (Edge<V> edge : graph.get(vertex)) { // edge.dest is neighbour
                if (!visited.contains(edge.dest)) { // for each unvisited neighbour
                    int srcCost = costs.get(vertex);
                    int destCost = costs.get(edge.dest);

                    // Check for Relaxation
                    if (srcCost + edge.wt < destCost) {
                        // update the destination's / neighbour's cost
                        costs.put(edge.dest, srcCost + edge.wt);
                    }
                    queue.add(new HmNode<V, Integer>(edge.dest, costs.get(edge.dest)));// add neighbour and its cost
                }
            }
            visited.add(vertex);
        }
        return costs;
    }

    /*--------For Bellman Ford Algorithm-----------*/
    // To get the shortest paths from source to all vertices, including the negavtive edges in graph
    public HashMap<V, Integer> shortestPaths(V source) {
        HashMap<V, Integer> costs = new HashMap<>();
        // Put the vertices and cost to that vertices in costs graph
        for (V vertex : graph.keySet()) {
            if (vertex.equals(source)) {
                costs.put(source, 0); // add a new pair as : source - 0
            } else {
                costs.put(vertex, Integer.MAX_VALUE); // Cost to all other vertices is +infinity
            }
        }
        for (int i = 1; i < V; i++) { // Repeat V-1 times
            for (V vertex : graph.keySet()) { // get the vertices
                int srcCost = costs.get(vertex);
                for (Edge<V> edge : graph.get(vertex)) { // get the neighbours
                    int destCost = costs.get(edge.dest); // get the cost of neighbour
                    // Check for relxation
                    if (srcCost != Integer.MAX_VALUE && srcCost + edge.wt < destCost) { // +infinity + 1 = -ve
                        costs.put(edge.dest, srcCost + edge.wt); // update the cost
                    }
                }
            }
        }
        return costs;
    }

    /*------------Prim's Algorithm------------- */
    // To find the Minimum Spanning Tree - MST
    // Time Complexity : O((V+E) log V) = O(E log V)
    public ArrayList<Edge<V>> mst(V start) {
        ArrayList<Edge<V>> edges = new ArrayList<>(); // To store the edges included in MST
        // int cost = 0; // to track the cost of MST

        PriorityQueue<HmNode<V, Integer>> queue = new PriorityQueue<>(new Comparator<HmNode<V, Integer>>() {
            @Override
            public int compare(HmNode<V, Integer> node1, HmNode<V, Integer> node2) {
                return Integer.compare(node1.value, node2.value);
            }
        });
        queue.add(new HmNode<V, Integer>(start, 0));
        Set<V> mst = new HashSet<>(); // track the vertices that are added in MST
        Map<V, Edge<V>> edgeTo = new HashMap<>(); // to track the edge that leads to each vertex in the MST

        while (!queue.isEmpty()) {
            HmNode<V, Integer> currNode = queue.remove(); // O(log n)
            V vertex = currNode.key;
            if (!mst.contains(vertex)) {
                mst.add(vertex); // O(1)
                // cost += currNode.value;

                // Add the edge that leads to this vertex to the MST (except for the start vertex)
                if (edgeTo.containsKey(vertex)) {
                    edges.add(edgeTo.get(vertex)); // Amortized O(1) time complexity
                }

                for (Edge<V> edge : graph.get(vertex)) { // get edges connected to current vertex
                    if (!mst.contains(edge.dest)) { // for each neighbour that is not in MST
                        // Add the neighbour and edge cost / wt in the queu
                        queue.add(new HmNode<V, Integer>(edge.dest, edge.wt)); // O(log n)
                        // Add the neighbour and edge leading to that neighbour in edgeTo Map
                        edgeTo.put(edge.dest, edge); // O(1)
                    }
                }
            }
        }
        // System.out.println("Cost: " + cost);
        return edges;
    }

    /* Kosaraju's Algorithm - To Find the strongly connected components in graph
     * A group of vertices is said to be strongly connected if there is path from every vertex to every other
     * Approach:
     * 1. Apply topological sort on the graph (List of edges in topological order)
     * 2. Reverse the graph (reverse the direction of all edges)
     * 3. Apply DFS on the vertices of reversed graph in the order of topological sort
     * 4. Each time a new vertex is visited, it will be a new component
     * 5. Repeat the process until all vertices are visited
     * 6. Return the components
     */
    public ArrayList<ArrayList<V>> getStronglyConComps(V start) {
        // Validation Check
        if (!graph.keySet().contains(start)) {
            System.out.println("Start Point Doesn't Exist!");
            return null;
        }
        ArrayList<ArrayList<V>> components = new ArrayList<>();
        // Step 1
        ArrayList<V> sortedVertices = this.topologicalSort(start);
        // Step 2
        Graph<V> reversedGraph = this.reverse();
        // Step 3 - DFS
        Set<V> visited = new HashSet<>();
        ArrayList<V> currComponent;
        while (!sortedVertices.isEmpty()) {
            // a list to add vertices inlcuded in the component
            V vertex = sortedVertices.remove(0); // get the vertices one-by-one in topological order

            // Start traversing in depth from each unvisited vertex
            if (!visited.contains(vertex)) {
                currComponent = new ArrayList<>();
                sccHelper(vertex, currComponent, visited, reversedGraph); // dfs
                components.add(currComponent);
            }
        }
        return components;
    }


    /** A private method to help apply modified DFS for stronglyConnectedComponents()
     * @param start
     * @param vertices
     * @param visited
     * @param revGraph
     */
    private void sccHelper(V start, ArrayList<V> vertices, Set<V> visited, Graph<V> revGraph) {
        // if (visited.contains(start)) {
        //     return;
        // }
        visited.add(start);
        vertices.add(start);
        // get all edges connected of vertex
        for (Edge<V> edge : revGraph.graph.get(start)) {
            // for each neighbour
            if (!visited.contains(edge.dest)) {
                sccHelper(edge.dest, vertices, visited, revGraph);
            }
        }
    }

    /*--------Method to reverse the graph---------*/
    public Graph<V> reverse() {
        Graph<V> reversedGraph = new Graph<>();
        for (V vertex : graph.keySet()) {
            for (Edge<V> edge : graph.get(vertex)) {
                reversedGraph.addEdge(new Edge<V>(edge.dest, edge.src));
            }
        }
        return reversedGraph;
    }

    /*--------To print the edges on console-------*/
    public void printEdges() {
        for (V vertex : graph.keySet()) {
            for (Edge<V> edge : graph.get(vertex)) {
                System.out.println(edge.src + " --> " + edge.dest);
            }
        }
    }

    /**
     * Tarjan's Algorithm => To get the edge that is acting as a bridge
     * => For fully connected graph - returns a single bridge edge, null otherwise 
     * Key Points:
     *  - Discovery Time (dTime): The time at which the current vertex was visited / discovered
     *  - Lowest Discovery Time (lowDt): The lowest of discovery time among all the neighbours
     *  - Bridge Condition: low[u] < dt[v] => (u --> v) is bridge edge
     */
    public ArrayList<Edge<V>> getBridges() {
        // Step-2 : Keep track of dTime and lowestDt by a HashMap<Vertex, Time>
        Set<V> visited = new HashSet<>(); // track visited vertices
        HashMap<V, Integer> dTime = new HashMap<>(); // Key: vertex, Value: Dicovery Time
        HashMap<V, Integer> lowestDt = new HashMap<>(); // Key: vertex, Value: Lowest Dicovery Time
        // int time = 0; // if time is used as an integer, it is not inrcremented across recursive calls correctly
        int[] time = {0};
        ArrayList<Edge<V>> bridges = new ArrayList<>();
        // Step-1 : Apply DFS on each unvisited vertex
        for (V vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                bridgeDfs(vertex, null, visited, time, dTime, lowestDt, bridges);
            }
        }
        // For debugging - To see discovery and lowest time of each vertex at the end
        System.out.println("\nDiscovery Times: ");
        for (V vertex : dTime.keySet()) {
            System.out.println("Vertex: " + vertex + "; dTime: " + dTime.get(vertex));
        }
        System.out.println("\nLowest Discovery Times: ");
        for (V vertex : lowestDt.keySet()) {
            System.out.println("Vertex: " + vertex + "; lowTime: " + lowestDt.get(vertex));
        }
        return bridges;
    }

    /** Private Method to help DFS traversal for getBridges() method
     * @param vertex => current vertex
     * @param parent
     * @param visited
     * @param time
     * @param dTime => discovery time
     * @param lowDt => lowest discovery time
     * @param bridges => list of bridges (in case if there are more than one)
     */
    private void bridgeDfs(V vertex, V parent, Set<V> visited, int[] time, HashMap<V, Integer> dTime,
            HashMap<V, Integer> lowDt, ArrayList<Edge<V>> bridges) {
        visited.add(vertex); // visit the vertex
        dTime.put(vertex, time[0]); // initially dVime and lowDt have same values
        lowDt.put(vertex, time[0]);
        time[0]++; // update the time
        // Iterate for all the neighbours of current vertex
        for (Edge<V> edge : graph.get(vertex)) {
            V neighbour = edge.dest;
            if (neighbour.equals(parent)) {
                continue;
            }
            if (!visited.contains(neighbour)) {
                bridgeDfs(neighbour, vertex, visited, time, dTime, lowDt, bridges);
                // After DFS - update lowest dTime based on neighbours dTime
                // low[curr] = min (low[curr], low[neighbour])
                lowDt.put(vertex, Math.min(lowDt.get(vertex), lowDt.get(neighbour)));
                if (dTime.get(vertex) < lowDt.get(neighbour)) {
                    // return new Edge<V>(edge.src, neighbour);
                    bridges.add(edge);
                }
            }
            // If neighbour is visited - update lowest dTime of current vertex
            else {
                // low[currVertex] = min (low[currVertex], dTime[neighbour])
                lowDt.put(vertex, Math.min(lowDt.get(vertex), dTime.get(neighbour)));
            }
        }
    }

    /*----Tarjan's Algorithm => To get all the Articulation Points in graph----- */
    public ArrayList<V> articulationPoints() {
        ArrayList<V> atcPoints = new ArrayList<>();
        HashMap<V, Integer> minTime = new HashMap<>();
        HashMap<V, Integer> discTime = new HashMap<>();
        Set<V> visited = new HashSet<>();
        int[] time = {-1};
        // Apply DFS for each vertex
        for (V veretx : graph.keySet()) {
            if (!visited.contains(veretx)) {
                atcPointDfs(veretx, null, visited,time, minTime, discTime, atcPoints);
            }
        }
        // For debugging - To see discovery and minimum time of each vertex at the end
        System.out.println("\nDiscovery Times: ");
        for (V vertex : discTime.keySet()) {
            System.out.println("Vertex: " + vertex + "; dTime: " + discTime.get(vertex));
        }
        System.out.println("\nLowest Discovery Times: ");
        for (V vertex : minTime.keySet()) {
            System.out.println("Vertex: " + vertex + "; lowTime: " + minTime.get(vertex));
        }
        return atcPoints;
    }

    private void atcPointDfs(V vertex, V parent,Set<V> visited, int[] time, HashMap<V, Integer> minTime, HashMap<V, Integer> discTime, ArrayList<V> atcPoints) {
        visited.add(vertex);
        // Update and store min and discovery time of current vertex
        time[0]++;
        minTime.put(vertex, time[0]);
        discTime.put(vertex, time[0]);
        int children = 0;
        // Iterate over the neighbours
        for (Edge<V> edge : graph.get(vertex)) {
            V neighbour = edge.dest;
            // If neighbour is parent - skip
            if (neighbour.equals(parent)) {
                continue; // goto next neighbour
            }
            // If neighbour is univisted non parent / children - Apply DFS
            if (!visited.contains(neighbour)) {
                children++;
                atcPointDfs(neighbour, vertex, visited, time, minTime, discTime, atcPoints);
                // update min time of vertex -> min(low[curr], low[neighbour])
                minTime.put(vertex, Math.min(minTime.get(vertex), minTime.get(neighbour)));
                // Check for articulation Point condition 1
                if (parent != null && discTime.get(vertex) <= minTime.get(neighbour)) {
                    System.out.println("Condition 1 satisfied for " + vertex);
                    atcPoints.add(vertex);
                }
            } else {  // if non-parent neighbour is already visited - ancestor
                minTime.put(vertex,Math.min(minTime.get(vertex), discTime.get(neighbour)));
            }
        }
        // Check for articulation Point condition 2
        if (parent == null && children > 1) {
            System.out.println("Condition 2 satisfied for " + vertex);
            atcPoints.add(vertex);
        }
    }
}
