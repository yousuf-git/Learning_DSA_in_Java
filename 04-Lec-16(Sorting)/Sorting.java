public class Sorting {
    public static void main(String[] args) {
        // bubble sort (in each iteration if previous is greater than next one, swap
        // them)
        int[] array = { 5, 4, 3, 6, 7, -1 };
        for (int i = 0; i < array.length - 1; i++) { // len - 1 bcz last element is already sorted (its actaully an
                                                     // iterator, not for index)
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) { // Ascending order
                    // if (array[j] > array[j+1]) { // Descending order
                    // swapping without temp
                    array[j] = array[j] + array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                }
            }
        }
        System.out.print("Sorted Array by bubble sort: ");
        printArray(array);

        // selection sort
        int marks[] = { 50, 90, 40, 30, 69 }; // user input can also be used
        int minIdx, swap;
        for (int i = 0; i < marks.length - 1; i++) {
            minIdx = i; // assume that current index value is smallest
            for (int j = i + 1; j < marks.length; j++) {
                if (marks[j] < marks[minIdx]) {
                    minIdx = j;
                }
            }
            swap = marks[i];
            marks[i] = marks[minIdx];
            marks[minIdx] = swap;
        }
        System.out.print("Sorted Marks by Selection sort: ");
        printArray(marks);

        // insertion sort
        int[] nums = { 2, 543, 453, 54, 453, 4, 342 };
        // i for unsorted array, j for sorted part, (assuming index 0 is sorted)
        for (int i = 1; i < nums.length; i++) {
            int element = nums[i];
            // Make place for element in sorted array
            int j = i - 1; // sorted array ends just before the choosen element
            while (j >= 0 && element < nums[j]) { // index must be >=0 and element should be smaller than the value at
                                                  // that index in sorted portion
                nums[j + 1] = nums[j];
                j--; // moving backward
            }

            // placing the element when condition goes wrong i.e., all indices are checked
            // (j<0) or elememnt is not more smaller than value at index j
            nums[j + 1] = element;
        }
        System.err.print("Sorted Numbers by insertion sort: ");
        printArray(nums);
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
