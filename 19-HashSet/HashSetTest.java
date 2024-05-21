/* HashSetTest calss to test different methods of java.util.HashSet class
 * 1. Add Element in set
 * 2. Remove Element from set
 * 3. Size of set
 * 4. Iteration in set
 * 5. Searching element in set
 * 6. Printing set elements using System.out.println()
*/


import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
    public static void main(String[] args) {
        
        HashSet<Integer> set = new HashSet<>();
        /*-------Adding elements in set-------
         * Duplicates are not allowed in set
        */
        set.add(3);
        set.add(7);
        set.add(5);
        set.add(3); // will be ignored
        set.add(6);
        set.add(9);
        set.add(6); // will be ignored
        
        /*-------Size of set-------*/
        System.out.println("Size of set: " + set.size());
        
        /*-------Remove element from set-------*/
        set.remove(6);
        set.remove(2); // nothing happens if element is not present in set
        System.out.println("Size of set: " + set.size());
        
        /*-------Iterator in set-------
         * Iterator has 2 methods:
         * next() => goto next element in set
         * hasNext() => returns true if there is next element in the set, false otherwise
         * No sequence, of traversing
        */
        System.out.print("Iterating over elements in set: ");
        Iterator<Integer> i = set.iterator();
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }
        System.out.println();

        /*-------Search element in set-------*/
        if (set.contains(6)) {
            System.out.println(6 + " found in set");
        } else {
            System.out.println(6 + " not found in set");
        }

        /*-------Print all elements in set-------*/
        System.out.println("Elements in set: " + set);
    }
}
