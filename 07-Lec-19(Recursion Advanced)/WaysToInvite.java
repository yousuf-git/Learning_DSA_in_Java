// Find number of ways in which you can invite n people to a party, as single guest or in pairs

public class WaysToInvite {

  public static int waysToInvite(int n) {
    if (n <= 1) {
      return 1;
    }
    // single --> remaining guests are n-1
    int singleChoice = waysToInvite(n - 1);

    // pair --> remaining guests are (n-1) * ways for (n-2)
    int pairChoice = (n - 1) * waysToInvite(n - 2);

    return singleChoice + pairChoice;

  }

  public static void main(String[] args) {
    int guests = 3;
    int ways = waysToInvite(guests);
    System.out.println("Total ways to invite " + guests + " guests: " + ways);
  }
}
/*

Suppose if there are 3 guests => A,B,C

Way :   Single  :   Pair
1   :   A       :   B-C
2   :   B       :   A-C
3   :   C       :   A-B
4   :   A,B,C   :     0

=> So total number of ways appears to be 4
*/
