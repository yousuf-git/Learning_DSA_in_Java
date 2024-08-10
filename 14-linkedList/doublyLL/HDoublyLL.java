// File 1 - it contains methods that I used realted to singly linked list-----------*/
package doublyLL;

// LinkedList is already a class in JAVA so I used another name-----------*/
public class HDoublyLL<T> {
  public final String ANSI_RESET = "\u001B[0m";
  public final String ANSI_RED = "\u001B[31m";
  public final String ANSI_GREEN = "\u001B[32m";
  public final String ANSI_YELLOW = "\u001B[33m";

  // Each linked list has a head(First Node) and size
  private Node<T> head; // this is first node of doubly Linked List 
  private Node<T> tail; // this is last node of doubly Linked List 
  private int size;

  public HDoublyLL() { // constructor
    this.size = 0;
  }

  /* --------Setter for first node--------- */
  public void setHead(Node<T> head) {
    this.head = head;
  }

  /* --------Getter for first node--------- */
  public Node<T> getHead() {
    return this.head;
  }
  /* --------Setter for last node--------- */
  public void setTail(Node<T> tail) {
    this.tail = tail;
  }

  /* --------Getter for last node--------- */
  public Node<T> getTail() {
    return this.tail;
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
   * Time Complexity: O(1)
   */
  public void addFirst(T data) {
    // Create a new node
    Node<T> newNode = new Node<>(data);

    // if list is empty, i.e., head = null
    if (isEmpty()) {
      // if list is empty create a new node and assume it is first and last node
      head = newNode;
      tail = newNode;
    } else {
      // first new node will point to existing head (node)
      newNode.next = head;
      head.prev = newNode;
      // now head can be changed
      head = newNode;
    }
    this.size += 1;
  }

  /* ---------- Adds element at the end -----------
   * Time Complexity: O(1)
   */
  public void addLast(T data) {
    Node<T> newNode = new Node<>(data);
    if (isEmpty()) { // if list is empty create a new node and assume it is first and last node
      head = newNode;
      tail = newNode;
    } else {
      // if we directly change the head, first item and hence list list will be lost,
      // thats why we use a currentNode and update it untill null is reached
      // Approach 1, if we don't use tail pointer ->  O(n)
      // Node<T> currentNode = head;
      // while (currentNode.next != null) {
      //   currentNode = currentNode.next;
      // }
      // currentNode.next = newNode;
      // newNode.prev = currentNode;
      // tail = newNode;

      // Approach 2: using tail pointer -> O(1)
      tail.next = newNode; // attach new node at the end
      newNode.prev = tail; // set its previous to old tail
      tail = newNode; // update tail
    }
    this.size += 1;
  }

  /* ---------- default add -> To add element at the end ----------- */
  public void add(T data) {
    this.addLast(data);
  }

  /*----------- Pushes element to start - if LL represents a stack */
  public void push(T data) {
    addFirst(data);
  }

  /* ------------------To Add Element at a specific valid index------------------
   * Time Complexity: O(n) in worse case if n = size - 1
   */
  public void addAtIdx(int idx, T data) {
    if (idx >= this.size || idx < 0) { // if size = 5 => valid indices are 0 to 4
      throw new IndexOutOfBoundsException();
      // System.out.println("Invalid Index :<( ");
    } else {
      if (idx == 0) { // addFirst()
        addFirst(data);
        // System.out.println("Item " + data + " added at index " + idx + " :)");
      } else {
        Node<T> newNode = new Node<>(data);
        Node<T> currNode = head;
        for (int i = 1; i < idx; i++) {
          currNode = currNode.next;
        }
        // now current.next is the actual node that is to be shifted right
        newNode.next = currNode.next; // link: newNode --> existing node at that index
        currNode.next.prev = newNode; // link: existingNode.prev --> newNode
        currNode.next = newNode; // newNode is now the at the place of existing node
        newNode.prev = currNode; // attach new Node with prev list
        // System.out.println("Item " + data + " added at index " + idx + " :)");
        size += 1;
      }
    }
  }

  /* ---------- Removes element from start and returns it - O(1) ----------- */
  public T delFirst() {
    if (isEmpty()) {
      return null;
    } else {
      T item = head.data; // retrieve item from old head
      head = head.next; // shift the head to next
      head.prev = null; // there is no element before head
      this.size -= 1;
      return item;
    }
  }

  /* ---------- Removes element from the end and returns it: O(1) ----------- */
  public T delLast() {
    if (isEmpty()) {
      return null;
    } else {
      T item;
      if (head.next == null) { // if there is single element
        item = head.data;
        head = tail =  null; // simply assign the single element (head/tail) as null
        this.size -= 1;
        return item;
      } // To Revise delLast of SLL
      /**
       * 1. Iterate and goto 2nd last node
       * 2. Retrieve data from 2ndLast.next (last node)
       * 3. make next of 2nd last node --> null
       */
      // while (currentNode.next.next != null) {
      //   // update currentNode
      //   currentNode = currentNode.next;
      // }
      // // currentNode is 2nd last element now
      // item = currentNode.next.data;
      // currentNode.next = null;
      
      // If we use this approach for even single element in the list, step 3 will throw NullPointerException
      item = tail.data; // get data from last node
      tail = tail.prev; // update last node
      tail.next = null; // next of last node is always null in DLL
      this.size -= 1;
      return item;
    }
  }

  /* ---------- default remove -> removes element from the end and returns it----------- */
  public T remove() {
    return this.delLast();
  }

  /*------- To remove element from top (first) if LL is satck ---------*/ 
  public T pop() {
    return delFirst();
  }

  /* ------------------To Remove Element from a position: O(n) ------------------ */
  public T delFromIdx(int idx) {
    if (isEmpty()) {
      // System.out.println("Cannot Remove, List is Empty :( ");
      return null;
    } else {
      if (idx >= this.size || idx < 0) {
        // System.out.println("Invalid Index <:( ");
        throw new IndexOutOfBoundsException();
      } else {
        if (idx == 0) { // delFirst
          return delFirst();
        } else {
          T data;
          Node<T> currNode = head;
          for (int i = 0; i < idx - 1; i++) { // 1 to idx   ||   0 to idx-1
            currNode = currNode.next;
          }
          // currNode.next is the node that is to be removed
          data = currNode.next.data; // retrieve its data first to return 
          currNode.next = currNode.next.next;
          currNode.next.prev = currNode;
          // System.out.println("Item " + data + " removed from index " + idx + " :)");
          size -= 1;
          return data;
        }
      }
    }
  }

  /* ---------- To Update Value at a specifc index: O(n) ----------- */
  public void set(int idx, T val) {
    if (idx >= size || idx < 0) {
      // System.out.println("Invalid Index <:( ");
      throw new IndexOutOfBoundsException();
    } else if (idx == 0) {
      head.data = val;
    } else {
      Node<T> newNode = new Node<>(val);
      Node<T> currNode = head;
      for (int i = 0; i < idx - 1; i++) {
        currNode = currNode.next;
      }
      newNode.next = currNode.next.next;
      currNode.next = newNode;
    }
  }
  /* ---------- To get Value from a specifc index: O(n)----------- */
  public T get(int idx) {
    if (idx >= size || idx < 0) {
      // System.out.println("Invalid Index <:( ");
      throw new IndexOutOfBoundsException();
    } else if (idx == 0) {
      return head.data;
    } else {
      Node<T> currNode = head;
      for (int i = 0; i < idx; i++) {
        currNode = currNode.next;
      }
      return currNode.data;
    }
  }


  /* ----------To display elements of list ----------- */
  public void display() {
    if (isEmpty()) {
      System.out.println("Nothing to display, List is Empty !");
    } else {
      Node<T> currentNode = head;
      while (currentNode != null) {
        System.out.print(currentNode.data + " <--> ");
        // System.out.print(currentNode.data +" ⟺  "); // use upper one if this is not working
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
        System.out.print("    ");
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
          System.out.print(ANSI_YELLOW + " <-->  " + ANSI_GREEN);
          // System.out.print(ANSI_YELLOW + " ⟺  " + ANSI_GREEN);
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
          // System.out.print("-"); use this if upper symbol is not supported by your terminal
        }
        System.out.print("    ");
        currNode = currNode.next;
      }
      System.out.println(ANSI_RESET);
    }
  }

