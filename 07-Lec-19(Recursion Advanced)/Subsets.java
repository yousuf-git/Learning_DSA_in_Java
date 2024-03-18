// Display all subsets of first n natural numbers using recursion

import java.util.ArrayList;

public class Subsets {

  public static int count = 0;

  // Function to display an ArrayList<Integer>
  public static void displayList(ArrayList<Integer> subset) {
    for (int i = 0; i < subset.size(); i++) {
      System.out.print(subset.get(i) + " ");
    }
    System.out.println();
  }

  // Recursive Function to print subsets
  public static void printSubsets(int n, ArrayList<Integer> subset) {
    // Base Case
    if (n == 0) {
      count++; // subset counter
      System.out.println("Subset " + count + ":");
      displayList(subset);
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
    int n = 3; // 
    printSubsets(n, subset);
    System.out.println("Total Subsets: " + count); // 2^n
  }
}
