// File 4
package singlyLL;

import java.util.Scanner;

public class LLQuestions {

  public static Node delN(int n, int size, Node head) {
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
      //   Node prevNode = head;
      //   Node currNode = head.next;
      Node prevNode = head;
      //   if we want to delete 4th node we need 3rd node and change its next
      for (int i = 1; i < size - n; i++) {
        prevNode = prevNode.next;
      }
      //   prevNode.next means nth node
      System.out.println("nth last element: " + prevNode.next.data);
      prevNode.next = prevNode.next.next; // n-1 will point to n+1
      return head;
    }
  }

  public static boolean isPalindrome(HSinglyLL list) {
    Node head = list.getHead();
    if (head == null || head.next == null) {
      return true;
    } else {
      // find middle
      Node middle = list.middle(head);
      System.out.println("Middle Value: " + middle.data);
      // Reverse the 2nd half
      middle.next = list.iterativeReverse(middle.next);
      list.display();

      // Compare 1st half / left side with 2nd half / right side
      Node leftNode = head;
      Node rightNode = middle.next;

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

  public static void main(String[] args) {
    /*Q1: Find nth node from last and delete it
     * if n = 2 it means 2nd last node and so on....
     */
    HSinglyLL list = new HSinglyLL();
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

    /*Q2: Check if a linked list is palindrome
     * 313, 1221, bob, LOL,..  is palindrom i.e., reverse = original
     * Methods:
     *  1. Store data of list in an array/arrayList then check if array/arrayList is palindrome or not
     *  2. Create a new reversed linkedList and then compare it with original
     *  3. Follow the 3 steps (Also avoids extra space)
     *      Suppose list is: 1 2 2 1
     *      a.  Find Middle of linked list --> 2
     *      b.  Reverse the list after middle --> 1 2 1 2
     *      c.  Compare data/values of 1st half with 2nd half --> 1==1, 2==2
     */

    // By Method 2
    HSinglyLL palList = new HSinglyLL();
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
  }
}
