package graph;
// Edge class for graph that contains the information of source and destination which are genric type
public class Edge<T> {
    T src;
    T dest;

    Edge(T src, T dest) {
        this.src = src;
        this.dest = dest;
    }
}