  /* ---------- To Search an item by linear search: O(n) ----------- */
  public int linearSearch(String item) {
    int idx = -1;
    if (isEmpty()) {
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

  // /* ---------- To Sort items in list by Bubble Sort ----------- */
  // public void bubbleSort() {
  //   if (isEmpty()) {
  //     System.out.println("\nCannot Sort, List is Empty :( ");
  //   } else if (head.next == null) {
  //     System.out.println("\nAlready Sorted :) ");
  //   } else {
  //     int prevItem, currItem;
  //     int count;
  //     for (int i = 0; i < size - 1; i++) {
  //       count = 1; // if swapping occurs we have to change head
  //       Node prevNode = head;
  //       Node currNode = head.next;
  //       Node nextNode;
  //       int flag = 0; // either swapping occcurs or not, change the prevNode, start from head and step
  //                     // forward till flag (old prev is reached)

  //       // while (currNode != null) {
  //       for (int k = 0; k < size - i - 1; k++) {
  //         prevItem = Integer.parseInt(prevNode.data);
  //         // System.out.println("Previous Item : " + prevItem);
  //         currItem = Integer.parseInt(currNode.data);
  //         // System.out.println("Current Item : " + currItem);
  //         nextNode = currNode.next;

  //         if (prevItem > currItem) {
  //           // swap prev and curr
  //           // prevNode.next = currNode.next;
  //           prevNode.next = nextNode;
  //           currNode.next = prevNode;

  //           // link previous list with curr
  //           if (count == 1) { // there is no previous list
  //             head = currNode;
  //           } else {
  //             Node ptr = head;
  //             while (ptr.next != prevNode) {
  //               ptr = ptr.next;
  //             }
  //             ptr.next = currNode;
  //           }
  //         }
  //         // System.out.println();
  //         // display();
  //         count++;
  //         // update previous
  //         prevNode = head;
  //         for (int j = 0; j <= flag; j++) {
  //           prevNode = prevNode.next;
  //         }
  //         currNode = prevNode.next;
  //         flag++; // for keeping track of prev
  //       }
  //       // System.out.println("\nInner Ended");
  //     }
  //     // System.out.println("\nOuter Ended");
  //   }
  // }

  /* ----------Boolean Method to check if 2 linked lists are equal----------- */
  public boolean isEqual(HDoublyLL<T> list) {
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

      while (currNode != null && listNode != null) {
        if (!currNode.data.equals(listNode.data)) {
          return false;
        }
        currNode = currNode.next;
        listNode = listNode.next;
      }
      return true;
    }
  }

  // /* ----------Copies the current list into provided list----------- */
  // public HDoublyLL copyInto(HDoublyLL list) {
  //   HDoublyLL newList = new HDoublyLL();
  //   if (isEmpty()) {
  //     return new HDoublyLL();
  //   } else {
  //     Node currNode = this.head;
  //     Node newNode = currNode;
  //     while (currNode != null) {
  //       newList.addLast(currNode.data);
  //       newNode.next = currNode.next;
  //       currNode = currNode.next;
  //       newNode = currNode;
  //     }
  //     return newList;
  //   }
  // }

  /* ---------- To Reverse the original list ----------- */
  public void reverse() {
    // HDoublyLL<T> revList = new HDoublyLL<T>();
    if (isEmpty() || head.next == null) {
      return;
    } else {
      Node<T> prevNode = null;
      Node<T> currNode = head;
      Node<T> nextNode = null;
      this.tail = head; // tail is now head
      while (currNode != null) {
        nextNode = currNode.next; // update nextNode
        
        currNode.next = prevNode; // adjust pointers of current node
        currNode.prev = nextNode;
        
        prevNode = currNode; // update curr and prev
        currNode = nextNode;
      }
      this.head = prevNode; // head is now last node
    }
  }

  /*
   * returns middle Node of list => Hare - Turtle approach-----------
   * If only starting point is given, range will be till end
   */
  public Node<T> middle(Node<T> head) {
    if (isEmpty()) {
      return null; // if list is empty
    } else {
      Node<T> hare = head;    // slow ptr
      Node<T> turtle = head;  // fast ptr
      while (hare != null && hare.next != null) {
        hare = hare.next.next; // 2 steps
        turtle = turtle.next; // 1 step
      }
      return turtle;
    }
  }

  /* ---------Method 2 for middle: If start and end both are given----------- */
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
  // ------------------------------Done till here---------------------------------

  // // // Update Middle Mode --> ***********************Not
  // // completed************************
  // // public void setMiddle(Node middle) {
  // // System.out.println("S: "+size);
  // // // System.out.println(head.data);
  // // // System.out.println(head.next.data);
  // // // System.out.println(head.next.next.data);
  // // if (head == null || head.next == null || head.next.next == null) {
  // // System.out.println("hehe");
  // // this.head = middle;
  // // } else {
  // // Node pointer = head;
  // // for (int i = 0; i < (size/2)-1; i++) {
  // // System.out.println(size);
  // // pointer = pointer.next;
  // // }
  // // System.out.println(pointer.data);
  // // pointer.next = middle;
  // // }
  // // }

  // /*
  //  * --- Iterative Approach --- Returns the new head of reversed list from passed
  //  * head to end-----------
  //  * Can be attached with original list in order to save memory
  //  */
  // public Node iterativeReverse(Node head) {
  //   if (head == null) { // for empty list
  //     System.out.println("List is Empty, Cannot be reversed");
  //     return null;
  //   } else if (head.next == null) { // in case of single element in list
  //     return head;
  //   } else {
  //     // I'll be using 3 pointers i.e., each time deal with 3 nodes in the list
  //     // In each iteration 2nd element will point to 1st element and 3rd element will
  //     // then become the 2nd element which will point backwards and so on untill null
  //     // is reached
  //     // Node prevNode = head; // 1st
  //     // Node currNode = head.next; // 2nd
  //     // while (currNode != null) { // untill reached null
  //     // Node nextNode = currNode.next; // 3rd
  //     // currNode.next = prevNode; // 2nd --> 1st

  //     // // update nodes/pointers
  //     // prevNode = currNode; // 1st = 2nd
  //     // currNode = nextNode; // 2nd = 3rd
  //     // }
  //     // // After loop, current is null which means prev is last element
  //     // head.next = null; // remove already connection of head
  //     // // update the head
  //     // head = prevNode;
  //     // return head;
  //     /*********************** In Simple way *******************************/
  //     Node prevNode = null;
  //     Node currNode = head;
  //     while (currNode != null) {
  //       Node nextNode = currNode.next;
  //       currNode.next = prevNode;

  //       prevNode = currNode;
  //       currNode = nextNode;
  //     }
  //     return prevNode;
  //   }
  // }

  // /*---- Recursive Approach ---- Returns the new head of reversed list from passed head to end -----------
  //  * Can be attached with original list in order to save memory
  //  */
  // public Node recursiveReverse(Node head) {
  //   // base case, for last element
  //   if (head == null || head.next == null) {
  //     return head;
  //   }
  //   // Recursive call that will return new head; when head.next == null it means
  //   // head is last element so it will be the new head that will be same returned to
  //   // all functions below. i.e., this call and return statements are connected
  //   Node newHead = recursiveReverse(head.next);

  //   head.next.next = head; // if current head is 1st element then next of its next element should point to
  //                          // it (1st element) and so on....
  //   // upper line can be divided as below 2 lines
  //   // Node upperNode = head.next;
  //   // upperNode.next = head;
  //   head.next = null; // upperNode will point to currNode/currHead and currHead will point to nothing
  //   return newHead;
  // }

  // /*----------- Returns true if there is any infinite loop/cycle in the list ---------*/
  // public boolean hasCycle(Node head) {
  //   if (head == null) {
  //     return false;
  //   } else {
  //     Node hare = head;
  //     Node turtle = head;
  //     while (hare.next != null && hare.next.next != null) {
  //       hare = hare.next.next;
  //       turtle = turtle.next;
  //       if (hare == turtle) {
  //         return true;
  //       }
  //     }
  //     return false;
  //   }
  // }

  // /*----------- Removes cycle / loop from list, if any ---------*/
  // public void rmvCycle(Node head) {
  //   if (!this.hasCycle(head)) {
  //     System.out.println("There is no cycle in the list to remove !");
  //   } else {
  //     Node hare = head;
  //     Node turtle = head;
  //     while (hare.next != null && hare.next.next != null) {
  //       hare = hare.next.next;
  //       turtle = turtle.next;
  //       if (hare == turtle) {
  //         // System.out.println("Meet At: " + hare.data);
  //         hare.next = null;
  //         break;
  //       }
  //     }
  //   }
  // }

  // /*----------- Merges/concats current list with provided one ---------*/
  // public void merge(HDoublyLL newList) {
  //   // if (head == null) {
  //   // head = newList.getHead();
  //   // size = newList.getSize();
  //   // return;
  //   // } else {
  //   /*
  //    * Part Below does merging but also changes the original list, and behaves diff
  //    * when sorted
  //    */
  //   // Node currNode = head;
  //   // while (currNode.next != null) {
  //   // currNode = currNode.next;
  //   // }
  //   // currNode.next = newList.getHead();
  //   // size += newList.getSize();
  //   // }
  //   /*
  //    * Another Approach, get data from each node of newList and add at last of
  //    * currList
  //    */
  //   Node node = newList.getHead();
  //   while (node != null) {
  //     this.add(node.data);
  //     node = node.next;
  //   }
  // }

  // /* To check if list is palindrome, 121 = 121 */
  // public boolean isPalindrome() {
  //   if (isEmpty() || head.next == null) {
  //     return true; // if list is empty, or there is 1 element
  //   } else {
  //     /*
  //      * -------- M1 - If you want to save memory use below approach but original list
  //      * will be modified--------
  //      */
  //     // // find middle
  //     // Node middle = this.middle(head);
  //     // System.out.println("Middle Value: " + middle.data);
  //     // // Reverse the 2nd half
  //     // middle.next = iterativeReverse(middle.next);
  //     // // Compare 1st half / left side with 2nd half / right side
  //     // Node leftNode = head;
  //     // Node rightNode = middle.next;
  //     // while (rightNode != null) {
  //     // if (!leftNode.data.equals(rightNode.data)) {
  //     // return false;
  //     // }
  //     // leftNode = leftNode.next;
  //     // rightNode = rightNode.next;
  //     // }
  //     // return true;
  //     /*
  //      * ---------------- M2 - A new list will have to be created, but original will
  //      * not be affected----------
  //      */
  //     return this.isEqual(reverse());

  //   }
  // }

  // /* To get SUM of a list, list must be integer type */
  // public int getSum() {
  //   int sum = 0;
  //   Node currNode = head;
  //   while (currNode != null) {
  //     sum += Integer.parseInt(currNode.data);
  //     currNode = currNode.next;
  //   }
  //   return sum;
  // }

}