/**
Q: Given a matrix of size n x n, return diagonal sum of both primary and secondary diagonal. Don't add overlapping elements

Example 1:

    {1, 2, 3}
    {4, 5, 6}
    {7, 8, 9}

    Sum of primary diagonal = 1 + 5 + 9 = 15
    Sum of secondary diagonal = 3 + (5 - 5) + 7 = 10        5 is subtracted bcz of overlapping
    Total Sum = 22

Example 2:

    {1, 2, 3, 4}
    {5, 6, 7, 8}
    {9, 10, 11, 12}
    {13, 14, 15, 16}

    Sum of primary diagonal = 1 + 6 + 11 + 16 = 34
    Sum of secondary diagonal = 4 + 7 + 10 + 13 = 34
    Total Sum = 68

Approach:

For Primary Diagonal: row = col
For Secondary Diagonal: row + col = n - 1

if we know value of n and i, we can get j that satisfies condition of secondary diagonal by:
=>    col = n - 1 - row

*/
public class DiagonalSum {
    public static int getDiagonalSum(int[][] matrix) {
        if (matrix.length != matrix[0].length) {
            System.out.println("Invalid Matrix !");
            return 0;
        }
        int sum = 0;
        // Approach 1: Brute Force O(n^2)
        // for (int row = 0; row < matrix.length; row++) {
        //     for (int col = 0; col < matrix[row].length; col++) {
        //         if (row == col) {
        //             sum += matrix[row][col];
        //         } else if (row + col == matrix.length - 1) {
        //             sum += matrix[row][col];
        //         }
        //     }
        // }

        // Approach 2: Optimized -> O(n)
        for (int row = 0; row < matrix.length; row++) {
            // Primary Diagonal Condition
            sum += matrix[row][row]; // row == col
            // Secondary Diagonal Condition -> col = n - 1 - row
            // Check for seconday diagonal only if row != col
            if (row != matrix.length - 1 - row) {
                sum += matrix[row][matrix.length - 1 - row];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int sum = getDiagonalSum(matrix);
        System.out.println("Diagonal Sum: " + sum);
    }
}