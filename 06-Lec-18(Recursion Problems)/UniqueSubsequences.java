import java.util.HashSet;

public class UniqueSubsequences {

  public static void uniqueSubsequences(
    String str,
    int idx,
    String newString,
    HashSet<String> set
  ) {
    // Base Case
    if (idx == str.length()) {
      if (set.contains(newString)) { // To skip duplicated
        return;
      } else {
        System.out.println(newString);
        set.add(newString); // Add the subsequence in the set
        return;
      }
    }
    char currentCh = str.charAt(idx);

    // choice 2 --> Character is included in new String
    uniqueSubsequences(str, idx + 1, newString + currentCh, set);
    // choice 2 --> Character is not included in new String
    uniqueSubsequences(str, idx + 1, newString, set);
  }

  public static void main(String[] args) {
    // 1. Print unique subsequences of a string
    // "aaa" => aaa, aa, aa, aa, a, a, a, _ (Remove duplicate ones)
    //      Finally => aaa, aa, a, _ (This should be the output)
    // For this we will be needing a hashSet to store unique elements
    HashSet<String> hashSet = new HashSet<>();
    uniqueSubsequences("aaa", 0, "", hashSet);
  }
}
