class RecursionQs {

  // 1. For Tower of Hanoi
  public static void TransferDisks(
    int n,
    String src,
    String helper,
    String dest
  ) {
    // Base Case: if there is 1 disk --> S to D
    if (n == 1) {
      System.out.println(n + " Disk Transfered from " + src + " to " + dest); // 1 Disk, Directly transfer from source to destination
      return;
    }
    // Step 1: n-1 disks from S to H, using D as helper
    TransferDisks(n - 1, src, dest, helper); // if n-1 > 1 than it will futher divide its tasks to 3 steps

    // Step 2: Remaining 1 disk from source to destination directly
    System.out.println(1 + " Disk Transfered from " + src + " to " + dest);

    // Step 3: n-1 disks from H to D
    TransferDisks(n - 1, helper, src, dest); // if n-1 > 1 than it will futher divide its tasks to 3 steps
    // Time Complexity --> O(2^n)
  }

  // 2. For reverse of string
  public static void reverseString(String str, int idx) {
    if (idx == 0) { // Base Case
      System.out.println(str.charAt(idx));
      System.out.println(); // new line
      return;
    }
    // if (idx==-1) { // Another Base case
    //   System.out.println(); // new line
    //   return;
    // }

    System.out.print(str.charAt(idx));
    reverseString(str, idx - 1);
    // Time Complexity --> O(n)
  }

  // 3. Occurance of Element
  public static void findOccurance(
    String str,
    char ch,
    int idx,
    int fIdx,
    int lIdx
  ) {
    if (idx == str.length()) { // Base Case
      if (lIdx!=-1) {
        System.out.println("Last Occurance at index: " + lIdx);
        return;
      } else {
        System.out.println("Character Not found !");
        return;
      }
    }
    if (str.charAt(idx) == ch) {
      if (fIdx == -1) {
        fIdx = idx;
        System.out.println("First Occurance at Index: " + fIdx);
      }
      lIdx = idx;
    }
    findOccurance(str, ch, idx + 1, fIdx, lIdx);

    // Time Complexity --> O(n) where n is length of string
  }

  public static void main(String[] args) {
    // 1. Tower of Hanoi
    int n = 4;
    TransferDisks(n, "S", "H", "D");

    // 2. Print string in reverse order
    String name = "YRRAH";
    reverseString(name, name.length() - 1);
    // 3. Print First and Last occurance of an element in string
    String str = "Harry James Potter";
    findOccurance(str, 'e', 0, -1, -1);
  }
}
