// Display fibnocci series till n
// fibonacci series = 0 1 1 2 3 5 8 11 . . . . --> here first 2 numbers are always 0 and 1
public class DisplayFibonacci {
    
    /* ------------Approach1: Using 3 parameters------------ */
    public static void displayFibonacci(int n, long first, long second, int count) { // here n is 1-based index
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

    /* ------ Approach 2: Using only 1 parameter - This is very slow as n increases------- */
    public static long getFibonacci(int n) { // n is the 0-based index
        // Base case: if n is 0 or 1, return n
        if (n <=1 ) {
            return n;
        }
        // Any n fibonacci number is sum of previous 2 fibonacci numbers => nFib = Fib(n-1) + Fib(n-2)
        return (getFibonacci(n-1) + getFibonacci(n-2));
    }

    /*-----Approach 3: Using only 1 parameter with memoization----------*/
    
    static long[] cache;
    public static long getFib(int n) {
        if(n <= 1) {
            return n;
        }
        // First Check in cache / memo
        if (cache[n] != 0) {
            return cache[n]; // return the stores result
        }
        // Otherwise Calculate the curent 
        long currFib = getFib(n-1) + getFib(n-2);
        cache[n] = currFib;
        return currFib;
    }

    public static void main(String[] args) {
        System.out.println("\n Approach 1: \n");
        // display fibnocci series till n
        // fibonacci series = 0 1 1 2 3 . . . . ; --> here first 2 numbers are always 0 and 1
        int n,count;
        count = 1;
        n = 92; // 92 is the maximum number of terms that can be displayed by using long as data type
        if (n != 0) {
            System.out.print("Fibonacci Series of " + n + " terms: ");
            displayFibonacci(n, 0, 1, count);
        } else if(n<=0) {
            System.out.println("Invalid input for fibonacci series!");
        }
        System.out.println("\n");

        // System.out.println("By Approach 2: " + getFibonacci(93)); // it will get stuck
        cache = new long[n+1];
        System.out.println("By Approach 3: " + getFib(n));
        System.out.println();
    }
}
