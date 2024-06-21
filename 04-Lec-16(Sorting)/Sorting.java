public class Sorting {
    public static void main(String[] args) {
        // Bubble sort (in each iteration if previous is greater than next one, swap them)
        // Time Complexity: 𝑂(𝑛²)
        int[] array = { 5, 4, 3, 6, 7, -1 };
        // Use an iterator (i) from 0 to len - 1 bcz last element is already sorted
        for (int i = 0; i < array.length - 1; i++) {
            // an index tracker (j) that will be used to compare prev element with next consecutive
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) { // For Ascending order
                    // if (array[j] > array[j+1]) { // For Descending order
                    // swapping without temp
                    array[j] = array[j] + array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                }
            }
        }
        System.out.print("Sorted Array by bubble sort: ");
        printArray(array); // a user-dfined method to print the elements of array

        // Selection sort: Find smallest element and swap with 1st unsorted element
        // Time Complexity: 𝑂(𝑛²)
        int marks[] = { 50, 90, 40, 30, 69 }; // user input can also be used
        int minIdx, swap; // minIdx will track the index of smallest element in each iteration
        for (int i = 0; i < marks.length - 1; i++) {
            minIdx = i; // assume that current index value is smallest
            for (int j = i + 1; j < marks.length; j++) {
                if (marks[j] < marks[minIdx]) {
                    minIdx = j; // no swap just update the index
                }
            }
            // Swap using thirs variable (swap)
            swap = marks[i];
            marks[i] = marks[minIdx];
            marks[minIdx] = swap;
        }
        System.out.print("Sorted Marks by Selection sort: ");
        printArray(marks);

        /* Insertion sort 
         * Time Complexity: 𝑂(𝑛²) in the average and worst case, but 𝑂(𝑛) in the best case for nearly sorted data
         * Divide the array into 2 parts, sorted and unsorted
         * Choose 1 element from the unsorted portion and place it to its correct position in sorted portion
        */
        int[] nums = { 2, 543, 453, 54, 453, 4, 342 };
        // i for unsorted part, j for sorted part, (assuming index 0 is sorted initially)
        for (int i = 1; i < nums.length; i++) {
            int element = nums[i]; // store the current element because its index will be used to shift elements from unsorted portion
            // Make place for element in sorted array
            int j = i - 1; // sorted array ends just before the choosen element
            // j index must be valid (j>=0) and element should be smaller than the value at that index in sorted portion
            while (j >= 0 && element < nums[j]) {
                nums[j + 1] = nums[j]; // shift the element from sorted portion to one step forward
                j--; // moving backward in sorted portion
            }

            // placing the element when condition goes wrong i.e., all indices are checked
            // (j<0) or elememnt is not more smaller than value at index j
            nums[j + 1] = element;
        }
        System.err.print("Sorted Numbers by insertion sort: ");
        printArray(nums);
        int[] arr = {11,2,4,6,1,4,8,10};
        shellSort(arr);
        System.out.print("Shell Sort: ");
        printArray(arr);
    }

    /*  Shell Sort
     * Sorting based on comparisons with elements that are specific distance away - gap
     * Time Complexity: Typically ranges from 𝑂(𝑛 log 𝑛) to 𝑂(𝑛²) depending on the gap sequence used, but generally * performs much better than Insertion Sort for larger arrays.
     */
    
    public static void shellSort(int[] array) {
        if (array.length <= 1) { // just in case invalid array or array with single element arrives
            return;
        } else {
            int gap, swap;
            gap = array.length / 2; // there are more methods of calculating gap as well
            while (gap != 0) {
                for (int i = gap; i < array.length; i++) { // Start from index (i) = gap to end of array
                    /* Get the element that is gap distance before the upper element
                     * If swapping occures, we need to check for swap gap distance backwards too
                     * That's why j is decremented gap times
                    */
                    for (int j = i - gap; j >= 0; j -= gap) {
                        if (array[j] > array[j + gap]) { // compare array[j] with gap distance forward
                            // swap
                            swap = array[j];
                            array[j] = array[j + gap];
                            array[j + gap] = swap;
                        } else {
                            break; // if swapping not occures, not need to update j and check backwards
                        }
                    }
                }
                gap /= 2; // update the gap
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int elememnt : arr) {
            System.out.print(elememnt + " ");
        }
        System.out.println();
    }
}

// These all are to arrange in ascending order, try to do it for descending
// order...
