// For self revision

public class QuickSortRevision {

    public static int getPivot(int[] array, int st, int end) {
        // using pivot as ending index
        int pivot = end;
        int i = st - 1; // index to make place for elements smaller than pivot
        for (int j = st; j <= end; j++) {
            if (array[j] < array[pivot]) {
                i++;
                // swap => array[i] <--> array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i++; // correct place of pivot
        // Swap => array[end] <--> array[i]
        int temp = array[i];
        array[i] = array[end];
        array[end] = temp;

        return i;
    }

    public static void quickSort(int[] array, int st, int end) {
        // for valid start and ending indices, call recursively
        if (st < end) {
            int pivot = getPivot(array, st, end);
            // left of pivot
            quickSort(array, st, pivot - 1);
            // right of pivot
            quickSort(array, pivot + 1, end);
        }

    }

    public static void main(String[] args) {
        int[] array = {2, 4, 5, 10, -1, 0, 6, 9, 1};

        System.out.print("\nOriginal Array: ");
        for (int element : array) {
            System.out.print(element + " ");
        }

        quickSort(array, 0, array.length - 1);

        System.out.print("\nSorted Array: ");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println("\n");
    }

}
