// Place tiles of size (1 x m) in a floor of (n x m)
public class PlaceTiles {

  public static int placeTiles(int n, int m) {
    // Base Case 1: if there is a square left, then only 2 ways i.e. place both horizontally, or vertically
    if (n == m) {
      return 2;
    }
    // Base Case 2: if rows are less than columns, it means there is no more space to place vertically, so we have only 1 way as horizontal placement
    if (n < m) {
      return 1;
    }

    // Horizontal placements
    int xPlacements = placeTiles(n - 1, m);
    // Vertical placements
    int yPlacements = placeTiles(n - m, m);

    return xPlacements + yPlacements;
  }

  public static void main(String[] args) {
    int ways = placeTiles(4, 2);
    System.out.println("Total Ways: " + ways);
  }
}
