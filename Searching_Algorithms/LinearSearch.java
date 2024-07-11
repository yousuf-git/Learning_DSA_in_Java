package Searching_Algorithms;

public class LinearSearch {
    public static int linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = {2, 3, 0, 1, -1, 8, 4};
        int key = 4;
        int idx = linearSearch(array, key);
        if (idx != -1) {
            System.out.println(key + " found at index: " + idx);
        } else {
            System.out.println(key + " not found in array !");
        }
    }
}

// Output: 4 found at index: 6
