import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
// import java.util.ArrayList;
import java.util.Set;

/**
_________This class has the basic implementation of ArrayList from scratch_________
Includes the following methods:

1. size(): returns number of elements present in the ArrayList


 */
public class HArrayList<E> {

    private int size; // number of elements in the list
    private E[] list; // array of type E


    /**
     * @construcor
     */
    @SuppressWarnings("unchecked")
    public HArrayList() {
        size = 0;
        // list = new E[2]; // generic types are erased at runtime for backward compatibility reasons
        list = (E[]) new Object[10]; // create object and then type cast it
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Time Complexity: O(n)
    public boolean contains(Object o) {
        boolean contains = false;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
                contains = true;
            }
        }
        return contains;
    }

    public Object[] toArray() {
        Object[] copy = new Object[size];
        for (int i = 0; i < size; i++) {
            copy[i] = list[i];
        }
        return copy;
    }

    /*----To put elements of list into given array------ */
    public <T> E[] toArray(E[] array) {
        E[] arr;
        // Validate If given array is enough to accomodate all elements or not
        if (array.length >= this.size) {
            arr = array;
        } else {
            @SuppressWarnings("unchecked")
            E[] newArray = (E[]) new Object[size];
            arr = newArray;
        }
        // Copy all elements in same order
        for (int i = 0; i < size; i++) {
            arr[i] = list[i];
        }
        // If length of given array is larger than list, nullify remaining elements
        for (int i = size; i < arr.length; i++) {
            arr[i] = null;
        }
        return arr;
    }

    // Time Complexity
    // Amortized: O(1)      because every time we don't need to resize the list
    // Worse case: O(n)
    public boolean add(E element) {
        list[size] = element; // store the element at index: size
        size++; // increase the number of elements in the list
        // Check if size has reached to the end of list
        if (size == list.length) {
            return resize();
        }
        return true;
    }

    // Resizing Time Complexity - O(n)
    private boolean resize() {
        try {
            // Recreate the list - with length 50% more of original one
            @SuppressWarnings("unchecked")
            E[] newList = (E[]) new Object[list.length + (list.length / 2)];
            // Copy elements from the old list to new one
            for (int i = 0; i < size; i++) {
                newList[i] = list[i];
            }
            list = newList; // reference the old list to new list now
        } catch (Exception e) {
            System.out.println(e);
            return false; // In case if resizing cannot be done
        }
        return true;
    }

    /*______Time complexity Analysis________ 
     * Search -> O(n)
     * Shifting -> O(n)
     * Total  = O(n) + O(n) = O(2n) = O(n)
    */
    public boolean remove(Object obj) {
        // First check if obj is present in the list or not
        int index = indexOf(obj); // get the index of object
        if (index != -1) {
            remove(index); // remove it using remove(int idx)
            return true;
        } else {
            return false;
        }
    }

    // Time Complexity: O(n)       Shifting n elements in worse case
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null; // in case of invalid index
        }
        E element = list[index]; // grab the element from given index
        // Shift all elements from that index to end, one step backward
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        size--; // decrement the size
        return element; // return the old elememnt present at that index
    }

    // Time Complexity: O(m * n) where m is length of collection and n is size of list
    public boolean containsAll(Collection<?> c) {
        boolean contains = true;
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return contains;
    }

    // Time Complexity: O(m) where m is the number of elements in the collection
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            if (!add(element)) { // if element cannot be added (resizing issue)
                return false;
            }
        }
        return true;
    }

    /* -------- To Add a list at specific position -----------
     *  - The previous Elements will be shifted right
     *  Time Complexity: O(m*n)     where m is elements in the collection
     *  Can be optimized by shifting one time only
     *  By calling add(idx) for each element, shifting is done each time
     *  In worse case there will be shifting of n elements m times
     */
    // public boolean addAll(int index, Collection<? extends E> c) {
    //     // Validation
    //     if (c == null || c.size() == 0) {
    //         return false;
    //     }
    //     if (index < 0 || index >= size) {
    //         return false;
    //     }

    //     for (E element : c) {
    //         if (!add(index, element)) {
    //             System.out.println("Some error occured while resizing");
    //             return false;
    //         }
    //         index++;
    //     }
    //     return true;
    // }
    /* Optimized addAll()
     * Time Complexity Analysis:
     *  - Shifting -> O(n)      n = this.size()
     *  - Placing -> O(m)       m = c.size()
     *  - Total  = O(n) + O(m)
     * Optimized from O(m*n) to O(m+n)
     * 
    */
    public boolean addAll(int index, Collection<? extends E> c) {
        // Validation
        if (c == null || c.size() == 0) {
            return false;
        }
        if (index < 0 || index >= size) {
            return false;
        }
        int s = c.size();
        // Resize the lise untill all elements can be added
        while (s + size > list.length) {
            if (!resize()) {
                return false;
            }
        }
        // Shift the prrvious elements at once
        for (int i = size-1; i >= index; i--) {
            list[i + s] = list[i];
        }
        // Place the elements in the collection
        int idx = index;
        for (E e : c) {
            list[idx] = e;
            idx++;
        }
        size += s;

        return true;
    }

    // Time Complexity: O(n * m)
    public boolean removeAll(Collection<?> collection) {
        boolean isChanged = false;
        for (Object object : collection) {
            if (this.remove(object)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    /*-------Retain only these elements of collection - remove others from list -------
     * Time Complexity: O(n * (m+n))
     */
    // public boolean retainAll(Collection<?> collection) {
    //     // If empty or null collection is passed, remove all elements in the list
    //     if (collection == null || collection.size() == 0) {
    //         this.clear();
    //         return true;
    //     }
    //     boolean isModified = false;
    //     // Iterate over all the elements in the list
    //     for (int i = 0; i < size; i++) {
    //         System.out.print(i + " ");
    //         E element = list[i];
    //         System.out.println("Grabbed Element: " + element);
    //         // If the element from the list is not present in the collection - remove it
    //         if (!collection.contains(element)) {
    //             if (this.remove(element)) {
    //                 System.out.println("Removed");
    //                 i -= 1;
    //                 isModified = true; // if list is modified
    //             }
    //         }
    //     }
    //     return isModified;
    // }
    /*-----Optimized retainAll------------
     *  - Store elements of collection in a set -> O(m)
     *  - Iterate over the old list -> O(n)
     *  - Search in set -> O(1)
     *  Total Time Complexity -> O(m+n)
     */
    public boolean retainAll(Collection<?> collection) {
        // If empty or null collection is passed, remove all elements in the list
        if (collection == null || collection.size() == 0) {
            this.clear();
            return true;
        }
        Set<?> set = new HashSet<>(collection); // O(m)
        boolean isModified = false;
        int newIdx = 0;
        int newSize = 0;
        // Iterate over all the elements in the list
        for (int i = 0; i < size; i++) {
            E element = list[i];
            // If the element from the list is not present in the set - remove it
            if (set.contains(element)) {
                // retain it
                list[newIdx++] = element;
                newSize += 1;
            } else {
                list[i] = null;
                isModified = true;
            }
        }
        this.size = newSize;

        return isModified;
    }

    /*-------Remove all the elements from the list------- */
    // Time Complexity: O(n)
    public void clear() {
        // Mistake
        // for (int i = 0; i < size; i++) {
        //     this.remove(list[i]);
        // }
        // Method-1: Not Optimized -> O(n^2)
        // int idx = 0;
        // while (!this.isEmpty()) {
        //     this.remove(idx);
        // }
        // Method-2: Optimized -> O(n)
        int idx = size - 1;
        while (!this.isEmpty()) {
            remove(idx);
            idx = size - 1;
        }
        System.out.println("List Cleared !");
    }

    // Time Complexity: O(1)
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null; // invalid index
        }
        return list[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     * Time Complexity: O(1)
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = list[index];
        list[index] = element;
        return oldElement;
    }

    /* 
     * Shifts the old element at given index and all subsequents to right
     * Time Complexity: O(n) in worse case we need to move all elements to right
     */
    public boolean add(int index, E element) {
        // Validation of index
        if (index < 0 || index >= size) {
            return false;
        }
        // Shift all elements from given index to size, one step forward
        for (int i = size - 1; i >= index; i--) {
            if (i == list.length - 1) {
                if (!resize()) {
                    System.out.println("Resizing issue");
                    return false;
                }
            }
            list[i + 1] = list[i];
        }
        list[index] = element;
        size++;
        return true;
    }

    // Time Complexity: O(n)
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
                // return the very first index
                return i;
            }
        }
        return -1;
    }

    // Time Complexity: O(n)
    public int lastIndexOf(Object o) {
        int idx = -1;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
                idx = i;
            }
        }
        return idx;
    }

    /* Returns a list in given range, last index is not included
     * Time Complexity: O(n) for worse case, subList = list
     */
    public List<E> subList(int fromIdx, int toIdx) {
        // Validation
        if (fromIdx > toIdx || fromIdx < 0 || fromIdx >= size || toIdx < 0 || toIdx > size || isEmpty()) {
            return null;
        }
        List<E> subList = new ArrayList<>();
        int idx = fromIdx;
        subList.add(list[fromIdx]);
        while (idx < toIdx - 1) {
            idx++;
            subList.add(list[idx]);
        }
        return subList;
    }

    // Time Complexity: O(n)
    public void display() {
        if (this.isEmpty()) {
            System.out.println("List: []");
            return;
        }
        System.out.print("List: ");
        for (int i = 0; i < size; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    // Time Complexity: O(n)
    public HArrayList<E> copy() {
        HArrayList<E> copy = new HArrayList<>();
        for (int i = 0; i < size; i++) {
            copy.add(list[i]);
        }
        return copy;
    }
}
