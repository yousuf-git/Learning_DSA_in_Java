/* Edge class for graph that contains the information of source and destination which are genric type
 * If graph is weighted, edge has wt attribute that will be utilized, otherwise it will null
*/

package graph;

public class Edge<T> implements Comparable<Edge<T>> {
    public T src;
    public T dest;
    public Integer wt;

    /**
     * Construtor for unweighted edge
     * 
     * @param src  => source of edge
     * @param dest => destination of edge
     */
    public Edge(T src, T dest) {
        this.src = src;
        this.dest = dest;
        this.wt = null;
    }

    /**
     * Construtor for weighted edge
     * 
     * @param src
     * @param dest
     * @param wt   => weight / cost of edge
     */
    public Edge(T src, T dest, int wt) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }

    // To Compare any 2 edges based on their weights, here less weight edge will be considered as smaller
    @Override
    public int compareTo(Edge<T> edge) {
        return this.wt - edge.wt;
        // if it returns +ve value, it means current edge is greater than provided one
        // if it returns -ve value, it means current edge is smaller than provided one
        // if it returns zero, it means current edge is equal to provided one
    }
}
