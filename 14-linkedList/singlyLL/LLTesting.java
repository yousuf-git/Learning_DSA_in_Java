package singlyLL;

public class LLTesting {

  public static void main(String[] args) {
    System.out.println(
      "\n-------------Testing diff Linked List Methods-------------\n"
    );

    HSinglyLL<String> list = new HSinglyLL<>();
    /*-----------addFirst Method------------*/
    list.addFirst("I'm");
    /*-----------addLast Method------------*/
    list.addLast("Harry");
    list.addLast("Learning");
    list.addLast("Linked");
    list.addLast("List");
    /*-----------display Method------------*/
    // list.display();
    list.newDisplay();
    /*-----------delFirst Method------------*/
    list.delFirst();
    // list.display();
    list.newDisplay();
    /*-----------delLast Method------------*/
    list.delLast();
    // list.display();
    list.newDisplay();

    /*-----------Size Method------------*/
    System.out.println("Size of List: " + list.getSize());

    HSinglyLL<String> list_1 = new HSinglyLL<>();
    list_1.addFirst("1");
    list_1.addLast("2");
    list_1.addLast("3");
    System.out.print("\nList 1: ");
    // list_1.display();
    list_1.newDisplay();

    HSinglyLL<String> list_2 = new HSinglyLL<>();
    list_2.addFirst("1");
    list_2.addLast("2");
    // list_2.addLast("3");
    list_2.addLast("4");
    System.out.print("List 2: ");
    // list_2.display();
    list_2.newDisplay();
    
    /*-----------isEqual Method------------*/
    if (list_1.isEqual(list_2)) {
      System.out.println("List 1 is equal to list 2");
    } else {
      System.out.println("List 1 is not equal to list 2");
    }

    /*-----------copying the list into new list------------*/
    HSinglyLL<String> copyList = new HSinglyLL<>();
    copyList = list_2.copyInto(copyList);
    System.out.print("\nCopied List: ");
    // copyList.display();
    copyList.newDisplay();

    /*******************Trying to Copy an empty list**************************/
    // MyLinkedList copyList = new MyLinkedList();
    // copyList = new MyLinkedList().copyInto(copyList);
    // System.out.println("\nCopied List:");
    // copyList.display();

    /*-----------Testing if copied list is equal to original or not------------*/ 
    if (copyList.isEqual(list_2)) {
      System.out.println("Copied List is equal to list 2");
    } else {
      System.out.println("Copied List is not equal to list 2");
    }

    /*-----------Verifying that changes in copied list doesn't affect the original------------*/
    copyList.addLast("6");
    System.out.print("\nUpdated Copied List: ");
    // copyList.display();
    copyList.newDisplay();
    
    System.out.print("List 2: ");
    // list_2.display();
    list_2.newDisplay();

    /*******************Creating a new list that is reverse of original**************************/
    HSinglyLL<String> revList = list_2.reverse();
    System.out.print("\nReverse of List 2: ");
    // revList.display();
    revList.newDisplay();

    /*-----------Verifying that changes in reversed list doesn't affect the original------------*/
    revList.add("3");
    revList.add("5");
    revList.add("6");
    revList.add("7");
    revList.add("8");
    System.out.print("\nUpdated Reversed List: ");
    // revList.display();
    revList.newDisplay();
    System.out.print("List 2: ");
    // list_2.display();
    list_2.newDisplay();

    /*-------------Reversing the list after middle i.e. 2nd half of it------------------*/
    Node<String> head = revList.getHead();
    Node<String> mid = revList.middle(head);
    System.out.println("\nMiddle of List: " + mid.data);

    // newHead will be returned that will lead to reversed portion
    mid.next = revList.recursiveReverse(mid.next); // Pointing original list's mid to newHead of reversed list
    // -------------iterativeReverse can also be used above

    System.out.println("Returned Head val: " + mid.next.data);
    System.out.print("Returned List that is going to be connected after mid: ");
    Node<String> updNode = mid.next;
    while (updNode != null) {
      System.out.print(updNode.data + " ");
      updNode = updNode.next;
    }
    // head = null; // doing this will not change the actual head of list bcz head is a new node that is created here and this line is changing its value, to change value of list's head setHead() method can be used
    System.out.print("\nFinal List after Reversing 2nd half: ");
    // revList.display();
    revList.newDisplay();
  }
}