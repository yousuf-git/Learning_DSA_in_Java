package doublyLL;

public class Node<T> {
    Node<T> next, prev;
    T data;
    Node(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }   
}
