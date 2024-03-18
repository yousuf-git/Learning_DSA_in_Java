import java.util.Scanner;

public class Bit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int index, bitMask, num = 5; // 0101
        // Get Bit at index 2
        System.out.print("Input Index whose bit is to be checked: ");
        index = scanner.nextInt();
        bitMask = 1 << index;
        if ((bitMask & num) == 0) {
            System.out.println("Bit at index " + index + " is 0");
        } else {
            System.out.println("Bit at index " + index + " is 1");
        }
        // Set Bit at index 3 (Set always sets the specific bit from original value to
        // 1, either original bit is 0 or 1 already)
        bitMask = 1 << 3;
        int setNum = bitMask | num; // ==> 1000 | 0101 = 1101 => 13
        System.out.println("Num After Setting bit at index 1: " + setNum); // 13

        // clear Bit at index (always sets the specific bit from original value to 0,
        // either original bit is 1 or 0 already)
        // here setNum is 13 = 1101, I'm going to clear bit at index 3 and make it 0101
        // = 5
        bitMask = 1 << 3; // 1000
        int clearNum = ~(bitMask) & setNum; // ==> 0111 & 1101 = 0101 => 5
        System.out.println("setNum after clearing bit at index 3: " + clearNum); // 5

        // update bit
        // here num = 5 = 0101, lets update bit at index 1 (i.e., from 0 to 1)
        System.out.print("Enter Index to update : ");
        index = scanner.nextInt();
        if (getBit(num, index) == 0) {
            num = setBit(num, index);
        } else {
            num = clearBit(num, index);
        }
        System.out.println("Num After update: "+ num);

        scanner.close();
    }

    // Bit Manipulation Functions down here

    public static int getBit(int num, int index) {
        int bitMask = 1 << index;
        if ((bitMask & num) == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    public static int setBit(int num, int index) {
        int bitMask = 1 << index;
        return bitMask | num;
    }

    public static int clearBit(int num, int index) {
        int bitMask = 1 << index;
        return ~bitMask & num;
    }
}
