public class StringBuilders {
    public static void main(String[] args) {
        StringBuilder name = new StringBuilder("harry");
        // charAt(index)
        System.out.println("Value at index 4: " + name.charAt(4));
        // setCharAt
        name.setCharAt(0, 'B');
        System.out.println("Updated Name: " + name);
        // insert(index, value)
        name.insert(name.length(), "Allen");
        System.out.println(name);
        // delete(start_index, end_index)
        name.insert(0, 'H');
        name.delete(0, 1);
        System.out.println(name);
        // append(String)
        name = new StringBuilder("harry");
        name.append("Potter");
        System.out.println("Updated Name after append: " + name);

        // Q: Reverse a string
        int leftIndex, rightIndex;
        char leftChar, rightChar;
        int iterationCounter = 0;
        for (int i = 0; i < name.length() / 2; i++) {
            leftIndex = i;
            rightIndex = name.length() - 1 - i;

            leftChar = name.charAt(leftIndex);
            rightChar = name.charAt(rightIndex);

            name.setCharAt(leftIndex, rightChar);
            name.setCharAt(rightIndex, leftChar);
            iterationCounter++;
        }
        System.out.println("Reversed FullName: " + name);
        System.out.println("Length of String: " + name.length());
        System.out.println("Number of Iterations: " + iterationCounter);

    }
}
