package Searching_Algorithms;

class BinarySearch {
    public static int binarySearch(int[] array, int left, int right, int key) {
        if (left <= right) {
            // int mid = (left + right) / 2 ; // may cause space complexity issue
            int mid = left + (right - left) / 2;
            System.out.println(mid);
            if (array[mid] == key) {
                return mid;
            } else if (key < array[mid]) {
                // shift right bound to mid-1
                right = mid - 1;
                return binarySearch(array, left, right, key);
            } else {
                // shift left bound
                left = mid + 1;
                return binarySearch(array, left, right, key);
            }
        } else {
            return -1;
        }
    }
    public static void main(String[] args) {
        int[] array = {-1, 0, 1, 3, 4, 5, 8};
        int key = 8;
        int idx = binarySearch(array, 0, array.length - 1, key);
        if (idx != -1) {
            System.out.println(key + " found at index: " + idx);
        } else {
            System.out.println(key + " not found in array !");
        }
    }
}

// Output: 8 found at index: 6