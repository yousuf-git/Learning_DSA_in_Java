// Array has fixed size, can store primitive types + objects, memory is in stack
// ArrayList has no fixed size, can store only objects, memory is in heap

/*
General ArrayList functions/operatios

1. Add
2. Get
3. Modify
4. Delete/Remove
5. Iterations etc.

***************************************/

import java.util.ArrayList;
import java.util.Collections;

class ArrayLists{
    public static void main(String[] args) {
        // Generic types that can be used:
        //  Integer | Character | String | Boolean | Float | Byte | Short | Long | Double
        ArrayList<Integer> intList =  new ArrayList<Integer>();

        // Add elements
        intList.add(49);
        intList.add(29);
        intList.add(9);
        intList.add(69);
        System.out.println("Integer List: "+ intList);

        // Add new value at index 3, if there is already a value there, it will be shifted to right
        intList.add(3, 59);
        System.out.println("Integer List: "+ intList);

        // intList.add(6, 69); // error bcz of out of bound

        // For setting/replacing an existing value

        intList.set(3, 39);
        System.out.println("Integer List: "+ intList);

        // Remove an element
        intList.remove(3);
        System.out.println("Integer List: " + intList);

        // Count elements
        System.out.println("Total Elements in List: "+ intList.size());

        // Accessing each element in list
        System.out.print("Value of Elements in List: ");
        for (int val : intList) {
            System.out.print(val + " ");
        }

        // Searching --> Time Complexity => O(1)
        int item = 9;
        if (intList.contains(item)){
            System.out.println("\nItem Found");
        } else {
            System.out.println("\nItem Not Found ");
        }

        // Sorting
        Collections.sort(intList);
        System.out.println("Sorted List: "+intList);

    }
}