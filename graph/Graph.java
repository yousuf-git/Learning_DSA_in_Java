package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import hashmap.HmNode; // my own built HashMap Node class

/**
 * I've implemented graph in this class
 * Fully connected / unconnected, directed / undirected graphs are handled in
 * this single class in a general way You just have to ensure that you are
 * passing the right edges while building the
 * graph Implemented some methods both for connected and un-connected graphs
 * separately, in order to let you
 * understand the difference between them General methods work well for both
 * connected and unconncted graph so try to
 * use them in most cases
 * 
 * Contains following methods:
 * 
 * 01. buildConGraph(ArrayList<Edge<T>> edges) : Specially for connected graphs
 * building with list of edges
 * 02. buildGraph(ArrayList<Edge<T>> edges) : For general connected / unconnected
 * graphs building 03. addVertex(T vertex)
 * : To add a vertex of generic type 04. addEdge(Edges<T> edge) : To add an edge
 * and ensure that source and
 * destination are themselves added as vertex 05. findNbrs(T vertex) : To
 * Display All the neighbours of a given
 * vertex 06. bfsCon(T start) / dfsCon(T start, Set<T> visited) : Special for
 * traversal in connected graphs 07. bfs(T
 * start) / dfs(T start, Set<T> visited) : Traversal in general graphs 08.
 * printPath(T src, T dest) : To print all
 * possible paths from given source to destination vertex 09. topologicalSort(T
 * start) : Returns ArrayList of
 * vertices in topologically sorted order 10. hasCycleUnd() : Returns true if
 * there is cycle in the undirected graph,
 * false otherwise 11. hasCycleDir() : Returns true if there is cycle in the
 * directed graph, false otherwise 12.
 * getShortestPaths(T source) : Returns a HashMap<T, Integer> that contains
 * vertices as keys and value refers to
 * minimum cost/weights from source to that vertices using dijkstra's algorithm
 * 
 */

public class Graph<T> {
    protected int V; // number of vertices
    // A HashMap where key is vertex and value is an ArrayList of all edges directly
    // connected to it
    protected HashMap<T, ArrayList<Edge<T>>> graph;

    /**
     * @constructor
     */
    public Graph() {
        graph = new HashMap<>(); // initializing the graph as an empty HashSet
        this.V = 0; // current vertices will be zero
    }

