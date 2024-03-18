public class Recursion {

  public static void printNum(int n) {
    if (n > 10) { // Base case
      return;
    }
    System.out.print(n + " ");
    printNum(n + 1); // New function layer in stack
  }

  public static void printSum(int n, int count, int s) {
    if (count > n) {
      System.out.println("Sum: " + s);
      return;
    }
    s = s + count;
    printSum(n, count + 1, s);
    // System.out.println(count); // to check how functions will be removed from stack
  }

  public static void showFactorial(int n, int count, int f) {
    if (count > n || n == 1 || n == 0) {
      System.out.println("Factorial of " + n + " is " + f);
      return;
    }
    f = f * count;
    showFactorial(n, count + 1, f);
  }

  public static int calFactorial(int n, int f) {
    if (n == 0 || n == 1) {
      return 1;
    }
    f = n * calFactorial(n - 1, f);
    return f;
  }

  public static void main(String[] args) {
    // disply first 10 numbers without loop
    int num = 1;
    printNum(num);
    System.out.println(); // for new line

    // display sum of n natural numbers
    int n = 5; // user input can also be used
    int sum = 0;
    int count = 1;
    printSum(n, count, sum);

    // display factorial of n
    // n = 5 and count = 1 (already)
    int factorial = 1;
    showFactorial(n, count, factorial);
    System.out.println("By Proper Recursion according to stack:");
    System.out.println("Factorial of " + n + " is " + calFactorial(n, factorial));
  }
}
