package linkedList;

import java.util.Scanner;

public class MenuLL {

    public static void main(String[] args) {
        System.out.println("\n\t----------Menu Based program to manipulate a single linked list----------\n");
        /*
         * Features
         * 1. Create List
         * 2. Add Last
         * 3. Add First
         * 4. Remove Last
         * 5. Remove First
         * 6. Add on position
         * 7. Remove from position
         * 8. Display
         * 9. Sort
         * 10. Search
         * 11. Count
         * 12. Exit
         */
        HSinglyLL list = new HSinglyLL();
        Scanner input = new Scanner(System.in);
        int ch = 0;
        String item;
        int idx;
        while (ch != 12) {
            System.out.println("\n1. Create List");
            System.out.println("2. Add Last");
            System.out.println("3. Add First");
            System.out.println("4. Remove Last");
            System.out.println("5. Remove First");
            System.out.println("6. Add on position");
            System.out.println("7. Remove from position");
            System.out.println("8. Display List");
            System.out.println("9. Sort List");
            System.out.println("10. Search Item");
            System.out.println("11. Count");
            System.out.println("12. Exit");

            System.out.print("\nEnter Choice: ");
            ch = input.nextInt();

            switch (ch) {
                case 1:
                    char c = 'Y';
                    while (c == 'Y' || c == 'y') {
                        System.out.print("\nEnter Item to Insert: ");
                        item = input.next();
                        list.addLast(item);

                        System.out.print("Do You want to enter more element (Y/N): ");
                        c = input.next().charAt(0);
                    }
                    System.out.println("List Created Successfully :) ");
                    break;
                case 2:
                    System.out.print("\nEnter Item to add at last: ");
                    item = input.next();
                    list.addLast(item);
                    System.out.println("Item " + item + " Added at last Successfully :) ");
                    break;
                case 3:
                    System.out.print("\nEnter Item to add at first: ");
                    item = input.next();
                    list.addFirst(item);
                    System.out.println("Item " + item + " Added at first Successfully :) ");
                    break;
                case 4:
                    item = list.delLast();
                    if (item != null) {
                        System.out.println("Item " + item + " Removed from last Successfully :) ");
                    } else {
                        System.out.println("Cannot remove last from Empty List :( ");
                    }
                    break;
                case 5:
                    item = list.delFirst();
                    if (item != null) {
                        System.out.println("Item " + item + " Removed from first Successfully :) ");
                    } else {
                        System.out.println("Cannot remove first from Empty List :( ");
                    }
                    break;
                case 6:
                    System.out.print("\nEnter Item to add: ");
                    item = input.next();
                    System.out.print("Enter Index: ");
                    idx = input.nextInt();
                    list.addAtIndex(idx, item);
                    break;
                case 7:
                    System.out.print("\nEnter Index from where to remove: ");
                    idx = input.nextInt();
                    list.delFromIdx(idx);
                    break;
                case 8:
                    // list.display();
                    list.newDisplay();
                    break;
                case 9:
                    list.bubbleSort();
                    break;
                case 10:
                    System.out.print("\nEnter Item to search: ");
                    item = input.next();
                    idx = list.linearSearch(item);
                    if (idx != -1) {
                        System.out.println("\nItem " + item + " Found at Index " + idx + " :) ");
                    } else {
                        System.out.println("\nItem " + item + " Not Found :( ");
                    }
                    break;
                case 11:
                    System.out.println("\nElement(s) in list: " + list.getSize());
                    break;
                case 12:
                System.out.println("\n----------Happy Coding, GoodBye Yousuf :) --------- \n");
                    break;
                default:
                    System.out.println("Invalid Choice <:( ");
                    break;
            }
        }
        input.close();
    }
}
