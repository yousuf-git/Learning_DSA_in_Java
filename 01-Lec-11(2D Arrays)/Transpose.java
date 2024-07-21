/**
Q: Given a 2D array of size n x m. Return a 2D array of size m x n which is transpose of original 

__________________ Example __________________

Given:

    {1, 2},
    {3, 4},
    {5, 6}

Transpose:
    {1, 3, 5}
    {2, 4, 6}

*/

public class Transpose {
    // Time Complexity: O(n*m)
    public static int[][] transpose(int[][] matrix) {
        int[][] transpose = new int[matrix[0].length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            int tCol = row;
            int tRow = 0;
            for (int col = 0; col < matrix[row].length; col++) {
                transpose[tRow][tCol] = matrix[row][col];
                tRow++;
            }
        }
        return transpose;
    }

    public static void main(String[] args) {
        System.out.println();
        // int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
        // int[][] matrix = {{1, 2, 3, 4, 5, 6}};
        int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};

        System.out.println("Original Matrix: ");
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        int[][] transpose = transpose(matrix);
        System.out.println("Transpose Matrix: ");
        for (int row = 0; row < transpose.length; row++) {
            for (int col = 0; col < transpose[row].length; col++) {
                System.out.print(transpose[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}