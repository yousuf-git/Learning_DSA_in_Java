// Chessboard of n x n is given place n queens over it so that each one is safe
// Caution: shittiest problem ever - but yes I finally did it !!!
// https://leetcode.com/problems/n-queens/
// ___________________________________________________

import java.util.ArrayList;
import java.util.List;

class NQueens {
	public static List<List<String>> solutions;
	public static int solutionsCount = 0;

	public static boolean isSafe(String[][] board, int row, int col) {
		// Check if there is any Queen in the same row 
		// (optional, bcz if queen is placed in a row, we'll recursively check for next one instantly) 
		// for (int c = col; c >= 0; c--) {
		// 	if (board[row][c] == "Q") {
		// 		return false; // not safe
		// 	}
		// }
		// Check if there is any Queen in the same col
		for (int r = row; r >= 0; r--) {
			if (board[r][col] == "Q") {
				return false; // not safe
			}
		}
		// Check if there is any Queen in the top left diagoanl
		int r = row;
		int c = col;
		// Repeat untill row and columns are in range
		while (r >= 0 && c >= 0) {
			if (board[r][c] == "Q") {
				return false; // not safe
			}
			// Move top left in the diagonal
			r -= 1;
			c -= 1;
		}

		// Check if there is any Queen in top right diagonal, First reset row and col
		// pointers
		r = row;
		c = col;
		while (r >= 0 && c < board[0].length) {
			if (board[r][c] == "Q") {
				return false; // not safe
			}
			// Move top right in the diagonal
			r -= 1;
			c += 1;
		}
		return true; // Checked all possible risks; current (row, col) is safe
	}

	// To add solution in the list of solutions, once found
	public static void addSolution(String[][] board) {
		// Going to add a new solution
		// ArrayList of string, each string represents a row
		List<String> solution = new ArrayList<>();
		// Iterate over the board
		for (int row = 0; row < board.length; row++) {
			StringBuilder rowSb = new StringBuilder();
			for (int col = 0; col < board[row].length; col++) {
				rowSb.append(board[row][col]); // add character in the row
			}
			solution.add(rowSb.toString()); // add row in current solution
		}
		solutions.add(solution); // save the finalized solution board
		solutionsCount++;
	}

	public static boolean placeQueen(String[][] board, int row) {
		// Base case: If row has gone out of range
		if (row >= board.length) {
			addSolution(board); // save the solution
			return true; // solution found
		}
		boolean hasSolution = false;
		// Check for possibility of placing the queen in each col in the given row
		for (int col = 0; col < board[row].length; col++) {
			// Check if current (row, col) is safe
			if (isSafe(board, row, col)) {
				board[row][col] = "Q"; // place the queen here
				// Check for other rows below
				hasSolution = placeQueen(board, row + 1) || hasSolution;
				// hasSolution will be updated if placeQueen() returns true even 1 time
				// return true; // can be used if only one solution is required
				// But we need all possible solutions so we need to reset the current position and continue.
				board[row][col] = "."; // backtracking
			}
		}
		return hasSolution;
	}

	public static List<List<String>> solveQueens(int n) {
		solutions = new ArrayList<>(); // initialize the solutions list

		for (List<String> list : solutions) {
			list = new ArrayList<>(); // initialize the lists in the solutions, as empty ArrayLists
		}
		String[][] board = new String[n][n];
		// Fill the board with dots (.)
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ".";
			}
		}
		placeQueen(board, 0); // Start placing the queens from the very first row
		return solutions; // return the list of solutions
	}

	public static void main(String[] args) {
		
		int n = 4; // Number of queens and size of board
		System.out.println("\nTotal Queens: " + n);
		System.out.println("\n__________Solution Boards_________\n");
		List<List<String>> solutions = solveQueens(n);
		
		// Display the solutions in a formatted way
		for (List<String> solution : solutions) { // Get each solution from the list of solutions
			for (int i = 0; i < solution.size(); i++) { // Solution is actually a board that has rows as Strings
				String row = solution.get(i); // Get each row
				for (int j = 0; j < row.length(); j++) { // Iterate over the row
					System.out.print(row.charAt(j) + " "); // print the individual entry of row
				}
				System.out.println(); // Next line after each row
			}
			System.out.println(); // Empty line after printing each solution
		}
		System.out.println("\nTotal Solutions: " + solutionsCount);
		System.out.println();
	}
}