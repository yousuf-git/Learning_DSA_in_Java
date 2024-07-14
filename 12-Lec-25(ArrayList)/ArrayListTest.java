import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        HArrayList<Integer> list = new HArrayList<>();

        if (list.isEmpty()) {
            System.out.println("List is Empty !");
        } else {
            System.out.println("List is not empty");
        }

        for (int i = 1; i < 15; i++) {
            if (list.add(i*2)) {
                System.out.println(i*2 + " Added");
            } else {
                System.out.println("Error at " + i);
            }
        }

        System.out.println("Size of List : " + list.size());

        System.out.print("Elements in list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        int element = 69;
        if (list.contains(element)) {
            System.out.println(element + "found ");
        } else {
            System.out.println(element + " not found");
        }
        element = 12;
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

        So, if I want to remove an element 10 itself from the list, I'll call remove((Integer) 10) - cast

        */ 

        // Testing: remove(int index) - returns element / null
        element = 10;
        Integer removedElement = list.remove(element); // here element will be as index
        if (removedElement != null) {
            System.out.println(removedElement + " removed !");
        } else {
            System.out.println(element + " not found in list !");
        }

        // Testing: remove(E obj) - returns true / false
        if (list.remove((Integer) element)) {
            System.out.println(element + " removed !");
        } else {
            System.out.println(element + " not found in list");
        }
        System.out.print("Elements in list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println("Updated Size of List : " + list.size());

        Object[] copy = list.toArray();
        System.out.print("Copy List: ");
        for (Object val : copy) {
            System.out.print(val + " ");
        }
        // If we want to convert this array to integer type
        // Integer[] copyList = new Integer[copy.length];

        // for (int i = 0; i < copy.length; i++) {
        //     copyList[i] = (Integer) (copy[i]);
        // }

        // for (int i = 0; i < copyList.length; i++) {
        //     System.out.print(copyList[i] + " ");
        // }

        System.out.println();

        // ArrayList<Integer> listToRemove = new ArrayList<>();
        // listToRemove.add(2);
        // listToRemove.add(4);
        // listToRemove.add(6);
        // listToRemove.add(8);
        // listToRemove.add(11);
        ArrayList<String> listToRemove = new ArrayList<>();
        listToRemove.add("2");
        listToRemove.add("4");
        listToRemove.add("6");
        listToRemove.add("8");
        listToRemove.add("11");

        list.removeAll(listToRemove);

        System.out.print("Updated ");
        list.display();
        System.out.println("Updated Size : " + list.size());

        System.out.print("Copy List: ");
        for (Object val : copy) {
            System.out.print(val + " ");
        }
        System.out.println();

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