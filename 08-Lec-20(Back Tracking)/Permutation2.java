// Permutations of a string, same as in lecture 20
public class Permutation2 {
    public static void displayPermutation(String str, String permStr){
        // Base Case
        if (str.length()==0) {
            System.out.println(permStr);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            String updatedStr = str.substring(0, i) + str.substring(i+1);
            
            // current character is choosen to be
            displayPermutation(updatedStr, permStr+currChar);
        }
    }
    public static void main(String[] args) {
        String str = "XYZ";
        displayPermutation(str, "");
        
    }
    
}
