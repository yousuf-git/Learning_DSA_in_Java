// File 1 - it contains methods that I used realted to singly linked list-----------*/

/**
 * This is generic type Singly Linked List class that contains basic linked list methods in order to understand its
 * implementation
 * 
 * @param T => Type of Data that will be stored in the nodes of linked list
 */
package singlyLL;

// LinkedList is already a class in JAVA so I used another name-----------*/
public class HSinglyLL<T> {
  private final String ANSI_RESET = "\u001B[0m";
  private final String ANSI_RED = "\u001B[31m";
  private final String ANSI_GREEN = "\u001B[32m";
  private final String ANSI_YELLOW = "\u001B[33m";

  // Each linked list has a head(First Node) and size
  private Node<T> head;
  private int size;

  public HSinglyLL() { // constructor
    this.size = 0;
  }

  /* --------Setter for head node--------- */
  public void setHead(Node<T> head) {
    this.head = head;
  }

  /* --------Getter for head node--------- */
  public Node<T> getHead() {
    return this.head;
  }

  /* --------Getter for list size--------- */
  public int getSize() {
    return this.size;
  }

  /* --------Returns true if list is empty--------- */
  public boolean isEmpty() {
    return head == null;
  }

  // Add Element / Node Method can be of 2 types => addFirst() and addLast()

  /* ---------- Adds element at the start -----------
   * Time Complexity: O(1) in worse canse - adjust pointers only
   */
  public boolean addFirst(T data) {
    // Validation
    if (data == null) {
      return false;
    }
    // Create a new node
    Node<T> newNode = new Node<>(data);

    // if list is empty, i.e., head = null
    if (isEmpty()) {
      // Head will point to the new node now
      head = newNode;
    } else {
      // first new node will point to existing head (node)
      newNode.next = head;
      // now head can be changed
      head = newNode;
    }
    this.size += 1;
    return true;
  }

  /* ---------- Adds element at the end -----------
   * Time Complexity: O(n)
   *   - can be optimized to O(1) if tail is maintained alongwith head
   */
  public boolean addLast(T data) {
    if (data == null) {
      return false;
    }
    Node<T> newNode = new Node<>(data);
    if (isEmpty()) {
      head = newNode;
    } else {
      // if we directly change the head, first item and hence list list will be lost,
      // thats why we use a currentNode and update it untill null is reached
      Node<T> currentNode = head;
      while (currentNode.next != null) {
        currentNode = currentNode.next;
      }
      currentNode.next = newNode;
    }
    this.size += 1;
    return true;
  }

  /* ---------- default add -> To add element at the end ----------- */
  public boolean add(T data) {
    return this.addLast(data);
  }

  /* ------------------To Add Element at a position------------------
   * Time Complexity: O(n) in worse case if position = size - 1
   */
  public void addAtIndex(int idx, T data) {
    // Validation of index
    if (idx >= this.size || idx < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      Node<T> newNode = new Node<>(data);
      // Case 1: addFirst()
      if (idx == 0) {
        newNode.next = head;
        head = newNode;
        size += 1;
      } else {
        Node<T> currNode = head;
        for (int i = 0; i < idx - 1; i++) {
          currNode = currNode.next;
        }
        Node<T> nextNode = currNode.next;
        currNode.next = newNode;
        newNode.next = nextNode;
        size += 1;
      }
    }
  }

