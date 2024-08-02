public class QuickSort2 {
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {
        // Base Case
        if (left >= right) {
            return;
        }
        // Step 1 : Get the pivot
        int pivot = array[right];
        
        // Step 2:move elements less than pivot to left of it and greater than pivot to right of it
        int pivotIdx = placePivot(array, pivot, left, right);
        
        // Step 2:  Place pivot to correct location 
        swap(array, pivotIdx, right);
        
        // recursive call for left 
        quickSort(array, left, pivotIdx - 1);
        // recursive call for right 
        quickSort(array, pivotIdx + 1, right);
    }

    public static int placePivot(int[] array, int pivot, int leftPtr, int rightPtr) {
        // Untill the pointers are in valid range
        while (leftPtr < rightPtr) {
            // Find element greater than pivot in left side, untill then move leftPtr right
            while (array[leftPtr] <= pivot && leftPtr < rightPtr) {
                leftPtr++;
            }
            // Find element less than pivot in right side, untill then move rightPtr left
            while (array[rightPtr] >= pivot && leftPtr < rightPtr) {
                rightPtr--;
            }
            swap(array, leftPtr, rightPtr); // move the small value to left, and large value to right
        }
        return leftPtr;
    }

    public static void swap(int[] array, int idx1, int idx2) {
        int swap = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = swap;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 5, 10, 4, 0, 4, 9, 1};

        System.out.print("\nOriginal Array: ");
        for (int element : array) {
            System.out.print(element + " ");
        }

        quickSort(array);

        System.out.print("\nSorted Array: ");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println("\n");
    }
}
