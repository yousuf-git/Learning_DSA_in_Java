public class Strings {

  public static void main(String[] args) {
    String firstName = "Harry";
    String lastName = "Potter";
    System.out.println(firstName + " " + lastName);

    // Comparing Strings => First different characters are compared; a < b < c < d < ...

    if (firstName.compareTo(lastName) == 0) { // false, H != P
      System.out.println(firstName + " is equal to " + lastName);
    } else if (firstName.compareTo(lastName) > 0) { // flase, H < P
      System.out.println(firstName + " is greater than " + lastName);
    } else { // it will be executed
      System.out.println(firstName + " is smaller than " + lastName);
    }
    String name1 = "harry";
    String name2 = "harry";

    if (name1 == name2) {
      System.out.println("Names are equal");
    } else {
      System.out.println("Names are not equal");
    }

    if (new String("harry") == new String("harry")) {
      System.out.println("Names are equal");
    } else {
      System.out.println("Names are not equal"); // else part will be executed bcz objects have diff behavior
    }

    // String Methods
    // System.out.println(firstName.length()); // get length as integer

    String fullName = firstName.concat(lastName); // concating 2 strings
    System.out.println("Full Name: " + fullName);

    // substring(start_index, end_index) // value at end_index is not included
    System.out.println(
      "SubString of fullName: " + fullName.substring(5, fullName.length())
    );
    // Strings are immutable => no modification in same string, we need new string
    // to store modified version

  }
}
