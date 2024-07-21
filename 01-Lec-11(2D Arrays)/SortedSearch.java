/**
Q: Given a row-wise and col-wise sorted matrix (2D array), Find given key in it

Approaches:

1. Linear Search              O(n^2)
2. Row-wise Binary Search     O(n log n)
3. Staircase search           O(n+m) -> O(n) if n very much greater than m, and vice versa
    In this case we can consider one of 2 cells
    - top right     Its lower cells are greater and left cells are smaller
    - bottom left   Its upper cells are smaller and right cells are greater

    Start from either of these cells and move in respective direction according to value of key, untill key is found or we are out of bounds
*/

public class SortedSearch {
    public static int[] linearSearch(int[][] matrix, int key) {
        int[] coordinates = new int[2];
        coordinates[0] = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == key) {
                    coordinates[0] = row;
                    coordinates[1] = col;
                }
            }
        }
        return coordinates;
    }

    // Helper for binary search
    public static int helper(int[] row, int key, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (row[mid] == key) {
                return mid;
            } else if (key < row[mid]) {
                return helper(row, key, left, mid - 1);
            } else {
                return helper(row, key, mid + 1, right);
            }
        }
        return -1;
    }

    public static int[] binarySearch(int[][] matrix, int key) {
        int[] coordinates = new int[2];
        coordinates[0] = -1;
        for (int row = 0; row < matrix.length; row++) {
            // Call binary search helper for each row
            int idx = helper(matrix[row], key, 0, matrix[0].length - 1);
            if (idx != -1) {
                coordinates[0] = row;
                coordinates[1] = idx; // idx will be as a col
                return coordinates;
            }
        }
        return coordinates;
    }

    // Helper for staircase search
    public static int[] helper(int[][] matrix, int key, int row, int col, int[] cdnts) {
        // Base case
        if (row < 0 || col > matrix[row].length - 1) {
            return cdnts;
        }

        if (key < matrix[row][col]) {
            // move top
            return helper(matrix, key, row - 1, col, cdnts);
        } else if (key > matrix[row][col]) {
            // move right
            return helper(matrix, key, row, col + 1, cdnts);
        } else { // key == matrix[row][col]
            cdnts[0] = row;
            cdnts[1] = col;
            return cdnts;
        }
    }

    public static int[] staircaseSearch(int[][] matrix, int key) {
        int[] coordinates = new int[2];
        coordinates[0] = -1;
        // Start from bottom left corner
        int row = matrix.length - 1;
        int col = 0;
        return helper(matrix, key, row, col, coordinates);
    }

    public static void main(String[] args) {
        System.out.println();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int key = 6;
        // int[] coordinates = linearSearch(matrix, key);
        // int[] coordinates = binarySearch(matrix, key);
        int[] coordinates = staircaseSearch(matrix, key);
        
        if (coordinates[0] != -1) {
            System.out.println(key + " Found at (" + coordinates[0] + ", " + coordinates[1] + ")");
        } else {
            System.out.println(key + " not found in given matrix :( ");
        }
        System.out.println();
    }
}