/*
Statement: 
    Given a positive integer n, generate an n x n matrix filled with elements from 1 to (n^2) in spiral order.

Reference: https://leetcode.com/problems/spiral-matrix-ii

 */

public class SpiralMatrix2 {

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int tr = 0;
        int br = n - 1;
        int lc = 0;
        int rc = n - 1;

        int val = 1;
        while (tr <= br && lc <= rc) {
            // top row
            for (int col = lc; col <= rc; col++) {
                res[tr][col] = val++;
            }
            // right col
            for (int row = tr + 1; row <= br; row++) {
                res[row][rc] = val++;
            }

            // bottom row
            for (int col = rc - 1; col >= lc; col--) {
                if (tr == br) {
                    break;
                }
                res[br][col] = val++;
            }
            // left col
            for (int row = br - 1; row >= tr + 1; row--) {
                if (lc == rc) {
                    break;
                }
                res[row][lc] = val++;
            }
            // update pointers
            tr++;
            br--;
            lc++;
            rc--;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = generateMatrix(n);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

}
