/*
 * Pivot and Partition
 * Pivot means a central/fixed point, it can be:
 *      Random
 *      Median
 *      First Element
 *      Last Element (I used here)
 * Step 1: Move elements less than pivot before pivot and elements greater * than pivot after pivot (pivot will move to its correct position)
 * Step 2: Repeat step 1 untill there is only unit element
 */

public class QuickSort {

  public static int getPivot(int[] array, int stIdx, int endIdx) {
    int pivot = array[endIdx]; // last element choosen as pivot
    int i = stIdx - 1; //
    for (int j = stIdx; j < endIdx; j++) {
      if (array[j] < pivot) {
        i++;
        // swap array[i] and array[j]
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    }
    i++;
    int temp = array[i];
    array[i] = array[endIdx];
    array[endIdx] = temp;
    return i;
  }

  public static void quickSort(int[] array, int stIdx, int endIdx) {
    if (stIdx < endIdx) {
      int pivotIdx = getPivot(array, stIdx, endIdx);

      quickSort(array, stIdx, pivotIdx - 1);
      quickSort(array, pivotIdx + 1, endIdx);
    }
  }

  public static void main(String[] args) {
    int[] array = { 2, 4, 5, 10, 4, 0, 4, 9, 1 };

    System.out.println("Original Array:");
    for (int element : array) {
      System.out.print(element + " ");
    }

    quickSort(array, 0, array.length - 1);

    System.out.println("\nSorted Array:");
    for (int element : array) {
      System.out.print(element + " ");
    }
  }
}
/*
 * Time Complexity:
 * Worst case if pivot is always smallest and largest,
 * Method 1
 * Suppose we are trying to sort in ascending order and pivot is smallest element and is at the end so we need to traverse through whole list --> O(n)
 * And array will be divided upto n levels so --> O(n)
 * => Total = O(n^2)
 *
 * Method 2
 * First step => n times
 * Next step => n-1
 * .
 * .
 * . => 1
 * Total = n + (n-1) + (n-2) +.....+ 1
 * A.P => Sum = n(n+1) / 2 = (n^2 + n) / 2
 * In Asymptotic Notation : O(n^2)
 */
