import java.util.Scanner;

class ComplexQuestions {

  // For Stack Height --> n

  public static int calPower(int num, int pow) {
    if (pow == 0) { // First Base case
      return 1;
    }
    if (num == 0) { // Second Base Case
      return 0;
    }
    int product = calPower(num, pow - 1); // 2^5 = 2 * 2^4 = 2 * 2 * 2^3...
    int numProduct = num * product;
    return numProduct;
  }

  // For height of stack --> log n

  public static int calPow(int num, int pow) {
    if (pow == 0) { // Base Case 1
      return 1;
    }
    // if (pow == 1) { // Base Case 2
    //   return num;
    // }
    if (num == 0) { // Base Case 2
      return 0;
    }
    // If power is even
    if (pow % 2 == 0) {
      System.out.println("Even Block");
      return (calPow(num, pow / 2) * calPow(num, pow / 2));
    } else { // If Power is odd
      // return (calPow(num, pow / 2) * calPow(num, pow / 2) * calPow(num, 1));
      System.out.println("Odd Block");
      return (calPow(num, pow / 2) * calPow(num, pow / 2) * num); // same
    }
  }

  public static void main(String[] args) {
    // Q: Print x power n (x^n) --> stack height = n
    Scanner scanner = new Scanner(System.in);
    int x, n; // x --> num; n --> power
    x = 3;
    n = 4;
    // For user input
    // System.out.println("Enter Number: ");
    // x = scanner.nextInt();
    // System.out.println("Enter Power: ");
    // n = scanner.nextInt();
    System.out.println("By Stack height n");
    System.out.println(calPower(x, n));

    // Q: Print x power n (x^n) --> stack height = log(n)
    System.out.println("By Stack height log(n)");
    System.out.println(calPow(x, n));

    scanner.close();
  }
}
