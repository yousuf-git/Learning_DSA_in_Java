public class DisplayFibonacci {
    // display fibnocci series till n
    // fibonacci series = 0 1 1 2 3 . . . . ; --> here first 2 numbers are always 0 and 1
    public static void displayFibonacci(int n, int first, int second, int count) {
        // System.out.println("First: "+first);
        // System.out.println("Second: " + second);
        if (count == 1) {
            System.out.print(first); // 0 will always be displayed
            if (n == count) // if number of terms is 1 than no need to proceed
                return;
        }
        if (count == n) { // Base case, count is terms counter
            return;
        }
        System.out.print(" " + second);
        // System.out.println("Iteration "+count);
        displayFibonacci(n, second, first + second, ++count);
    }

    public static void main(String[] args) {
        // display fibnocci series till n
        // fibonacci series = 0 1 1 2 3 . . . . ; --> here first 2 numbers are always 0 and 1
        int n,count;
        count = 1;
        n = 8;
        if (n != 0) {
            System.out.print("Fibonacci Series of " + n + " terms: ");
            displayFibonacci(n, 0, 1, count);
        } else if(n<=0) {
            System.out.println("Invalid input for fibonacci series!");
        }
        System.out.println();
    }
}
