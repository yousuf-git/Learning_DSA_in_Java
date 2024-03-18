public class RecursionQs3 {

  public static void subsequences(String str, int idx, String newString) {
    // Base Case
    if (idx == str.length()) {
      System.out.println(newString);
      return;
    }
    char currentCh = str.charAt(idx);

    // choice 2 --> Character is included in new String
    subsequences(str, idx + 1, newString + currentCh);
    // choice 2 --> Character is not included in new String
    subsequences(str, idx + 1, newString);
  }

  public static void main(String[] args) {
    // 1. Print subsequences of a string
    // "abc" => abc, ab, bc, ac, a, b, c, _
    subsequences("wxyz", 0, "");
  }
}
/*//////////////////////////////////////////

Time Complexity of subsequences:
Total subsequences = (choices)^(number of elements)
    ~ Since choices are 2 and n elements
Total subsequences = (2)^(n)

Nodes at last level = 2^n = (2)^(n-1) * 2
    Each second last node made 2 calls
One level before --> (2)^(n-1)
Two level before --> (2)^(n-2)
Three level before --> (2)^(n-3)
.
.
.
Last Node --> 1

Total function calls = 2^n + (2)^(n-1) + (2)^(n-2) + (2)^(n-3) +.....+1
G.P = a(r^n-1) / (r-1)
G.P = 1(2^n-1) / (2-1)
    = [(2)^(n+1)] - 1
In asymtotus notations --> ignoring constants (+1, -1)
Time Complexity = O(2^n)

/////////////////////////////////////////*/
