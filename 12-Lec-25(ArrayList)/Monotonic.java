/**
 * Q: An Arraylist is monotonic if it is either monotone increasing or monotone decreasing.
 * 
 * - An Arraylist nums is monotone increasing if for all i <= j, nums.get(i) <= nums.get(j).
 * 
 * - An Arraylist nums is monotone decreasing if for all i <= j, nums.get(i) >= nums.get(j).
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class Monotonic {
    public static boolean isMonotonic(List<Integer> list) {
        // Brute Force - O(n^2)

        boolean ascMono = true;
        boolean dscMono = true;

        // // Check for ascending - O(n)
        // for (int i = 0; i < list.size() - 1; i++) {
        //     if (list.get(i) > list.get(i + 1)) {
        //         ascMono = false;
        //         break;
        //     }
        // }
        // // Check for descending - O(n)
        // for (int i = 0; i < list.size() - 1; i++) {
        //     if (list.get(i) < list.get(i + 1)) {
        //         dscMono = false;
        //         break;
        //     }
        // }

        // Optimized - O(n)

        // Check in single iteration
        int i = 0;
        while (i < list.size() - 1) {
            // Ascedning condition violation
            if (list.get(i) > list.get(i + 1)) {
                ascMono = false;
            }
            // Descedning condition violation
            if (list.get(i) < list.get(i + 1)) {
                dscMono = false;
            }
            i++;
        }
        return ascMono || dscMono;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        // list.add(4);
        // list.add(6);
        list.add(10);
        list.add(9);
        // list.add(10);
        // list.add(69);
        // list.add(70);

        if (isMonotonic(list)) {
            System.out.println("Given list is monotonic");
        } else {
            System.out.println("Given List is not monotonic !");
        }
    }

}