    /*---------------Build An un-connected graph by list of edges---------------- */
    public void buildGraph(ArrayList<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            T src = edge.src;
            T dest = edge.dest;
            // Check if src exist in the Map as a key or not
            // if (!graph.containsKey(src)) {
            // graph.put(src, new ArrayList<>()); // if not exists, add an empty array list as its value
            // }
            // The above if statement for adding new key, can be replaced by method putIfAbsent():
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
    public void addVertex(T vertex) {
        // add the vertex as a key, if it doesn't exist already
        if (graph.putIfAbsent(vertex, new ArrayList<>()) == null) {
            V++; // null is returned only if vertex is being added as a new key
        }
    }

    /*----------Add a new edge in the vertex---------
     * Case-1: src and dest both exist in the graph => connect them
     * Case-2: src exists but dest doesn't => First add the dest as a vertex then connect them
     * Case-3: src and dest, both doesn't exist => Add both as vertex then connect them
     */
    public void addEdge(Edge<T> edge) {
        // Add source and destination of edge as individual vertices in the graph if
        // they are not already there
        this.addVertex(edge.src); // add source
        this.addVertex(edge.dest); // add destination

        // Adding the given edge in the edges list of source
        graph.get(edge.src).add(edge); // Generally: ArrayList<Edges<T>>.add(Edge<T>)
    }

    /*------Display All the neighbors of given vertex if it exists------- */
    public void findNbrs(T vertex) {
        if (!graph.containsKey(vertex)) {
            System.out.println(vertex + " is an invalid Vertex !");
            return;
        }

        for (Edge<T> edge : graph.get(vertex)) {
            // System.out.println(edge.src + " : " + edge.dest); // to display as an edge
            System.out.print(edge.dest + " ");
        }
        System.out.println();
    }

    // Below DFS and BFS are suitable if all the vertices are connected, i.e, graph
    // is fully connected

    /*-----------For BFS traversal - in connected graph------------------ */
    public void bfsCon(T start) {
        if (!graph.containsKey(start)) {
            System.out.println("Start Point doesn't exist !");
            return;
        }
        // boolean[] visited = new boolean[graph.size()]; // for generic type, using
        // index is a problem
        // That's why I'll be using a set
        Set<T> visited = new HashSet<>();

        Queue<T> queue = new LinkedList<>(); // using a queue, implemented by linked list
        // Add the start point in the queue
        queue.add(start);
        while (!queue.isEmpty()) {

            // Add the neighbours of current vertex
            T vertex = queue.poll(); // get the veretx from front of queue
            if (visited.contains(vertex)) {
                continue;
            }
            System.out.print(vertex + " ");
            visited.add(vertex);
            ArrayList<Edge<T>> edges = graph.get(vertex); // get all the edges connected to vertex
            // if (edges != null) {
            for (Edge<T> edge : edges) { // iterate over the edges
                queue.add(edge.dest); // the destination the edges will give the neighbors
            }
            // }
        }
        System.out.println();
    }

    /*-------------DFS Traversal in recursive way - for connected graph ----------- */
    /**
     * @param start
     * @param visited
     */
    public void dfsCon(T start, Set<T> visited) {
        // If any invalid start point is passed
        if (!graph.containsKey(start)) {
            System.out.println("Invalid Start point !");
            return;
        }
        // No need for these Base case
        // if (start == null) {
        // return;
        // }
        // if (visited.contains(start)) { // if current vertex is already visited
        // return;
        // }
        System.out.print(start + " ");
        visited.add(start);
        ArrayList<Edge<T>> edges = graph.get(start);
        for (Edge<T> edge : edges) {
            if (!visited.contains(edge.dest)) {
                dfsCon(edge.dest, visited); // recursive call for each non-visited neighbour
            }
        }
    }

    // Below DFS and BFS are suitable if all the vertices are not connected, i.e,
    // graph is not fully connected
    /*-------------DFS Traversal in recursive way - for general graph ----------- */
    /**
     * @param start
     * @param visited
     */
    public void dfs(T start, Set<T> visited) {
        if (!graph.containsKey(start)) {
            System.out.println("Invalid Start Point !");
            return;
        }
        // start the dfs traversal from given start point
        dfsCon(start, visited);

        // When control returns and there are some other vertices left
        for (T vertex : graph.keySet()) { // get all vertices
            if (!visited.contains(vertex)) { // start dfs from all those vertices which aren't visited yet
                dfsCon(vertex, visited); // call to dfs for non-visited vertices
            }
        }
    }

    /*-------------BFS Traversal - for general graph ----------- */
    public void bfs(T start) {
        if (!graph.containsKey(start)) {
            System.out.println("Invalid Start Point !");
            return;
        }
        Set<T> visited = new HashSet<>();
        bfsHelper(start, visited);
        for (T vertex : graph.keySet()) {
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
    private void bfsHelper(T start, Set<T> visited) {
        // if (!graph.containsKey(start)) {
        // System.out.println("Invalid Start Point !");
        // return;
        // }
        Queue<T> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            T vertex = queue.poll(); // remove vertex from front of queue
            if (!visited.contains(vertex)) {
                System.out.print(vertex + " "); // display the non-visited vertex
                visited.add(vertex); // mark it as visited now
                ArrayList<Edge<T>> edges = graph.get(vertex); // get edges connected to that vertex
                // Add its neighbours into the queue
                for (Edge<T> edge : edges) {
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
    public void printPath(T src, T dest) {
        // Some base conditions for validation of src and dest
        if (!graph.containsKey(src)) {
            System.out.println("Invalid Source !");
            return;
        }
        if (!graph.containsKey(dest)) {
            System.out.println("Invalid Destination !");
            return;
        }
        pathHelper(src, dest, new ArrayList<>(), new HashSet<>());
    }

    /*---------Recursive Method to support all paths finding---------- */
    /**
     * @param src
     * @param dest
     * @param path
     * @param visited
     */
    private void pathHelper(T src, T dest, ArrayList<T> path, Set<T> visited) {
        path.add(src);
        visited.add(src); // Correctly add src to visited here

        if (src.equals(dest)) {
            printPath(path);
            // return; // use return statement to print one path only
        } else {
            // DFS Traversal
            ArrayList<Edge<T>> edges = graph.get(src); // Get all edges directly connected to src
            for (Edge<T> edge : edges) { // Iterate over the neighbors by destination of these edges
                if (!visited.contains(edge.dest)) {
                    pathHelper(edge.dest, dest, path, visited);
                }
            }
        }

        // Backtrack
        path.remove(path.size() - 1); // Remove the last element added to the path
        visited.remove(src); // Remove src from visited after processing its neighbors
    }

    /*--------A private method to print the path containing vertices' ArrayList------ */
    private void printPath(ArrayList<T> vertices) {
        // for (T verex : vertices) {
        // System.out.print(verex + " --> ");
        // }
        System.out.print(vertices.get(0));
        for (int i = 1; i < vertices.size(); i++) {
            System.out.print(" --> " + vertices.get(i));
        }
        System.out.println();
    }

    /*---------To print Topological Sorted Order of graph------------ */
    /**
     * @param start
     * @return ArrayList of vertices in topological sorted order
     */
    public ArrayList<T> topologicalSort(T start) {
        /*
         * 1. Apply dfs on start edge - I have made a topoSortHelper() method that uses
         * modified dfs for
         * topo sort
         */
        Set<T> visited = new HashSet<>(); // to track visited vertices
        Stack<T> stack = new Stack<>(); // to store vertices in sorted order
        topoSortHelper(start, visited, stack); // dfs on start vertex
        // Get all vertices of the graph and apply dfs if any is not visited, (specially
        // for unconnected graph)
        for (T vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                topoSortHelper(vertex, visited, stack);
            }
        }
        // When all the vertices are visited, just pop all elements from the stack and
        // return it as an ArrayList
        ArrayList<T> vertices = new ArrayList<>();
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
    private void topoSortHelper(T start, Set<T> visited, Stack<T> stack) {
        visited.add(start);
        // Get neighbours of current start vertex
        for (Edge<T> edge : graph.get(start)) {
            if (!visited.contains(edge.dest)) {
                visited.add(edge.dest);
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
        // Get the vertices of graph
        Set<T> visited = new HashSet<>();
        for (T vertex : graph.keySet()) {
            System.out.println("Vertex: " + vertex);
            if (!visited.contains(vertex)) {
                if (cycleHelperUnd(vertex, visited, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*---------A private method to help dfs traversal for hasCycle()------- */
    /**
     * @param curr
     * @param visited
     * @param parent
     * @return boolean
     */
    private boolean cycleHelperUnd(T curr, Set<T> visited, T parent) {
        visited.add(curr);
        // System.out.println("Current: " + curr);
        // get all neighbours of the current vertex
        for (Edge<T> edge : graph.get(curr)) {
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
                // if false, check for next neighbour
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
        Set<T> visited = new HashSet<>();
        for (T vertex : graph.keySet()) {
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
     * @param stack   => recursion stack, to check if any vertex has been in the
     *                recursion stack already
     * @return boolean
     */
    private boolean cycleHelperDir(T curr, Set<T> visited, Set<T> stack) {

        visited.add(curr);
        stack.add(curr);
        // get all neighbours of current vertex
        for (Edge<T> edge : graph.get(curr)) { // edge.dest will be neighbour
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
     * @param source => Shortest Path finding from this single source to all
     *               vertices
     */
    public HashMap<T, Integer> getShortestPaths(T source) {
        // A graph holding distance from source to specific vertex
        HashMap<T, Integer> costs = new HashMap<>(); // keys : Vertices, Values :
        // Distance / cost to that vertex
        // get all vertices and add cost from source to destination as infinity,
        // execpt for source itself, because source to source cost is 0
        // Building the cost storage graph
        for (T vertex : graph.keySet()) { // get all vertices of graph
            if (vertex.equals(source)) {
                costs.put(source, 0); // source to souce cost is 0
            } else {
                costs.put(vertex, Integer.MAX_VALUE); // Integer.MAX_VALUE => +infinity
            }
        }
        /**
         * Create a priority queue to insert the key-value pair I've used my HmNode
         * class that has 2 attributes: key
         * and value I'll store vertex as a key and cost to that vertex as value, in the
         * HmNode This queue will
         * internally sort the nodes on the basis of shortest cost / distance means the
         * vertex having minimum cost
         * will be removed first
         */
        PriorityQueue<HmNode<T, Integer>> queue = new PriorityQueue<>(new Comparator<HmNode<T, Integer>>() {
            @Override
            public int compare(HmNode<T, Integer> entry1, HmNode<T, Integer> entry2) {
                return Integer.compare(entry1.value, entry2.value);
            }
        });
        queue.add(new HmNode<T, Integer>(source, 0));

        // A vertex will be marked visited if all of its neighbours' distance has been
        // updated
        Set<T> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            HmNode<T, Integer> node = queue.remove(); // remove the max priority (min cost) vertex
            T vertex = node.key;
            // Integer cost = node.value;

            // Get all neighbours of current vertex
            for (Edge<T> edge : graph.get(vertex)) { // edge.dest is neighbour
                if (!visited.contains(edge.dest)) { // for each unvisited neighbour
                    int srcCost = costs.get(vertex);
                    int destCost = costs.get(edge.dest);

                    // Check for Relaxation
                    if (srcCost + edge.wt < destCost) {
                        // update the destination's / neighbours cost
                        costs.put(edge.dest, srcCost + edge.wt);
                    }
                    queue.add(new HmNode<T, Integer>(edge.dest, costs.get(edge.dest)));// add neighbour and its cost
                }
            }
            visited.add(vertex);
        }
        return costs;
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println();

        /*-----------For Building a graph of String type----------*/
        Graph<String> graph = new Graph<>(); // initialize a graph with 5 vertices
        ArrayList<Edge<String>> list = new ArrayList<>();

        // See Cities graph from graph.txt file
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
        list.add(new Edge<String>("Lahore", "Quetta"));

        list.add(new Edge<String>("Karachi", "Lahore"));
        list.add(new Edge<String>("Karachi", "Quetta"));
        list.add(new Edge<String>("Karachi", "Islamabad"));

        list.add(new Edge<String>("Islamabad", "Karachi"));

        /*-----------For Building a graph of integer type----------*/
        // Graph<Integer> graph = new Graph<>(); // initialize a graph with 5 vertices
        // ArrayList<Edge<Integer>> list = new ArrayList<>();

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

        // list.add(new Edge<Integer>(6, 5));

        // For unconnected graph testing, these vertices are not connected to above ones
        // list.add(new Edge<Integer>(8, 9));
        // list.add(new Edge<Integer>(8, 10));

        // Building graph out of these edges
        graph.buildGraph(list);
        // Build connected graph, ensure the edges values are correct
        // graph.buildConGraph(list);

        System.out.print("Neighbours of Multan: ");
        graph.findNbrs("Multan");
        // graph.findNbrs("Quetta");
        // graph.findNbrs("Lahore");
        // graph.findNbrs("hogwards"); // invalid vertex

        System.out.println("Number of Vertices: " + graph.size());

        // System.out.println("Neighbours of 1"); // for integer type graph
        // graph.findNbrs(1);

        System.out.println("----------Connected---------");
        System.out.print("BFS Traversal: ");
        graph.bfsCon("Murree");
        // System.out.println();

        System.out.print("DFS Traversal: ");
        graph.dfsCon("Murree", new HashSet<>());
        System.out.println();

        // System.out.println("----------Unconnected---------");
        System.out.print("DFS: ");
        graph.dfs("Murree", new HashSet<>());
        // graph.dfs(0, new HashSet<>());
        System.out.println();

        System.out.print("BFS: ");
        graph.bfs("Murree");
        // graph.bfs(0);
        System.out.println();
        // Q: Print All paths from given source to destination
        System.out.println("\n----------All Paths from Multan to Murree are below---------");
        graph.printPath("Multan", "Murree");

    }
}
