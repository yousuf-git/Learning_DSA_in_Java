public class MergeSortSelf {

  public static void divide(int[] array) {
    if (array.length <= 1) { // Base Case
      return;
    }
    int mid = (array.length) / 2;
    int leftSize = mid;
    int rightSize = array.length - mid;

    // Creating new arrays according to their sizes
    int[] leftArray = new int[leftSize];
    int[] rightArray = new int[rightSize];

    // indices to track left and right arry
    int l = 0, r = 0;

    // Filling values in both new arrays
    for (int i = 0; i < array.length; i++) {
      if (i < mid) {
        leftArray[l] = array[i];
        l++;
      } else {
        rightArray[r] = array[i];
        r++;
      }
    }
    // Further dividing left and right arrays
    divide(leftArray);
    divide(rightArray);

    // Merging arrays, I have named conquer, you can use merge as well
    conquer(leftArray, rightArray, array);
  }

  public static void conquer(int[] leftArray, int[] rightArray, int[] array) {
    // Determine the size of left and right arrays
    int leftSize = (array.length) / 2;
    int rightSize = array.length - leftSize;

    // Indices to track left, right and original array
    // int i = 0, l = 0, r = 0;
    int i = 0;
    int l = 0;
    int r = 0;

    // Compare elements of right and left arrays and fill in original array
    while (r < rightSize && l < leftSize) {
      if (leftArray[l] < rightArray[r]) {
        array[i++] = leftArray[l++];
        // array[i] = leftArray[l];
        // i++;
        // l++;
      } else {
        array[i++] = rightArray[r++];
        // array[i] = rightArray[r];
        // r++;
        // i++;
      }
    }
    // if one of the array is done while other is left than simply fill all of its elements to original one
    while (r < rightSize) {
      array[i++] = rightArray[r++];
      //   array[i] = rightArray[r];
      //   r++;
      //   i++;
    }
    while (l < leftSize) {
      array[i++] = leftArray[l++];
      //   array[i] = leftArray[l];
      //   l++;
      //   i++;
    }
  }

  public static void main(String[] args) {
    int[] array = { 2, 5, 6, 2, 8, 1, 9, 2, 3 };

    System.out.println("Original Array: ");
    for (int element : array) {
      System.out.print(element + ", ");
    }

    divide(array);

    System.out.println("\nSorted Array: ");
    for (int element : array) {
      System.out.print(element + ", ");
    }
  }
}
