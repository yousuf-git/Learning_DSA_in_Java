package singlyLL;

/**
 * There are nodes in a Linked List, so I'm using a class for it
 * @param T => Type of data that will be stored in Node 
 */ 

public class Node <T> {

  // Node has 2 things => data and next node
  public T data;
  public Node<T> next; // same class bcz next node also has data + next

  // Parameterized Constructor that will create a single node initiallly-----------*/
  public Node(T data) {
    this.data = data;
    // whenever a node is created its pointer is null, assume that it is the last node
    this.next = null;
  }
}
