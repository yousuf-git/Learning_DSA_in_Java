import java.util.Random;

public class MergeSort {

	
	public static void merge(int[] array, int st, int mid, int end) {
		// First we need a new array of size of both arrays to be merged 
		// Size of this new merged array = (end - st + 1)
		// (end - st) gives total number of elements in array, and we know that size it +1 of toal number of elements
		int size = end - st + 1;
		int[] merged = new int[size];

		// array 1 => st --> mid
		// array 2 => mid+1 --> end
		int lIdx = st; // starting index of left array
		int rIdx = mid + 1; // strting index of right array
		int mIdx = 0; // index for storing elementes in merged array
		// Untill both indices are in range
		while (lIdx <= mid && rIdx <= end) {
			// Compare element from left and right array
			if (array[lIdx] < array[rIdx]) {
				merged[mIdx] = array[lIdx];
				lIdx++; // if left one is smaller then increase its index
			} else {
				merged[mIdx] = array[rIdx];
				rIdx++; // if right one is smaller then increase its index
			}
			mIdx++; // index for merged array
		}
		// If any of the left or right array has still elements then simply copy them
		while (lIdx <= mid) {
			merged[mIdx] = array[lIdx];
			mIdx++;
			lIdx++;
		}
		while (rIdx <= end) {
			merged[mIdx] = array[rIdx];
			mIdx++;
			rIdx++;
		}
		// Copy elements from merged to original array
		mIdx = 0;
		for (int i = st; i <= end; i++) {
			array[i] = merged[mIdx];
			mIdx++;
		}

	}

	public static void divide(int[] array, int st, int end) {
		if (st >= end) {
			return;
		}
		// This can cause problem of space complexity if st and end indices are too large
		// int mid = (st + end) / 2;
		// Alternatively,
		int mid = st + (end - st) / 2;
		divide(array, st, mid); // Further Divide the left side
		divide(array, mid + 1, end); // Further Divide the right side
		merge(array, st, mid, end); // Send current indices information for conquering / merging

	}

	public static void mergeSort(int[] array) {
		divide(array, 0, array.length - 1);
	}

	// Optional - To print very larger size arrays
	// public static void printArray(int[] array) {
	// 	// Print the array in chunks
	// 	int chunkSize = 50; // Adjust the chunk size as needed
	// 	System.out.print("[" + array[0]);
	// 	for (int i = 1; i < array.length; i += chunkSize) {
	// 		for (int j = i; j < Math.min(i + chunkSize, array.length); j++) {
	// 			System.out.print(array[j] + " ");
	// 		}
	// 		System.out.print(", "); // To separate the chunks
	// 	}
	// 	System.out.println("]\n");

	// }

	/*------------To print the array in a format--------------*/
	public static void printArray(int[] array) {
		// If array is of size 0
		if (array.length == 0 || array == null) {
			System.out.println("[]");
			return;
		}
		System.out.print("[" + array[0]);
		for (int i = 1; i < array.length; i++) {
			System.out.print(", " + array[i]);

		}
		System.out.println("]\n");
	}

	public static void main(String[] args) {
		System.out.println();
		// To generate array of random integers
		// Use a smaller size for demonstration purposes
		int size = 10; // Adjust this size based on your system's capacity
		int[] array = new int[size];

		Random random = new Random();

		// Fill the array with random integers
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt();
		}
		System.out.print("Original Array: ");
		printArray(array);

		mergeSort(array);

		System.out.print("Sorted Array: ");
		printArray(array);

	}
}

/*
 * Time Complexity:
 * For divide part --> O(log(n))
 * For Conquer / Merge Part --> O(n)
 * 
 * Total = O(nlogn)
 */
