// Count all paths from (0, 0) to (n, m)

public class MazePaths {

  public static int n, m;

  public static int countPaths(int i, int j) {
    // if any of row or column is going out of bounds
    if (i == n || j == m ) {
      return 0;
    }
    // if both i,j reach at end 
    if (i==n-1 && j==m-1) { 
        return 1;
    }
    // Total paths from (i,j) to (n, m) = total paths towards right + total paths towards down

    int rightPaths = countPaths(i, j+1); // increase column
    int downPaths = countPaths(i + 1, j); // increase row

    return  rightPaths + downPaths;
    
  }

  public static void main(String[] args) {
    n = 3;
    m = 3;
    int paths = countPaths(0, 0);
    System.out.println("Total Paths: " + paths);
  }
}
