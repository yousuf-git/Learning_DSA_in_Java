// Display all possible permutations of string "xyz"
class Permutation {

  public static void displayPerm(String str, String permStr) {
    if (str.length() == 0) { // Base case
      System.out.println(permStr);
      return;
    }

    for (int i = 0; i < str.length(); i++) {
      char currentChar = str.charAt(i); // character to be fixed
      String newStr = str.substring(0, i) + str.substring(i + 1); // string of characters other than choosen one
      displayPerm(newStr, permStr + currentChar);
    }
    /* Time Complexity:
        For first call there are n choices
        For next n-1 choices
        .
        .
        .
        Total calls = n * (n-1) * (n-2) *.....* 1
        => Time Complexity = O(n!)
    */ 
  }

  public static void main(String[] args) {
    displayPerm("xyz", "");
  }
}
