import java.util.Scanner;

class Array {
    public static void main(String[] args) {
        // Array Declaration
        int[] arr1 = { 2, 4, 6, 9 }; // Method 1
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
        // System.out.println(arr1); // address of array

        int[] arr = new int[5]; // Method 2
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 2;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println("index: " + i + " value: " + arr[i]);
        }

        // 2D Array
        // type[][] name = new type[rows][columns]
        int rows = 2;
        int columns = 3;
        int[][] matrix = new int[rows][columns];
        // System.out.println("Length: "+ matrix.length); // 2
        int count = 2;
        // Storing even numbers in the 2D Array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = count;
                count += 2; // To store only even numbers in array
            }
        }
        // Displaying Output
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j]); // i => row, j => column
                System.out.print(" "); // for space after each element
            }
            System.out.println(); // new line
        }

        // Q: Input a matrix from user and then search for a number in it and display its index if found
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter No. of Rows: ");
            rows = sc.nextInt();
            System.out.print("Enter No. of Columns: ");
            columns = sc.nextInt();

            matrix = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print("Input Value for (" + i + ", " + j + "): ");
                    matrix[i][j] = sc.nextInt();

                }
            }
            System.out.println("Enter Number to find: ");
            int num = sc.nextInt();
            boolean isFound = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (matrix[i][j] == num) {
                        System.out.println("Number found at location (" + i + ", " + j + ")");
                        isFound=true;
                        break;
                    }
                }
            }
            if(!isFound){
                System.out.println("Number Not Found !");
            }
        }

    }

}