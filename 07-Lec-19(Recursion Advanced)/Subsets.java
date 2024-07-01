// Display all subsets of first n natural numbers using recursion

import java.util.ArrayList;

public class Subsets {

  public static int count = 0; // static integer to count subsets

  // Function to display an ArrayList<Integer>
  public static void display(ArrayList<Integer> subset) {
    if (subset.size() == 0) {
      System.out.print("_");
      return;
    }
    System.out.print(subset.get(0));
    for (int i = 1; i < subset.size(); i++) {
      System.out.print(", " + subset.get(i));
    }
    // System.out.println();
  }

  // Recursive Function to print subsets
  public static void printSubsets(int n, ArrayList<Integer> subset) {
    // Base Case
    if (n == 0) {
      count++; // subsets counter
      System.out.print("Subset " + count + ": { ");
      display(subset); // print the subset
      System.out.println(" }");
      return;
    }

    // if elements is choosen to be added in subset
    subset.add(n);
    printSubsets(n - 1, subset);

    // if element is not choosen to be added in subset
    subset.remove(subset.size() - 1); // remove element added recently from last index
    printSubsets(n - 1, subset);
  }

  public static void main(String[] args) {
    ArrayList<Integer> subset = new ArrayList<>();
    int n = 3; // First 3 natural numbers => set = {1,2,3}
    System.out.println("\nSubsets of " + n + " natural numbers:\n");
    printSubsets(n, subset);
    System.out.println("Total Subsets: " + count); // 2^n
  }
}