  /* ---------- Removes element from start -----------
   * Time Complexity: O(1) - Adjust Pointers only
   */
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    } else {
      T item = head.data;
      head = head.next;
      this.size -= 1;
      return item;
    }
  }

  /* ---------- Removes element from the end -----------
   * Time Complexity: O(n) for worse case
   *    - Can be optimized to O(1) if tail pointer is maintained
   */
  public T removeLast() {
    if (isEmpty()) {
      return null;
    } else {
      T item;
      this.size -= 1;
      Node<T> currentNode = head;
      /******************** Wrong Approach *********************************/

      // while (currentNode.next != null) {
      // Update currentNode
      // currentNode = currentNode.next;
      // if (currentNode.next == null){ // if currentNode.next is null, it means that currentNode is last element
      // currentNode = null; // make the last element null
      // return;
      // }
      /*
       * currentNode is created here and making it null will not affect the elemnents
       * of linked list, we have to manipulate the pointer(next) actually
       */
      /******************************************************************/

      /******************** Right Approach *********************************/
      while (currentNode.next != null) {
        if (currentNode.next.next == null) {
          // if true it means currentNode.next is last element
          item = currentNode.next.data;
          currentNode.next = null; // make the last element null
          return item;
        }
        // update currentNode
        currentNode = currentNode.next;
      }
      // if head is the only element i.e., head.next == null then control will not
      // enter the loop
      item = head.data;
      head = null; // simply assign the single element (head) as null
      return item;
    }
  }

  /* ---------- default remove -> removes element from the end ----------- */
  public T remove() {
    return this.removeLast();
  }

  /* ------------------To Remove Element from a position------------------
   * Time Complexity: O(n)  if idx = size-1
   */
  public T removeFrom(int idx) {
    if (isEmpty() || idx >= this.size || idx < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      T oldData = this.get(idx);
      if (idx == 0) {
        head = head.next;
        size -= 1;
      } else {
        Node<T> currNode = head;
        for (int i = 0; i < idx - 1; i++) {
          currNode = currNode.next;
        }
        currNode.next = currNode.next.next;
        size -= 1;
      }
      return oldData;
    }
  }

  /* ---------- To Update Value at a specifc index -----------
   * Time Complexity: O(n)  
   */
  public T set(int idx, T val) {
    if (isEmpty() || idx >= size || idx < 0) {
      throw new IndexOutOfBoundsException();
      // System.out.println("Invalid Index <:( ");
    } else if (idx == 0) {
      T oldValue = removeFirst();
      addFirst(val);
      return oldValue;
    } else {
      Node<T> newNode = new Node<>(val);
      Node<T> currNode = head;
      // Goto 2nd last node till idx
      for (int i = 0; i < idx - 1; i++) {
        currNode = currNode.next;
      }
      // currNode.next is the node that is to be replaced
      T oldValue = currNode.next.data;
      newNode.next = currNode.next.next;
      currNode.next = newNode;
      return oldValue;
    }
  }

  /* ----------To display elements of list -----------
   * Time Complexity: O(n)  -Traversing whole list
   */
  public void display() {
    if (isEmpty()) {
      System.out.println("Nothing to display, List is Empty !");
    } else {
      Node<T> currentNode = head;
      while (currentNode != null) {
        System.out.print(currentNode.data + " --> ");
        currentNode = currentNode.next;
      }
      System.out.println("null");
    }
  }

  /* ---------- To Visually display nodes of list ----------- */
  public void newDisplay() {
    System.out.print(ANSI_GREEN);
    if (isEmpty()) {
      System.out.println(ANSI_RED + "null" + ANSI_RESET);
    } else {
      Node<T> currNode = head;
      while (currNode != null) {
        for (int i = 0; i < 6 + currNode.data.toString().length(); i++) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.print("_");
        }
        System.out.print("   ");
        currNode = currNode.next;
      }

      System.out.println();
      currNode = head;
      while (currNode != null) {
        try {
          Thread.sleep(150);
          System.out.print("│ ");
          Thread.sleep(150);
          System.out.print(ANSI_RESET + currNode.data + ANSI_GREEN);
          Thread.sleep(150);
          System.out.print(" │");
          Thread.sleep(150);
          System.out.print(" │");
          Thread.sleep(150);
          // System.out.print(" --> ");
          System.out.print(ANSI_YELLOW + " → " + ANSI_GREEN);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        currNode = currNode.next;
      }
      System.out.print(ANSI_RED + "null" + ANSI_GREEN);
      System.out.println();
      currNode = head;
      while (currNode != null) {
        for (int i = 0; i < 6 + currNode.data.toString().length(); i++) {
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.print("¯");
          // System.out.print("-"); use this if upper symbol is not supported by your
          // terminal
        }
        System.out.print("   ");
        currNode = currNode.next;
      }
      System.out.println(ANSI_RESET);
    }
  }

  /* ---------- To Search an item by linear search-----------
   * Time Complexity: O(n) in worse case if item is at the end
   */
  public int linearSearch(T item) {
    int idx = -1;
    if (isEmpty() || item == null) {
      return idx;
    } else {
      idx = 0;
      Node<T> currNode = head;
      while (currNode != null) {
        if (currNode.data.equals(item)) {
          return idx;
        }
        currNode = currNode.next;
        idx++;
      }
      return -1;
    }
  }

  /* ---------- To Sort items in list by Bubble Sort ----------- */
  public boolean bubbleSort() {
    if (isEmpty() || head.next == null) {
      return true;
    } else if (!(head.data instanceof Integer || head.data instanceof String)) {
      return false;
      // System.out.println("\nAlready Sorted :) ");
    } else {
      int prevItem, currItem;
      int count;
      for (int i = 0; i < size - 1; i++) {
        count = 1; // if swapping occurs we have to change head
        Node<T> prevNode = head;
        Node<T> currNode = head.next;
        Node<T> nextNode;
        int flag = 0; // either swapping occcurs or not, change the prevNode, start from head and step forward till flag (old prev is reached)

        // while (currNode != null) {
        for (int k = 0; k < size - i - 1; k++) {
          prevItem = Integer.parseInt(prevNode.data.toString()); // first convert the data type into string 
          // System.out.println("Previous Item : " + prevItem);
          currItem = Integer.parseInt(currNode.data.toString());
          // System.out.println("Current Item : " + currItem);
          nextNode = currNode.next;

          if (prevItem > currItem) {
            // swap prev and curr
            // prevNode.next = currNode.next;
            prevNode.next = nextNode;
            currNode.next = prevNode;

            // link previous list with curr
            if (count == 1) { // there is no previous list
              head = currNode;
            } else {
              Node<T> ptr = head;
              while (ptr.next != prevNode) {
                ptr = ptr.next;
              }
              ptr.next = currNode;
            }
          }
          // System.out.println();
          // display();
          count++;
          // update previous
          prevNode = head;
          for (int j = 0; j <= flag; j++) {
            prevNode = prevNode.next;
          }
          currNode = prevNode.next;
          flag++; // for keeping track of prev
        }
        // System.out.println("\nInner Ended");
      }
      // System.out.println("\nOuter Ended");
      return true;
    }
  }

  /* ----------Boolean Method to check if 2 linked lists are equal-----------
   * Time Complexity: O(n) - compare all nodes
   */
  public boolean isEqual(HSinglyLL<T> list) {
    // case 1 : if both lists are empty
    if (this.isEmpty() && list.isEmpty()) {
      return true;
    }
    // case 2: if their size varies
    else if (this.size != list.size) {
      return false;
    }
    // case 3: if both lists have same size and contain some data
    else {
      Node<T> currNode = this.head;
      Node<T> listNode = list.head;
      boolean flag = true; // equality indicator flag

      while (currNode != null && listNode != null) {
        if (currNode.data.equals(listNode.data)) {
          currNode = currNode.next;
          listNode = listNode.next;
        } else {
          // direct return statement can also be used
          flag = false;
          break;
        }
      }
      return flag;
    }
  }

  /* ----------Copies the current list into provided list----------- */
  public HSinglyLL<T> copyInto(HSinglyLL<T> list) {
    HSinglyLL<T> newList = new HSinglyLL<>();
    if (isEmpty()) {
      return new HSinglyLL<>();
    } else {
      Node<T> currNode = this.head;
      Node<T> newNode = currNode;
      while (currNode != null) {
        newList.addLast(currNode.data);
        newNode.next = currNode.next;
        currNode = currNode.next;
        newNode = currNode;
      }
      return newList;
    }
  }

  /* ----------Returns a new list that is reverse of original-----------
   * Time Complexity: O(n) - Traverse whole list
   */
  public HSinglyLL<T> reverse() {
    HSinglyLL<T> revList = new HSinglyLL<>();
    if (isEmpty()) {
      return revList;
    } else {
      revList = this.copyInto(revList);
      Node<T> prevNode = revList.head;
      Node<T> currNode = prevNode.next;
      while (currNode != null) {
        Node<T> nextNode = currNode.next;
        currNode.next = prevNode;
        prevNode = currNode;
        currNode = nextNode;
      }
      revList.head.next = null;
      revList.head = prevNode;

      return revList;
    }
  }

  /*
   * Returns middle Node of list => Hare - Turtle approach-----------
   * If only starting point is given, range will be till end
   * Time Complexity: O(n) - Traverse whole list
   */
  public Node<T> middle(Node<T> head) {
    if (isEmpty()) {
      return null; // if list is empty
    } else {
      Node<T> hare = head;
      Node<T> turtle = head;
      while (hare != null && hare.next != null) {
        hare = hare.next.next; // 2 steps
        turtle = turtle.next; // 1 step
      }
      return turtle;
    }
  }

  /* ---------Method 2 for middle: If start and end both are given-----------
   * Time Complexity: O(n) - Traverse whole list  
   */
  public Node<T> middle(Node<T> start, Node<T> end) {
    if (start == null) {
      return null;
    } else {
      Node<T> hare = start;
      Node<T> turtle = start;
      while (hare != end && hare.next != end) {
        hare = hare.next.next; // 2 stpes
        turtle = turtle.next; // 1 step
      }
      return turtle;
    }
  }

  // // Update Middle Mode --> ***********************Not
  // completed************************
  // public void setMiddle(Node middle) {
  // System.out.println("S: "+size);
  // // System.out.println(head.data);
  // // System.out.println(head.next.data);
  // // System.out.println(head.next.next.data);
  // if (head == null || head.next == null || head.next.next == null) {
  // System.out.println("hehe");
  // this.head = middle;
  // } else {
  // Node pointer = head;
  // for (int i = 0; i < (size/2)-1; i++) {
  // System.out.println(size);
  // pointer = pointer.next;
  // }
  // System.out.println(pointer.data);
  // pointer.next = middle;
  // }
  // }

  /*
   * --- Iterative Approach --- Returns the new head of reversed list from passed
   * head to end-----------
   * Can be attached with original list in order to save memory
   */
  public Node<T> iterativeReverse(Node<T> head) {
    if (head == null) { // for empty list
      System.out.println("List is Empty, Cannot be reversed");
      return null;
    } else if (head.next == null) { // in case of single element in list
      return head;
    } else {
      // I'll be using 3 pointers i.e., each time deal with 3 nodes in the list
      // In each iteration 2nd element will point to 1st element and 3rd element will
      // then become the 2nd element which will point backwards and so on untill null
      // is reached
      // Node prevNode = head; // 1st
      // Node currNode = head.next; // 2nd
      // while (currNode != null) { // untill reached null
      // Node nextNode = currNode.next; // 3rd
      // currNode.next = prevNode; // 2nd --> 1st

      // // update nodes/pointers
      // prevNode = currNode; // 1st = 2nd
      // currNode = nextNode; // 2nd = 3rd
      // }
      // // After loop, current is null which means prev is last element
      // head.next = null; // remove already connection of head
      // // update the head
      // head = prevNode;
      // return head;
      /*********************** In Simple way *******************************/
      Node<T> prevNode = null;
      Node<T> currNode = head;
      while (currNode != null) {
        Node<T> nextNode = currNode.next;
        currNode.next = prevNode;

        prevNode = currNode;
        currNode = nextNode;
      }
      return prevNode;
    }
  }

  /*---- Recursive Approach ---- Returns the new head of reversed list from passed head to end -----------
   * Can be attached with original list in order to save memory
   */
  public Node<T> recursiveReverse(Node<T> head) {
    // base case, for last element
    if (head == null || head.next == null) {
      return head;
    }
    // Recursive call that will return new head; when head.next == null it means
    // head is last element so it will be the new head that will be same returned to
    // all functions below. i.e., this call and return statements are connected
    Node<T> newHead = recursiveReverse(head.next);

    head.next.next = head; // if current head is 1st element then next of its next element should point to
                           // it (1st element) and so on....
                           // upper line can be divided as below 2 lines
                           // Node upperNode = head.next;
                           // upperNode.next = head;
    head.next = null; // upperNode will point to currNode/currHead and currHead will point to nothing
    return newHead;
  }

  /*----------- Returns true if there is any infinite loop/cycle in the list ---------*/
  public boolean hasCycle(Node<T> head) {
    if (head == null) {
      return false;
    } else {
      Node<T> hare = head;
      Node<T> turtle = head;
      while (hare.next != null && hare.next.next != null) {
        hare = hare.next.next;
        turtle = turtle.next;
        if (hare == turtle) {
          return true;
        }
      }
      return false;
    }
  }

  /*----------- Removes cycle / loop from list, if any ---------*/
  public void rmvCycle(Node<T> head) {
    if (!this.hasCycle(head)) {
      System.out.println("There is no cycle in the list to remove !");
    } else {
      Node<T> hare = head;
      Node<T> turtle = head;
      while (hare.next != null && hare.next.next != null) {
        hare = hare.next.next;
        turtle = turtle.next;
        if (hare == turtle) {
          // System.out.println("Meet At: " + hare.data);
          hare.next = null;
          break;
        }
      }
    }
  }

  /*----------- Merges/concats current list with provided one ---------*/
  public void merge(HSinglyLL<T> newList) {
    // if (head == null) {
    // head = newList.getHead();
    // size = newList.getSize();
    // return;
    // } else {
    /*
     * Part Below does merging but also changes the original list, and behaves diff
     * when sorted
     */
    // Node currNode = head;
    // while (currNode.next != null) {
    // currNode = currNode.next;
    // }
    // currNode.next = newList.getHead();
    // size += newList.getSize();
    // }
    /*
     * Another Approach, get data from each node of newList and add at last of
     * currList
     */
    Node<T> node = newList.getHead();
    while (node != null) {
      this.add(node.data);
      node = node.next;
    }
  }

  /* To check if list is palindrome, 121 = 121 */
  public boolean isPalindrome() {
    if (isEmpty() || head.next == null) {
      return true; // if list is empty, or there is 1 element
    } else {
      /*
       * -------- M1 - If you want to save memory use below approach but original list
       * will be modified--------
       */
      // // find middle
      // Node middle = this.middle(head);
      // System.out.println("Middle Value: " + middle.data);
      // // Reverse the 2nd half
      // middle.next = iterativeReverse(middle.next);
      // // Compare 1st half / left side with 2nd half / right side
      // Node leftNode = head;
      // Node rightNode = middle.next;
      // while (rightNode != null) {
      // if (!leftNode.data.equals(rightNode.data)) {
      // return false;
      // }
      // leftNode = leftNode.next;
      // rightNode = rightNode.next;
      // }
      // return true;
      /*
       * ---------------- M2 - A new list will have to be created, but original will
       * not be affected----------
       */
      return this.isEqual(reverse());

    }
  }

  /* To get SUM of a list, list must be integer type */
  public int getSum() {
    int sum = 0;
    Node<T> currNode = head;
    while (currNode != null) {
      sum += Integer.parseInt(currNode.data.toString());
      currNode = currNode.next;
    }
    return sum;
  }

  /*--------- get value from a specific index----------
   * Time Complexity: O(n) in worse case if idx = size - 1
  */
  public T get(int idx) {
    if (isEmpty() || idx < 0 || idx >= size) {
      throw new IndexOutOfBoundsException();
    } else {
      Node<T> currNode = head;
      for (int i = 0; i < idx; i++) {
        currNode = currNode.next;
      }
      return currNode.data;
    }
  }

  /*--------- get index of given value / item ----------
   * Time Complexity: O(n) in worse case if item is at the end
  */
  public int get(T item) {
    return linearSearch(item);
  }


  public static void main(String[] args) {
    HSinglyLL<String> list = new HSinglyLL<>();
    // list.add("nth");
    // list.add("Sehri");
    // list.add("Done");
    // list.add("Alhamdulillah");
    // list.add(":)");
    list.add("12");
    list.add("2");
    list.add("34");
    list.add("4"); // mid
    list.add("1");
    list.add("69");
    list.add("0");
    list.add("-1");
    System.out.print("\nOriginal List: ");
    // list.newDisplay();
    list.display();
    list.set(0, "69");
    System.out.print("Updated List: ");
    list.display();

    // calculate mid of 2 --> 34 --> 4 --> 1

    // Node midNode = list.middle(list.getHead().next, list.middle(list.getHead()).next);
    // System.out.println(midNode.data);

    // list.bubbleSort();
    // list.display();
    // list.newDisplay();

    // String testStr = "harry";
    // System.out.println(testStr);
    // try {
    // Thread.sleep(2000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // System.out.println(testStr.length());

  }
}
/********************* Object References Testing **************************/
// class A {
// int a;
// A() {
// a = 9;
// }
// public static void main(String[] args) {
// A obj1 = new A();
// A obj2;
// A obj3;
// obj2 = obj3 = obj1;
// System.out.println("Address of obj1 = " + obj1);
// System.out.println("Address of obj2 = " + obj2);
// System.out.println("Address of obj3 = " + obj3);
// System.out.println("a from obj1 = " + obj1.a);
// System.out.println("a from obj2 = " + obj2.a);
// System.out.println("a from obj3 = " + obj3.a);
// }
// }
