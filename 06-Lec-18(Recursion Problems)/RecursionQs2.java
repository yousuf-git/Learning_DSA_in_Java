public class RecursionQs2 {

  // 1. Function to check if Array is sorted
  public static boolean isSorted(int[] arr, int idx) {
    if (idx == arr.length - 1) { // Base Case 1, all indexes are checked
      return true;
    }
    if (arr[idx] > arr[idx + 1]) { // Base Case 2, unsorted
      return false;
    } else {
      return isSorted(arr, idx + 1);
    }
  }

  // 2. Move all character (ch) in a string to the end
  public static String moveToEnd(
    String str, // original string
    char ch, // character which is to be moved
    int idx, // current index
    int count, // character counter
    String newString // new string to be formed
  ) {
    // Base case-1 : If idx has reached end, attach the character (ch) count times with the new string
    if (idx == str.length()) {
      for (int i = 1; i <= count; i++) {
        newString += ch;
      }
      return newString;
    }
    // If current character of original str is ch, don't append in the new string, just increase its count
    if (str.charAt(idx) == ch) {
      count++;
      return moveToEnd(str, ch, idx + 1, count, newString); // recurisive call for next index
    } else { // Otherwise, append the character in the new string
      newString += str.charAt(idx);
      return moveToEnd(str, ch, idx + 1, count, newString); // recurisive call for next index
    }
    // Time Complexity:
    // One time complete traversing in string --> O(n)
    // Then count times loop --> O(count), count can be maximum to n
    //    Final value = O(2n)
    //    Removing constant in Asymtotic Notation ~ O(n)
    // Finally --> O(n) where n is length of string
  }

  // 3. Function to remove duplicate characters from a string
  public static String removeDuplicate(
    String str, // Original String
    int idx, // Current Index
    String newString // New String where there is no duplication
  ) {
    // Base case : If index has reached to end of string
    if (idx == str.length()) {
      return newString;
    }
    // If character at current index in original string is not in newString
    if (newString.indexOf(str.charAt(idx)) == -1) {
      newString += str.charAt(idx); // Append the character in the new string
    }
    return removeDuplicate(str, idx + 1, newString); // recursive call
    // Traversed string single time
    // Time Complexity --> O(n)
  }

  public static void main(String[] args) {
    // 1. Check if an array is sorted
    int[] array = { 2, 4, 43, 100 }; // I have made function to check ascending order
    if (isSorted(array, 0)) { // Function call
      System.out.println("Array is Sorted");
    } else {
      System.out.println("Array is not sorted");
    }

    // 2. Move all x in a string to the end
    String str = "abcbdjxxxxdbdxbsdx";
    char ch = 'x';
    str = moveToEnd(str, ch, 0, 0, "");
    System.out.println("Moved "+ch+ " at end: "+ str);

    // 3. Remove duplicate characters from a string
    String name = "Harry James";
    String newName = removeDuplicate(name, 0, "");
    System.out.println("New Name after deleting duplicate characters: "+ newName);

  }
}
