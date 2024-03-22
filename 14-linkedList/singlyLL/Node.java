package singlyLL;

// There are nodes in a Linked List, so I'm using a class for it-----------*/
public class Node {

  // Node has 2 things => data and next node
  public String data;
  public Node next; // same class bcz next node also has data + next

  // Parameterized Constructor that will create a single node initiallly-----------*/
  Node(String data) {
    this.data = data;
    // whenever a node is created its pointer is null, assume that it is the last node
    this.next = null;
  }
}
