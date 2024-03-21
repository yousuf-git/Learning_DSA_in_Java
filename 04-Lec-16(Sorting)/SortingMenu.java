/* -------------Menu Based program that provides facility to create / update array
 * then apply sorting algorithm of user choice ------------------
 */

import java.util.Scanner;

public class SortingMenu {
  static Scanner scanner;
  static final String RESET = "\u001B[0m";
  static final String BLUE = "\u001B[36m";
  static final String YELLOW = "\u001B[33m";

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    System.out.print(BLUE + " \nEnter Size of Array: " + RESET);
    int[] array = new int[scanner.nextInt()];
    fillArray(array);
    int choice = 0;
    while (choice != 6) {

      System.out.println("\n1. Bubble Sort");
      System.out.println("2. Selection Sort");
      System.out.println("3. Insertion Sort");
      System.out.println("4. Display Array");
      System.out.println("5. Updated Array");
      System.out.println("6. Exit");
      System.out.print(BLUE + "\nEnter Choice: " + RESET);
      choice = scanner.nextInt();
      switch (choice) {
        case 1:
          /* ------Bubble Sort----- */
          bubbleSort(array);
          System.out.println("Array Sorted By Bubble sort :) ");
          break;
        case 2:
          /* ------Selection Sort----- */
          selectionSort(array);
          System.out.println("Array Sorted By Selection sort :) ");
          break;
        case 3:
          /* ------Insertion Sort----- */
          insertionSort(array);
          System.out.println("Array Sorted By Insertion sort :) ");
          break;
        case 4:
          newDisplay(array);
          // displayArray(array);
          break;
        case 5:
          fillArray(array);
          break;
        case 6:
          System.out.println("--------------Happy Coding, Harry :) ------------");

          break;
        default:
          System.out.println("Invalid Input Bruh <:( ");
          break;
      }

    }

    scanner.close();
  }

  /*
   * --------To Fill values in an array, can be an existing one so it will be
   * updated--------
   */
  public static void fillArray(int[] array) {
    System.out.println("Fill " + array.length + " Elements in array: ");
    for (int i = 0; i < array.length; i++) {
      array[i] = scanner.nextInt();
    }
    System.out.println("Array Filled :) ");
  }

  /* ------------To diplay current values in an array------------ */
  public static void displayArray(int[] array) {
    for (int val : array) {
      System.out.print(val + " ");
    }
  }

  /* ------------To sort array in ascending order by bubble sort------------ */
  public static void bubbleSort(int[] array) {
    if (array.length == 1) {
      return;
    } else {
      int temp;
      int innerCount, outerCount = 1, totalItr = 0;
      for (int i = 0; i < array.length - 1; i++) {
        /*
         * compare prev element with next, there is no next of last element
         * so I have used array.length-1 instead of whole array i.e., array.length
         * and i is elements counter not index
         */
        innerCount = 1;
        for (int j = 0; j < (array.length - 1) - i; j++) {
          System.out.println("\nArray After Inner Iteration " + (innerCount++));
          // for each iteration if previous element is greater than next, swap them
          if (array[j] > array[j + 1]) {
            // swap
            temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
          newDisplay(array);
          totalItr++; // total iterations counter
        }
        System.out.println(YELLOW + "\nArray After Outer Iteration " + RESET + (outerCount++));
        newDisplay(array);
        totalItr++; // total iterations counter
      }
      System.out.println("\nTotal Iterations: " + totalItr);
    }
  }

  /* ------------To sort array in ascending order by selection sort------------ */
  public static void selectionSort(int[] array) {
    if (array.length == 1) {
      return;
    } else {
      int smallIdx, swap, innerCount, outerCount = 1, totalItr = 0;
      for (int i = 0; i < array.length - 1; i++) {
        smallIdx = i; // index of smallest valu, will be updated if any other element is smaller
        innerCount = 1; // inner loop counter
        for (int j = i + 1; j < array.length; j++) {
          System.out.println("\nArray After Inner Iteration " + (innerCount++));
          if (array[j] < array[smallIdx]) {
            // update smallest value index
            smallIdx = j;
          }
          newDisplay(array);
          System.out.println("Smallest: " + array[smallIdx]);
          totalItr++; // total iterations counter
        }
        // swap current Index (i) val with smallest Index (j) val
        swap = array[i];
        array[i] = array[smallIdx];
        array[smallIdx] = swap;
        System.out.println(YELLOW + "\nArray After Outer Iteration " + RESET + (outerCount++));
        newDisplay(array);
        totalItr++; // total iterations counter
      }
      System.out.println("\nTotal Iterations: " + totalItr);
    }
  }

  /* ------------To sort array in ascending order by insertion sort------------ */
  public static void insertionSort(int[] array) {
    if (array.length == 1) {
      System.out.println("Array is Already Sorted !");
    } else {
      int innerCount, outerCount = 1, totalItr = 0;
      int element, sortedIdx;

      for (int i = 1; i < array.length; i++) {
        element = array[i]; // retrieve the current item
        sortedIdx = i - 1; // sorted portion starts from just behind current i
        innerCount = 1; // inner loop counter
        while (sortedIdx >= 0 && element < array[sortedIdx]) {
          array[sortedIdx + 1] = array[sortedIdx];
          sortedIdx--;
        } // end of while
        System.out.println("\nArray After Inner Iteration " + (innerCount++));
        newDisplay(array);
        totalItr++; // total iterations counter
        array[sortedIdx + 1] = element;
        System.out.println(YELLOW + "\nArray After Outer Iteration " + RESET + (outerCount++));
        newDisplay(array);
        // displayArray(array);
        totalItr++; // total iterations counter
      } // end of for
      System.out.println("\nTotal Iterations: " + totalItr);
    }
  }

  /* ------------To Visually diplay current values in an array------------ */
  public static void newDisplay(int[] array) {
    System.out.println(BLUE);
    try {
      System.out.print("_");
      for (int val : array) {
        for (int i = 0; i < 3 + String.valueOf(val).length(); i++) {
          Thread.sleep(100);
          System.out.print("_");
        }
      }
      System.out.print("\n│");
      for (int val : array) {
        Thread.sleep(100);
        System.out.print(" ");
        Thread.sleep(200);
        System.out.print(RESET + val);
        Thread.sleep(100);
        System.out.print(BLUE + " │");
      }
      System.out.print("\n¯");
      for (int val : array) {
        for (int i = 0; i < 3 + String.valueOf(val).length(); i++) {
          Thread.sleep(100);
          System.out.print("¯");
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(RESET);
  }
}
