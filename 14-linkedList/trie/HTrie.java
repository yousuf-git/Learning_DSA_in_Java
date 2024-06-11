package trie;

// import java.util.LinkedList;

// import singlyLL.HSinglyLL;

public class HTrie {
    TNode root;

    HTrie() {
        root = new TNode();
        root.children = new TNode[26];
        root.isEOW = false;
    }

    /*
     * Insert Method() --> O(L)
     * - Iterate over the given string
     * - If character doesn't exist in next level, create new node and insert
     * - If character exixts already, skip it
     */
    public void insert(String word) {
        if (word.length() == 0) {
            return;
        }
        TNode currRoot = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i); // this will give the current character
            int idx = ch - 'a'; // this will give corresponding index of character
            if (currRoot.children[idx] == null) {
                // create new node
                TNode node = new TNode();
                // store the new node at that index of character
                currRoot.children[idx] = node;
                if (i == word.length() - 1) { // for last character of word
                    currRoot.children[idx].isEOW = true;
                }
            }
            currRoot = currRoot.children[idx]; // update the current root
        }
    }

    /*
     * Seacrch Method() --> O(L)
     * - Iterate over the given string
     * - If character doesn't exist in next level return false
     * - If character exixts and given word is ended but in trie node isEOW is false
     * => return false
     * - Otherwise return true
     */
    public boolean search(String word) {
        if (word.length() == 0) {
            return true;
        }
        TNode currRoot = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (currRoot.children[idx] == null) {
                return false;
            }
            if (i == word.length() - 1) { // last character
                return currRoot.children[idx].isEOW; // returns true if in trie there is also EOW = true
            }
            currRoot = currRoot.children[idx]; // update the current root
        }
        return true;
    }

    // public void display() {
    // if (root == null) {
    // return;
    // }
    // TNode rootNode = root;
    // for (int i = 0; i < rootNode.children.length; i++) {
    // TNode node = rootNode.children[i];

    // if (node == null) {
    // return;
    // } else {
    // char ch = 'a' + i;

    // }

    // }
    // }
    /*-----------Recursive function to solve word break problem----------- */
    public boolean wordBreak(String str) {
        if (str.length() == 0) {
            return true;
        }
        // if len = 5, max index = 4 but i will goto 5 because in substring i itself is
        // not included
        for (int i = 1; i <= str.length(); i++) {
            String firstStr = str.substring(0, i); // first char
            String secondStr = str.substring(i, str.length()); // remaining
            if (this.search(firstStr) && wordBreak(secondStr)) {
                // if first part eixsts in trie as a single word itslef and second can alse be
                // break down
                return true;
            }
        }
        return false; // if string is iterated and not both conditions are true at same time
    }

    /*-----------Function to solve unique substring problem----------- */
    public int countNodes(TNode root) {
        if (root == null) {
            return 0;
        }
        int count = 1; // count the root itself
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += countNodes(root.children[i]);
            }
        }
        return count;
    }

    /*-----------Function to solve longest prefix problem----------- */
    public static String ans = "";

    public void longestWord(TNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].isEOW) {
                char ch = (char) (i + 'a'); // type casting int to char
                sb.append(ch);
                System.out.println(sb +" ..");
                if (sb.length() > ans.length()) {
                    ans = sb.toString();
                    System.out.print(ans + " .");
                }
                longestWord(root.children[i], sb);
                sb.deleteCharAt(sb.length() - 1); // remove last character 
            }
        }
    }

    public static void main(String[] args) {
        HTrie dict = new HTrie();
        String[] words = { "hello", "its", "harry", "learning", "trie", "ds" };
        for (String word : words) {
            dict.insert(word);
        }
        if (dict.search("hello")) {
            System.out.println("found");
        } else {
            System.out.println("not found !");
        }
        /*
         * Word-Break Problem
         * 1. Given a String like "ilovejava", and a set of words
         * 2. Check if this whole string can be broken in such a way that
         * all words exixst in the given set of words
         */
        String[] wordsSet = { "i", "like", "love", "python", "java" };
        HTrie trie = new HTrie();
        for (String word : wordsSet) { // build trie by given words
            trie.insert(word);
        }
        if (trie.wordBreak("ilovejava")) {
            System.out.println("String can be broken");
        } else {
            System.out.println("String cannot be broken !");
        }

        /*
         * Question:
         * Count Unique Substrings
         * Given: ababa
         * All Possible Substrings
         * a ab aba abab ababa
         * b ba bab baba ""
         * a ab aba
         * b ba
         * a
         * Some of them are repeating so removing them
         * a ab aba abab ababa
         * b ba bab baba ""
         * => Unique Substrings = 10
         */
        /*
         * SOlution
         * Find all suffix of given word and create trie out of it
         * Count nodes of trie
         */
        String givenStr = "ababa";
        trie = new HTrie();
        System.out.print("All Suffix of " + givenStr + ": ");
        for (int i = 0; i < givenStr.length(); i++) {
            trie.insert(givenStr.substring(i)); // suffix
            System.out.print(givenStr.substring(i) + " ");
        }
        System.out.println("\nUnique Substrings: " + trie.countNodes(trie.root));

        /*
         * Question
         * Longest Word will all prefixes
         * Given Words :
         * {"a", "banana", "app", "appl", "ap", "apply", "apple"}
         */
        /*
         * Solution
         * Prefixes of apple: "a", "ap", "app", "appl", "apple", ""
         * Prefixes of apply: "a", "ap", "app", "appl", "apply", ""
         * All their prefixes exist
         */
        String[] prefixSet = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
        HTrie trie_2 = new HTrie();
        for (String prefix : prefixSet) {
            trie_2.insert(prefix);
        }
        System.out.println(trie_2.countNodes(trie_2.root));
        // HSinglyLL<String> ans = new HSinglyLL<>();
        // Boolean isAns;
        // for (String prefix : prefixSet) {
        // isAns = true;
        // for (int i = 0; i < prefix.length(); i++) {
        // String subString = prefix.substring(0, prefix.length()-i);
        // if (!trie.search(subString)) {
        // isAns = false;
        // break; // goto next word in prefix set
        // }
        // }
        // if (isAns) {
        // ans.add(prefix);
        // }
        // }
        // while (!ans.isEmpty()) {
        // String str = ans.delLast();
        // System.out.println(str);
        // }
        trie_2.longestWord(trie_2.root, new StringBuilder(""));
        System.out.println(ans);

    }
}
