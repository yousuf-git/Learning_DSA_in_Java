import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        System.out.println("\nCreating List........");
        HArrayList<Integer> list = new HArrayList<>();

        System.out.println("\nCheck if List is Empty\n");
        if (list.isEmpty()) {
            System.out.println("Yes, List is Empty !");
        } else {
            System.out.println("No, List is not empty");
        }

        System.out.println("\nAdding 14 elements in list\n");
        for (int i = 1; i < 15; i++) {
            if (list.add(i * 2)) {
                System.out.println(i * 2 + " Added");
            } else {
                System.out.println("Error at " + i);
            }
        }

        System.out.println("\nChecking size of list\n");
        System.out.println("Size of List : " + list.size());

        System.out.println("\nPrinting Elements by get() method\n");
        System.out.print("Elements in list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        int element = 69;
        System.out.println("\nChecking if list contains " + element + "\n");
        if (list.contains(element)) {
            System.out.println(element + "found ");
        } else {
            System.out.println(element + " not found");
        }

        element = 12;
        System.out.println("\nGetting index of " + element + "\n");
        int idx = list.indexOf(element);
        if (idx != -1) {
            System.out.println(element + " is at index " + idx);
        } else {
            System.out.println(element + " Not found");
        }

        /*__________Interesting fact___________
        There are 2 type of remove methods:
        1. remove(int index) - returns element that is removed, null if element is absent in list
        2. remove(E obj) - returns true if element is removed successfully, false otherwise
        
        Q: If list is integer type and we call remove(10) which one will be called?
        A: remove(int index) will be called bcz it is most specific applicable method for method overloading resolution
        
        So, if I want to remove an element 10 itself from the list, I'll call remove((Integer) 10) - by casting
        
        */

        // Testing: remove(int index) - returns element / null
        System.out.println("\nTesting remove(int index)\n");
        element = 10;
        Integer removedElement = list.remove(element); // here element will be as index
        if (removedElement != null) {
            System.out.println(removedElement + " removed !");
        } else {
            System.out.println("Invalid Index!");
        }

        // Testing: remove(E obj) - returns true / false
        System.out.println("\nTesting remove(E obj)\n");
        if (list.remove((Integer) element)) {
            System.out.println(element + " removed !");
        } else {
            System.out.println(element + " not found in list");
        }

        System.out.println("\nPrinting elements of list by display()\n");
        list.display();
        System.out.println("Updated Size of List : " + list.size());


        System.out.println("\nTesting toArray()\n");
        Object[] copy = list.toArray();
        System.out.print("Copied Array of List: ");
        for (Object val : copy) {
            System.out.print(val + " ");
        }
        System.out.println();
        // If we want to convert this array to integer type
        // Integer[] copyList = new Integer[copy.length];

        // for (int i = 0; i < copy.length; i++) {
        //     copyList[i] = (Integer) (copy[i]);
        // }

        // for (int i = 0; i < copyList.length; i++) {
        //     System.out.print(copyList[i] + " ");
        // }
        // System.out.println();

        System.out.println("\nTesting removeAll(Collection<?> c)\n");
        ArrayList<Integer> listToRemove = new ArrayList<>();
        listToRemove.add(2);
        listToRemove.add(4);
        listToRemove.add(6);
        listToRemove.add(8);
        listToRemove.add(11);
        // ArrayList<String> listToRemove = new ArrayList<>();
        // listToRemove.add("2");
        // listToRemove.add("4");
        // listToRemove.add("6");
        // listToRemove.add("8");
        // listToRemove.add("11");

        System.out.print("List of elements that are to be removed: ");
        for (Object val : listToRemove) {
            System.out.print(val + " ");
        }
        System.out.println();

        list.removeAll(listToRemove);

        System.out.print("Updated ");
        list.display();
        System.out.println("Updated Size : " + list.size());

        System.out.println("\nChecking if copied array is affected by original list\n");
        System.out.print("Copy List: ");
        for (Object val : copy) {
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println("\nTesting addAll(Collection<? extends E> c)\n");
        list.addAll(listToRemove); // adding the same elements again that were removed
        System.out.print("Updated ");
        list.display();

        System.out.println("\nTesting retainAll(Collection<?> c)\n");
        listToRemove.add(16);
        System.out.print("List of elements that are to be retained: ");
        for (Object val : listToRemove) {
            System.out.print(val + " ");
        }
        System.out.println();
        if (list.retainAll(listToRemove)) { // only these elements should be retained
            System.out.print("Updated ");
            list.display();
        } else {
            System.out.println("No Change in list");
        }

        System.out.println("\nTesting addAll(int index, Collection<? extends E> c)\n");

        System.out.print("Original ");
        list.display();
        System.out.print("List of elements that are to be added: ");
        for (Object val : listToRemove) {
            System.out.print(val + " ");
        }
        System.out.println();

        list.addAll(4, listToRemove);
        System.out.print("Updated ");
        list.display();

        System.out.println("\nTesting copy()\n");
        HArrayList<Integer> duplicate = list.copy();
        System.err.print("Duplicate ");
        duplicate.display();
        duplicate.clear();
        System.err.print("Updated Duplicate ");
        duplicate.display();
        System.out.println("Last Idx of 2: " + list.lastIndexOf(2));

        List<Integer> subList = list.subList(1, list.size());
        if (subList == null) {
            System.out.println("List is empty or invalid range !");
        } else {
            System.out.print("Sub List: ");
            for (Integer integer : subList) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
/*

Suppose, length = 7

  1   2   3   4   5   6   7       size
  0   1   2   3   4   5   6       index
_____________________________
| 2 | 3 | 4 | 5 | 5 | 5 | 7 |
____________________________

if(size = length) -> rehashing

if size = 7 means array is filled, now rehashing will be done (new list will be created)

*/
