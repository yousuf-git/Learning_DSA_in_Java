/**
 * 
 * Takes a partially filled-in grid and attempts to assign values to all unassigned locations in such a way to meet
 * the requirements for Sudoku solution (non-duplication across rows, columns, and boxes)
 * 
 * Approach : 
 * 1. Start moving from (0, 0) to (N-1, N-1) 2.
 * 
 */
public class Sudoku {
    // N is the size of the 2D matrix N*N
    static int N = 9;

    public static boolean isValid(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] == num) {
                return false;
            }
            if (grid[i][col] == num) {
                return false;
            }
        }
        // grid
        int fr = (row / 3) * 3;
        int fc = (col / 3) * 3;
        for (int r = fr; r < fr + 3; r++) {
            for (int c = fc; c < fc + 3; c++) {
                if (grid[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasSolution(int[][] grid, int row, int col) {
        // Base Case - If we have covered all the rows
        if (row == grid.length) {
            return true;
        }
        int nrow, ncol; // row and col for the next recursive call
        if (col != grid.length - 1) {
            ncol = col + 1;
            nrow = row;
        } else {
            ncol = 0;
            nrow = row + 1;
        }
        if (grid[row][col] != 0) {
            return hasSolution(grid, nrow, ncol);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isValid(grid, row, col, i)) {
                    grid[row][col] = i;
                    if (hasSolution(grid, nrow, ncol)) {
                        return true;
                    } else {
                        grid[row][col] = 0; // BackTrack
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int grid[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0}, {5, 2, 0, 0, 0, 0, 0, 0, 0}, {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0}, {9, 0, 0, 8, 6, 3, 0, 0, 5}, {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0}, {0, 0, 0, 0, 0, 0, 0, 7, 4}, {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        System.out.println("\nGiven Grid\n");
        printGrid(grid);
        if (hasSolution(grid, 0, 0)) {
            System.out.println("\nSolution Grid\n");
            printGrid(grid);
        } else {
            System.out.println("No Valid Solution Found !");
        }
    }

    // Method to print grid in sudoku pattern
    public static void printGrid(int[][] grid) {
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            if (i == 3 || i == 6) {
                System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
            }
            for (int j = 0; j < grid.length; j++) {

                if (j == 3 || j == 6 || j == 9) {
                    System.out.print("| ");
                }
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
            if (i == grid.length - 1) {
                
            }
        }
        System.out.println();
    }
}
// grid[0][3]
// grid[0][4]

/*

1 2 3 | 4 6 8 | 8 8 8 |
1 2 3 | 4 6 8 | 8 8 8 |
1 2 3 | 4 6 8 | 8 8 8 |
¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
1 2 3 | 4 6 8 | 8 8 8 |
1 2 3 | 4 6 8 | 8 8 8 |
1 2 3 | 4 6 8 | 8 8 8 |
¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
1 2 3 | 4 6 8 | 8 8 8 |
1 2 3 | 4 6 8 | 8 8 8 |
1 2 3 | 4 6 8 | 8 8 8 |

*/
