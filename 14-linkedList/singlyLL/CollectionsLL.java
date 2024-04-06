// See this file at the end 

package singlyLL;

// Built it Linked List class that helps to avoid implementing linked list methods from scratch

import java.util.LinkedList;

public class CollectionsLL {

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<String>();
    list.addFirst("Harry");
    list.addFirst("I'm");
    list.addLast("Learning");
    list.addLast("Linked");
    list.addLast("List");

    System.out.println("\nList: " + list);
    System.out.println("Size of List: " + list.size());

    list.addLast("newVal");
    System.out.println("\nList: " + list);
    System.out.println("Size of List: " + list.size());

    list.removeLast();
    System.out.println("\nList: " + list);
    System.out.println("Size of List: " + list.size());

    list.removeFirst();
    System.out.println("\nList: " + list);
    System.out.println("Size of List: " + list.size());

    // If we use add/remove without specifying first/last, item is added/removed
    // from last
    list.add("testVal");
    System.out.println("\nList: " + list);
    System.out.println("Size of List: " + list.size());

    list.remove("testVal");
    System.out.println("\nList: " + list);
    System.out.println("Size of List: " + list.size());

    // Access values in list

    System.out.print("\nValue of Elements in List: ");
    for (String val : list) {
      System.out.print(val + " -->  ");
    }
    System.out.println("null");
    // by get method
    System.out.print("\nBy Method 2: ");
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + " --> ");
    }
    System.out.println("null");
  }
}
