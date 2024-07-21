// Count all paths from (0, 0) to (m-1, n-1)

public class UniquePaths {

  public static int n, m;
  private static int[][] memo;

  // Method-1 : Without Memoization, Time Complexity: O(2^(m+n))
  public static int countPaths(int i, int j) {
    // if any of row or column is going out of bounds
    if (i == n || j == m) {
      return 0;
    }
    // if both i,j reach at end 
    if (i == n - 1 && j == m - 1) {
      return 1;
    }
    // Total paths from (i,j) to (n, m) = total paths towards right + total paths towards down

    int rightPaths = countPaths(i, j + 1); // increase column
    int downPaths = countPaths(i + 1, j); // increase row

    return rightPaths + downPaths;

  }

  // Method-2 : With Memoization, Time Complexity: O (m x n) 
  // Memoized version is preferred by avoiding recomputation of already solved subproblems
  public static int countPaths(int row, int col, int m, int n) {
    if (row >= m || col >= n) {
      return 0; // Out of bounds
    }
    if (row == m - 1 && col == n - 1) {
      return 1; // Reached destination
    }
    if (memo[row][col] > 0) {
      return memo[row][col]; // Return already computed value (saves time)
    }
    // Calculate number of paths by moving right and down
    int rightPaths = countPaths(row, col + 1, m, n);
    int downPaths = countPaths(row + 1, col, m, n);
    memo[row][col] = rightPaths + downPaths; // Store result in memo table
    return memo[row][col];
  }

  public static void main(String[] args) {
    m = 3; // rows
    n = 2; // cols
    memo = new int[m][n]; // Initialize memoization table with 0 (default)

    int paths = countPaths(0, 0, m, n);
    System.out.println("Total Paths: " + paths);
  }
}
/*
________Example_______

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

*/
// https://leetcode.com/problems/unique-paths/
