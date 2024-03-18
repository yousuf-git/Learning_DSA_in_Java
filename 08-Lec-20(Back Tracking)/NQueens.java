// Chessboard of n x n is given place n queens over it so that each one is safe
// Caution: shittiest problem ever

import java.util.ArrayList;
import java.util.List;

public class NQueens {

  // to be called by main function for n Queens

  public static void placeQueens(int n) {
    List<List<String>> allBoards = new ArrayList<>();
    // Create a board of (n x n)
    char[][] board = new char[n][n];
    helper(allBoards, board, 0);
  }

  // Recursive function that is to act as a helper for placeQueens() to check safety and place queens

  public static void helper(
    List<List<String>> allBoards,
    char[][] board,
    int col
  ) {
    // Base Case --> if all columns are done
    if (col == board.length) {
      // save configuration and return
      saveBoard(allBoards, board);
      return;
    }
    for (int i = 0; i < board.length; i++) {
      if (isSafe(board, i, col)) {
        board[i][col] = 'Q';
        helper(allBoards, board, col + 1); // place on other columns
        board[i][col] = '-'; // when control will be returned the previous configuration should be set to default (-)
      }
    }
  }

  // to check if queen is safe at current row & col in board

  public static boolean isSafe(char[][] board, int row, int col) {
    // 1. check towards right
    for (int i = col + 1; i < board.length; i++) {
      if (board[row][i] == 'Q') {
        return false;
      }
    }
    // 2. check towards left
    for (int i = col - 1; i >= 0; i--) {
      if (board[row][i] == 'Q') {
        return false;
      }
    }
    // 3. check towards top
    for (int i = row - 1; i >= 0; i--) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }
    // 4. check towards down
    for (int i = row + 1; i < board.length; i++) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }
    // 5. check towards top-right
    for (
      int i = row + 1, j = col + 1;
      i < board.length && j < board[0].length;
      i++, j++
    ) {
      try {
        if (board[i][j] == 'Q') {
          return false;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
        System.out.println("i: " + i + ", j:" + j);
      }
    }
    // 6. check towards top-left
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      try {
        if (board[i][j] == 'Q') {
          return false;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
        System.out.println("i: " + i + ", j:" + j);
      }
    }
    // 7. check towards bottom-left
    for (int i = row + 1, j = col - 1; i < board.length && col >= 0; i++, j--) {
      try {
        if (board[i][j] == 'Q') {
          return false;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
        System.out.println("i: " + i + ", j:" + j);
      }
    }
    // 8. check towards bottom-right
    for (
      int i = row - 1, j = col + 1;
      i >= 0 && col < board[0].length;
      i--, j++
    ) {
      try {
        if (board[i][j] == 'Q') {
          return false;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
        System.out.println("i: " + i + ", j:" + j);
      }
    }

    // If checked for all 8 directions and there is no queen there than return true that current position(row, col) is safe

    return true;
  }

  // to save current board configuration in allBoards List

  public static void saveBoard(List<List<String>> allboards, char[][] board) {
    String row = "";
    List<String> updatedBoard = new ArrayList<>();

    for (int i = 0; i < board.length; i++) {
      row = "";
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == 'Q') {
          row += 'Q';
        } else {
          row += "-";
        }
      }
      updatedBoard.add(row);
    }
    allboards.add(updatedBoard);
  }

  // --> Main Function
  public static void main(String[] args) {
    int n = 4;
    placeQueens(n);
    // --> For those who didn't use for loop with 2 iterators before
    // for(int i=0, j=0;i<5;i++,j++){
    //     System.out.println("i: "+i +", j:" +j);
    // }

    // --> To understand 2D array
    // int[][] arr = new int[2][4];
    // System.err.println("Length of array: " + arr.length);
    // System.err.println("Length of 1st row: "+ arr[0].length);
  }
}
