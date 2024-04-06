// File 3
// This implementation is also included in HSinglyLL class which is generic amd contains all methods of singly ////linked list, however to understand this specific method separately, you can consider it.
package singlyLL;

/* Reverse a linked list
 * Method 1
    Create a new linked list => additional space
    Space Complexity => O(n)

 * Method 2 (that I've done here)
    Don't use additional space => just modify the connections and head
    Space Complexity => O(1)
 */
// I've again created linkedList class but you can use previous one bcz we are in the same pkg

class LL<T> {

  private Node<T> head;

  public Node<T> getHead() {
    return this.head;
  }

  public void setHead(Node<T> head) {
    this.head = head;
  }

  public void add(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
    } else {
      Node<T> currNode = head;
      while (currNode.next != null) {
        currNode = currNode.next;
      }
      currNode.next = newNode;
    }
  }

  public void displayList() {
    if (head == null) {
      System.out.println("Linked List is Empty !");
    } else {
      Node<T> curNode = head;
      while (curNode != null) {
        System.out.print(curNode.data + " --> ");
        curNode = curNode.next;
      }
      System.out.println("null");
    }
  }

  /************ Reverse a linked list using iterative approach ************/
  public void iterativeReverse() {
    if (head == null) { // for empty list
      System.out.println("List is Empty, Cannot be reversed");
    } else if (head.next == null) { // in case of single element in list
      return;
    } else {
      // I'll be using 3 pointers i.e., each time deal with 3 nodes in the list
      // In each iteration 2nd element will point to 1st element and 3rd element will then become the 2nd element which will point backwards and so on untill null is reached
      Node<T> prevNode = head; // 1st
      Node<T> currNode = head.next; // 2nd
      while (currNode != null) { // untill reached null
        Node<T> nextNode = currNode.next; // 3rd
        currNode.next = prevNode; // 2nd --> 1st

        // update nodes/pointers
        prevNode = currNode; // 1st = 2nd
        currNode = nextNode; // 2nd = 3rd
      }
      // After loop, current is null which means prev is last element
      head.next = null; // remove already connection of head
      // update the head
      head = prevNode;
    }
  }

  /************ Reverse a link list by recursive approach ************/
  public Node<T> recursiveReverse(Node<T> head) {
    // base case, for last element
    if (head == null || head.next == null) {
      return head;
    }
    // Recursive call that will return new head, when head.next == null it means head is last element so it will be the new head that will be same returned to all functions below. i.e., this call and return statements are connected
    Node<T> newHead = recursiveReverse(head.next);

    head.next.next = head; // if current head is 1st element then next of its next element should point to it (1st element)
    // upper line can be divided as below 2 lines
    // Node upperNode = head.next;
    // upperNode.next = head;

    head.next = null;
    return newHead;
  }
}

public class ReverseLL {

  public static void main(String[] args) {
    LL<Integer> list = new LL<>();
    // list.add("hi");
    // list.add("harry");
    // list.add("Welcome !");

    list.add(1);
    list.add(3);
    list.add(2);
    list.add(1);
    System.out.print("Original List: ");
    list.displayList();
    System.out.println();
    
    list.iterativeReverse();
    System.out.print("Reversed List: ");
    list.displayList();
    // list.setHead(list.recursiveReverse(list.getHead()));
    // list.displayList();
  }
}
