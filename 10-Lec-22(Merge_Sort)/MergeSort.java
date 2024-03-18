public class MergeSort {

  public static void conquer(
    int[] array,
    int startIdx,
    int midIdx,
    int endIdx
  ) {
    int[] mergedArray = new int[endIdx - startIdx + 1];
    int idx1 = startIdx; // for start of left array
    int idx2 = midIdx+1; // for start of right array
    int m = 0; // for merged array

    // Placing elements in merged array after sorting
    while (idx1 <= midIdx && idx2 <= endIdx) {
      if (array[idx1] <= array[idx2]) {
        mergedArray[m++] = array[idx1++];
      } else {
        mergedArray[m++] = array[idx2++];
      }
    }
    while (idx1 <=midIdx) {
      mergedArray[m++] = array[idx1++]; 
    }
    while (idx2 <=endIdx) {
      mergedArray[m++] = array[idx2++]; 
    }

    // merged array j --> 0 to merge.length
    // original array i --> startIdx to merge.length
    for (int i = startIdx, j = 0; j < mergedArray.length ; i++, j++) {
      array[i] = mergedArray[j];
    }
  }

  public static void divide(int[] array, int startIdx, int endIdx) {
    if (startIdx >= endIdx) {
      return;
    }
    // int midIdx = (startIdx + endIdx)/2; may result in space complexity
    int midIdx = startIdx + (endIdx - startIdx) / 2;

    divide(array, startIdx, midIdx);
    divide(array, midIdx + 1, endIdx);
    conquer(array, startIdx, midIdx, endIdx);
  }

  public static void main(String[] args) {
    int[] array = { 2, 3, 4, 5, 1, 4, 3 };
    
    System.out.println("Original Array:");
    for (int element : array) {
      System.out.print(element + " ");
    }
    
    divide(array, 0, array.length - 1);
    
    System.out.println("\nSorted Array:");
    for (int element : array) {
      System.out.print(element + " ");
    }
  }
}

/*
 * Time Complexity:
 * For divide part --> O(log(n))
 * For Conquer/Merge Part --> O(n)
 * 
 * Total = O(nlogn)
 */
