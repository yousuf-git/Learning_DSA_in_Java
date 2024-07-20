/**
Q: Given a matrix, print it in spiral order Example: 
    [1, 2, 3]
    [4, 5, 6]
    [7, 8, 9]

Output: 1 2 3 6 9 8 7 4 5

Approach:

- In each iteration we are covering 2 rows and 2 columns
Top Row:
Traverse on same row by increasing column

Right Col:
Traverse on same col by increasing row

Bottom Row:
Traverse on same row by decreasing column

Left Col:
Traverse on same col by decreasing row

Important Conditions:

1. The number printed for top row, shouldn't be repeated for bottom row, if row bounds are equal
2. The number printed for right col, shouldn't be repeated for left col, if col bounds are equal

Reference: https://leetcode.com/problems/spiral-matrix

*/

public class SpiralMatrix {
    public static void printSpiral(int[][] matrix) {
        // Suppose: Top-row, Bottom-row, Left-column, Right-column
        int topRow = 0;
        int btmRow = matrix.length - 1;
        int leftCol = 0;
        int rightCol = matrix[0].length - 1;
        while (topRow <= btmRow && leftCol <= rightCol) {
            try {
                // System.out.println("\nTop Row: ");
                for (int col = leftCol; col <= rightCol; col++) {
                    System.out.print(matrix[topRow][col] + " ");
                }

                // System.out.println("\nRight Column: ");
                for (int row = topRow + 1; row <= btmRow; row++) {
                    System.out.print(matrix[row][rightCol] + " ");
                }

                // System.out.println("\nBottom Row: ");
                for (int col = rightCol - 1; col >= leftCol; col--) {
                    // The number printed for top row, shouldn't be repeated for bottom row, if row bounds are equal
                    if (topRow == btmRow) {
                        break;
                    }
                    System.out.print(matrix[btmRow][col] + " ");
                }

                // System.out.println("\nLeft Column: ");
                for (int row = btmRow - 1; row >= topRow + 1; row--) {
                    // The number printed for right col, shouldn't be repeated for left col, if col bounds are equal
                    if (leftCol == rightCol) {
                        break;
                    }
                    System.out.print(matrix[row][leftCol] + " ");
                }
                // Update pointers / bounds
                topRow++;
                btmRow--;
                leftCol++;
                rightCol--;
            } catch (Exception e) {
                // In case of any error, bounds / pointers will be display so you can check which is causing error
                System.out.println(e);
                System.out.println("Top Row:" + topRow);
                System.out.println("Bottom Row:" + btmRow);
                System.out.println("Left Col:" + leftCol);
                System.out.println("Right Col:" + rightCol);
                break;
            }
        }
        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {
        // int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // Output: 1 2 3 6 9 8 7 4 5

        // int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // Output: 1 2 3 4 8 12 11 10 9 5 6 7

        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        // Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10

        System.out.print("\nSpiral Matrix: ");
        printSpiral(matrix);
    }
}
