public class KeyPadComb {

  public static String[] keyPad = {
    "_", // 0
    ".", // 1
    "abc", // 2
    "def", // 3
    "ghi", // 4
    "jkl", // 5
    "mno", // 6
    "pqrs", // 7
    "tuv", // 8
    "wxyz", // 9
  };

  public static void printComb(String input, int idx, String comb) {
    if (idx == input.length()) {
      System.out.println(comb);
      return;
    }
    char currentCh = input.charAt(idx);
    String mapping = keyPad[currentCh - '0'];
    for (int i = 0; i < mapping.length(); i++) {
      printComb(input, idx + 1, comb + mapping.charAt(i));
    }
  }

  public static void main(String[] args) {
    // 1. Input numbers as a string and print combinations according to that numbers according to character mapping from keypad

    // To understand how to get combinations from string array using char type index
    // System.out.println(keyPad['2']);
    // System.out.println(keyPad['2'-'0']);

    printComb("10", 0, "");
  }
}
/*/////////////////////////////////////////////////////////////
Time Complexity:
    
    Maximum length = 4 (length of string in mapping)
    ==> Choices = 4
    => Time Complexity = O(4^n) , n is length of input string
    
//////////////////////////////////////////////////////////////
Another keypad pattern:
   public static String[] keyPad = {
    ".", // 0
    "abc", // 1
    "def", // 2
    "ghi", // 3
    "jkl", // 4
    "mno", // 5
    "pqrs", // 6
    "tu", // 7
    "vwx", // 8
    "yz", // 9
    };
/////////////////////////////////////////////////////////////*/
