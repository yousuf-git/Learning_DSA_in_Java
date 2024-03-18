// Find number of ways in which you can invite n people to a party, as single guest or in pairs

public class WaysToInvite {

  public static int waysToInvite(int n) {
    if (n<=1) {
        return 1;
    }
    // single --> remaining guests are n-1
    int singleChoice = waysToInvite(n - 1);

    // pair --> remaining guests are (n-1) * ways for (n-2)
    int pairChoice = (n-1) * waysToInvite(n - 2);

    return singleChoice + pairChoice;

  }

  public static void main(String[] args) {
    int guests = 3;
    int ways = waysToInvite(guests);
    System.out.println("Total ways to invite "+guests + " guests: "+ ways);
  }
}
