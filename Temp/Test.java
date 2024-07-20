// package Temp;

// import java.time.Instant;
// import java.util.Arrays;
// import java.util.LinkedList;
// import java.util.List;

// class Test {
//     // To accept variable number of arguments, will accept as Array of string
//     public static void printAll(String... strings) {
//         // for (String s : strings) {
//         //     System.out.println(s);
//         // }
//         Arrays.asList(strings).forEach(System.out::printf);
//     }

//     public static void main(String[] args) {
//         System.out.println("Welcome to Java World !"); // Print as it is
//         int x = 6;
//         System.out.printf("Value of x : %d \n", x); // Print formattted string
//         // System.out.println("Value of x : %d", x); // Error, no arguments accepted
//         String indentedString = "Hello\nWorld".indent(4); // Indent by 4 spaces from left
//         System.out.println(indentedString);

//         printAll("e", "sdkd", "fdkjd");

//         Instant now = Instant.now();
//         System.out.println(now); // Print the current time stamp


//         List<String> list = new LinkedList<>();
//         list.add("hi");
//         list.add("java");
//         list.forEach(System.out::println);

//         System.out.println();


//         List<Integer> list2 = new LinkedList<>();
//         list2.add(1);
//         list2.add(3);
//         list2.add(2);
//         list2.add(4);

//         System.out.print("List: ");
//         list2.forEach(num -> System.out.print(num + " "));
//         System.out.println();
//         System.out.print("Even Numbers in the list: ");
//         list2.forEach(num -> {
//             if (num % 2 == 0) {
//                 System.out.print(num + " ");
//             }
//         });

//         System.out.println();


//     }
// }
package Temp;

// import java.util.LinkedList;
// import java.util.List;
// import graph.Graph;
import javax.swing.JFrame;

/**
 * Test
 */
public class Test extends JFrame {
    Test() {
        setTitle("Test frame");
        setBounds(600, 200, 1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}