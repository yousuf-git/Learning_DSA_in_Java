// File 4
package singlyLL;

import java.util.Scanner;

public class LLQuestions {

  public static Node<String> delN(int n, int size, Node<String> head) {
    if (n > size || n < 1) {
      System.out.println("Invalid n !");
      return head;
    } else if (head == null || head.next == null) {
      System.out.println("nth last element: " + head.data);
      return null;
    } else if (n == size) {
      System.out.println("nth last element: " + head.data);
      return head.next;
    } else {
      // Node prevNode = head;
      // Node currNode = head.next;
      Node<String> prevNode = head;
      // if we want to delete 4th node we need 3rd node and change its next
      for (int i = 1; i < size - n; i++) {
        prevNode = prevNode.next;
      }
      // prevNode.next means nth node
      System.out.println("nth last element: " + prevNode.next.data);
      prevNode.next = prevNode.next.next; // n-1 will point to n+1
      return head;
    }
  }

  public static boolean isPalindrome(HSinglyLL<String> list) {
    Node<String> head = list.getHead();
    if (head == null || head.next == null) {
      return true;
    } else {
      // find middle
      Node<String> middle = list.middle(head);
      System.out.println("Middle Value: " + middle.data);
      // Reverse the 2nd half
      middle.next = list.iterativeReverse(middle.next);
      list.display();

      // Compare 1st half / left side with 2nd half / right side
      Node<String> leftNode = head;
      Node<String> rightNode = middle.next;

      while (rightNode != null) {
        if (!leftNode.data.equals(rightNode.data)) {
          return false;
        }
        leftNode = leftNode.next;
        rightNode = rightNode.next;
      }
      return true;
    }
  }

  public static void reorderList(Node<String> head, Node<String> mid, int size) {
    if (head == null) {
      System.out.println("List is Empty !");
    }
    Node<String> currPtr = head;
    Node<String> nextPtr = mid.next;
    Node<String> currNode, nextNode;
    if (size % 2 != 0) {
      while (nextPtr != null) {
        currNode = currPtr;
        nextNode = nextPtr;

        nextPtr = nextPtr.next;
        currPtr = currPtr.next;

        nextNode.next = currNode.next;
        currNode.next = nextNode;
      }
      currPtr.next = null;
    } else {
      while (nextPtr.next != null) {
        currNode = currPtr;
        nextNode = nextPtr;

        nextPtr = nextPtr.next;
        currPtr = currPtr.next;

        nextNode.next = currNode.next;
        currNode.next = nextNode;
      }
      currPtr.next = nextPtr;
    }

  }

  public static void reorderList(Node<String> lHead, Node<String> rHead) {
    Node<String> lh = lHead;
    Node<String> rh = rHead;
    Node<String> lnext;
    Node<String> rnext;

    while (lh != null && rh != null) {
      lnext = lh.next;
      lh.next = rh;
      
      rnext = rh.next;
      rh.next = lnext;
      
      lh = lnext;
      rh = rnext;
      
    }
  }

  public static void main(String[] args) {
    /*
     * Q1: Find nth node from last and delete it
     * if n = 2 it means 2nd last node and so on....
     */
    HSinglyLL<String> list = new HSinglyLL<>();
    list.addFirst("11");
    list.addFirst("9");
    list.addFirst("7");
    list.addFirst("5");
    list.addFirst("3");
    System.out.print("Initial List: ");
    list.display();

    Scanner input = new Scanner(System.in);
    // System.out.print("\nEnter Value of n: ");
    // int n = input.nextInt();
    int n = 2;
    list.setHead(delN(n, list.getSize(), list.getHead()));
    System.out.print("\nList after deleting nth last element: ");
    list.display();

    input.close();

    /**
     * Q2: Check if a linked list is palindrome
     * 313, 1221, bob, LOL,.. is palindrom i.e., reverse = original
     * Methods:
     * 1. Store data of list in an array/arrayList then check if array/arrayList is
     * palindrome or not
     * 2. Create a new reversed linkedList and then compare it with original
     * 3. Follow the 3 steps (Also avoids extra space)
     * Suppose list is: 1 2 2 1
     * a. Find Middle of linked list --> 2
     * b. Reverse the list after middle --> 1 2 1 2
     * c. Compare data/values of 1st half with 2nd half --> 1==1, 2==2
     */

    // By Method 2
    HSinglyLL<String> palList = new HSinglyLL<>();
    palList.add("3");
    palList.add("1");
    palList.add("1");
    palList.add("3");
    System.out.println("\nList to check Palindrome: ");
    palList.display();

    if (palList.isEqual(palList.reverse())) {
      System.out.println("List is Palindrome");
    } else {
      System.out.println("List is Not Palindrome");
    }

    // By Method 3
    if (isPalindrome(palList)) {
      System.out.println("Yes");
    } else {
      System.out.println("NO");
    }
    /**
     * Q3: Reorder given List
     * Input: 1 --> 2 --> 3 --> 4 --> 5
     * Output: 1 --> 5 --> 2 --> 4 --> 3
     */
    HSinglyLL<String> inputList = new HSinglyLL<>();
    inputList.add("1");
    inputList.add("2");
    inputList.add("3");
    inputList.add("4");
    inputList.add("5");
    inputList.add("6");
    System.out.print("Input List: ");
    inputList.display();
    // inputList.middle(inputList.getHead()).next = inputList.recursiveReverse(inputList.middle(inputList.getHead()).next);
    // reorderList(inputList.getHead(), inputList.middle(inputList.getHead()), inputList.getSize());

    // Step 1: reverse the second half of list
    Node<String> rHead = inputList.recursiveReverse(inputList.middle(inputList.getHead()));
    Node<String> lHead = inputList.getHead();
    // System.out.println(inputList.middle(inputList.getHead()).data);
    inputList.middle(inputList.getHead()).next = null; // divide list into 2 lists
    reorderList(lHead, rHead);
    
    
    System.out.print("Reordered List: ");
    inputList.display();

    // display(rHead);


  }

  public static void display(Node<String> head) {
    if (head == null) {
      System.out.println("null");
      return;
    }
    Node<String> curr = head;
    while (curr != null) {
      System.out.print(curr.data + " --> ");
      curr = curr.next;
    }
    System.out.println("null");
  }
}
