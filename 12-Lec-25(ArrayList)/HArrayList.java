import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
// import java.util.ArrayList;

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
    HArrayList() {
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

    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
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

    // Resizing - O(n)
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
        } catch(Exception e) {
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
        int index = indexOf(obj); // get the index of object and then remove it using remove(idx)
        if (index != -1) {
            remove(index);
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
        return element;
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
            if(add(element)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    // Time Complexity: O(n * m)
    public boolean removeAll(Collection<?> collection) {
        boolean isChanged = false;
        for (Object object : collection) {
            if(this.remove(object)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean isModified = false;
        // Iterate over all the elements in the list
        for (E element : list) {
            // If the element from the list is not present in the collection - remove it
            if (!collection.contains(element)) {
                if(this.remove(element)) {
                    isModified = true; // if list is modified
                }
            }
        }
        return isModified;
    }

    /*-------Remove all the elements from the list------- */
    // Time Complexity: O(n)
    public void clear() {
        for (E element : list) {
            this.remove(element);
        }
    }

    // Time Complexity: O(1)
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null; // invalid index
        }
        return list[index];
    }

    // Time Complexity: O(1)
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            return null;
        }
        E oldElement = list[index];
        list[index] = element;
        return oldElement;
    }

    public boolean add(int index, E element) {
        // Validation of index
        if (index < 0 || index >= size) {
            return false;
        }
        // Shift all elements from given index to size, one step forward
        for (int i = index; i < size; i++) {
            if (index == size) {
                if(!resize()) {
                    return false;
                }
            }
            list[i+1] = list[i];
        }
        list[index] = element;
        return true;
    }

    // Time Complexity: O(n)
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
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

    public List<E> subList(int fromIndex, int toIndex) {
        List<E> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(list[i]);
        }
        return subList;
    }

    public void display() {
        System.out.print("List: ");
        for (int i = 0; i < size; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }
}
