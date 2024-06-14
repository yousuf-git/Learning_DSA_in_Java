package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * I've implemented graph in this class
 * Fully connected and unconnected, directed and undirected graphs are handled in this single class in a general way
 * You just have to ensure that you are passing the right edges while building the graph
 * Implemented some methods both for connected and un-connected graphs separately, 
 * in order to let you understand the difference between them
 * General methods work well for both connected and unconncted graph so try to use them in most cases
 * 
 * Contains following methods:
 * 
 * 1. buildConGraph(ArrayList<Edge<T>> edges) : Specially for connected graphs building with list of edges
 * 2. buildGraph(ArrayList<Edge<T>> edges) : For general connected / unconnected graphs building
 * 3. addVertex(T vertex) : To add a vertex of generic type
 * 4. addEdge(Edges<T> edge) : To add an edge and ensure that source and destination are themselves added as vertex
 * 5. findNbrs(T vertex) : To Display All the neighbours of a given vertex
 * 6. bfsCon(T start) / dfsCon(T start, Set<T> visited) : Special for traversal in connected graphs
 * 7. bfs(T start) / dfs(T start, Set<T> visited) : Traversal in general graphs
 * 8. printPath(T src, T dest) : To print all possible paths from given source to destination vertex
 */

public class Graph<T> {
    int V; // number of vertices
    // A HashMap where key is vertex and value is an ArrayList of all edges directly
    // connected to it
    HashMap<T, ArrayList<Edge<T>>> graph;

    // constructor
    // @param
    // V => Number of Vertices

    Graph() {
        graph = new HashMap<>();
        this.V = 0;
    }

    // Graph(int V) {
    // graph = new HashMap<>();
    // this.V = V;
    // }

    /*---------------Build A fully connected graph by list of edges---------------- */
    public void buildConGraph(ArrayList<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            T src = edge.src;
            // // check if src exist in the Map as a key or not
            // if (!graph.containsKey(src)) {
            // graph.put(src, new ArrayList<>()); // if not exists, add an empty array list
            // as its value
            // }
            // The above if statement for adding new key, can be replaced by:
            if (graph.putIfAbsent(src, new ArrayList<>()) == null) { // returns valid value if key exists already
                V++; // if null is returned, it means key / vertex is newly added
            }
            graph.get(src).add(edge); // get the vertex and attach new edge with it
        }
    }

    /*---------------Build An un-connected graph by list of edges---------------- */
    public void buildGraph(ArrayList<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            T src = edge.src;
            T dest = edge.dest;
            // // check if src exist in the Map as a key or not
            // if (!graph.containsKey(src)) {
            // graph.put(src, new ArrayList<>()); // if not exists, add an empty array list
            // as its value
            // }
            // The above if statement for adding new key, can be replaced by:
            if (graph.putIfAbsent(src, new ArrayList<>()) == null) { // returns valid value if key exists already
                V++; // if null is returned, it means key / vertex is newly added
            }
            if (graph.putIfAbsent(dest, new ArrayList<>()) == null) { // returns valid value if key exists already
                V++; // if null is returned, it means key / vertex is newly added
            }
            graph.get(src).add(edge); // get the vertex and attach new edge with it
        }
    }

    /*------------------Add a new vertex in graph---------- */
    public void addVertex(T vertex) {
        graph.putIfAbsent(vertex, new ArrayList<>()); // add the vertex as a key, if it doesn't exist already
    }

    /*----------Add a new edge in the vertex---------
     * Case-1: src and dest both exist in the graph => connect them
     * Case-2: src exists but dest doesn't => First add the dest as a vertex then connect them
     * Case-3: src and dest, both doesn't exist => Add both as vertex then connect them
     */
    public void addEdge(Edge<T> edge) {
        // Case-1
        if (graph.containsKey(edge.src) && graph.containsKey(edge.dest)) {
            graph.get(edge.src).add(edge); // connect them, by adding vertex in the list of vertices of src
        }
        // Case-2
        else if (graph.containsKey(edge.src)) { // means dest doesn't exist
            graph.put(edge.dest, new ArrayList<>()); // add the dest as vertex
            graph.get(edge.src).add(edge); // connect them, by adding vertex in the list of vertices of src
        }
        // Case-3
        else { // src doesn't exist
            graph.put(edge.src, new ArrayList<>()); // add the src as vertex
            graph.get(edge.src).add(edge); // connect them by updating list of vertices
        }
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
        // boolean[] visited = new boolean[graph.size()]; // for generic type using
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
        // ArrayList<Edge<T>> edges = graph.get(src); // get all edges directly connected to src
        // for (Edge<T> edge : edges) { // grab the neigbous by destination of these edges
        //     ArrayList<T> path = new ArrayList<>();
        //     path.add(src);
        //     Set<T> visited = new HashSet<>();
        //     visited.add(src);
        //     pathHelper(edge.dest, dest, path, visited);
        // }
    }

    // public void pathHelper(T src, T dest, ArrayList<T> path, Set<T> visited) {
    //     path.add(src);
    //     if (src.equals(dest)) {
    //         printPath(path);
    //         // path.remove(src);
    //         return;
    //         // return true;
    //     }
    //     ArrayList<Edge<T>> edges = graph.get(src); // get all edges directly connected to src
    //     for (Edge<T> edge : edges) { // grab the neigbous by destination of these edges
    //         if (!visited.contains(edge.dest)) {
    //             visited.add(src);
    //             pathHelper(edge.dest, dest, path, visited);
    //             visited.remove(src);
    //         }
    //     }
    //     // return false;
    // }
    private void pathHelper(T src, T dest, ArrayList<T> path, Set<T> visited) {
        path.add(src);
        visited.add(src);  // Correctly add src to visited here
    
        if (src.equals(dest)) {
            printPath(path);
            // if you want to find all paths, you can remove the return statement
            // return;
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
        visited.remove(src);  // Remove src from visited after processing its neighbors
    }
    

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
