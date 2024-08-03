public class Pairs {
    public static void printPairs(int[] array) {
        int total = 0; // total pairs counter
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                total++;
                System.out.print("(" + array[i] + "," + array[j] + ")");
            }
            System.out.println();
        }
        System.out.println("Total Pairs: " + total);
    }

    public static void main(String[] args) {
        System.out.println();
        int[] array = {2, 4, 6, 9, 10};
        printPairs(array);
        System.out.println();
    }
}

/*

Total Pairs = n(n-1) / 2

Example: if n = 5

    5(5-1) / 2
=   20/2
=   10

 */
