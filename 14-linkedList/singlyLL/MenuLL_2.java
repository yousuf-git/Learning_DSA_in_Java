/* -----------20 Different Operations on as many number of lists as you want-------------- */
package singlyLL;

import java.util.Scanner;

public class MenuLL_2 {
  public final static String YELLOW = "\u001B[33m";
  public final static String RESET = "\u001B[0m";

  public static void main(String[] args) {
    /*
     * Few Features work on lists with only integer type values in them
     */
    Scanner input = new Scanner(System.in);
    /*
     * -----------20 Different Operations on as many number of lists as you
     * want--------------
     */
    System.out.println(YELLOW
        + "\n \t╠══  20 Different Menus for as many number of lists as you want  ══╣ \n" + RESET);
    System.out.print("\nEnter Limit for Lists Creation: ");
    int listsLimit = input.nextInt();
    @SuppressWarnings("unchecked") // Suppress unchecked warning
    HSinglyLL<String>[] lists = (HSinglyLL<String>[]) new HSinglyLL[listsLimit]; // casted it to an array of HSinglyLL<String>. This cast is safe because we know that all elements of the array will be of type HSinglyLL<String>.

    int listsCount = 0;
    int ch = 0;
    String item;
    int idx, listNum, listIdx, listIdx_2;
    while (ch != 21) {
      System.out.println("\n01. Create List");
      System.out.println("02. Add Item at Last");
      System.out.println("03. Add Item at First");
      System.out.println("04. Remove Item from Last");
      System.out.println("05. Remove Item from First");
      System.out.println("06. Add on a position");
      System.out.println("07. Remove from a position");
      System.out.println("08. Display a List");
      System.out.println("09. Display All Lists");
      System.out.println("10. Display Head and Middle of a List");
      System.out.println("11. Count Items in a List");
      System.out.println("12. Search Item in a List");
      System.out.println("13. Sort a List");
      System.out.println("14. Merge 2 Lists");
      System.out.println("15. Completely Reverse a List");
      System.out.println("16. Reverse 2nd half of a List");
      System.out.println("17. Check If a List Palindrome");
      System.out.println("18. Compare 2 Lists");
      System.out.println("19. Get Sum of a List");
      System.out.println("20. Get Average of a List");
      System.out.println("21. Exit");

      System.out.print(YELLOW + "\nEnter Choice: " + RESET);
      ch = input.nextInt();

      switch (ch) {
        case 1: // create list
          if (listsCount >= listsLimit) {
            System.out.println("List Creation Limit Exceeded :( ");
          } else {
            lists[listsCount] = new HSinglyLL<>();
            char c = 'Y';
            while (c == 'Y' || c == 'y') {
              System.out.print("\nEnter Item to Insert: ");
              item = input.next();
              lists[listsCount].addLast(item);
              System.out.print("Do You want to enter more element (Y/N): ");
              c = input.next().charAt(0);
            }
            System.out.println("List Created Successfully :) ");
            listsCount++;
          }
          break;
        case 2: // addLast
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.print("\nEnter Item to add at last: ");
            item = input.next();
            lists[listIdx].addLast(item);
            System.out.println("Item " + item + " Added at last Successfully :) ");
          }
          break;
        case 3: // addFirst
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.print("\nEnter Item to add at first: ");
            item = input.next();
            lists[listIdx].addFirst(item);
            System.out.println("Item " + item + " Added at first Successfully :) ");
            break;
          }
        case 4: // delLast
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            item = lists[listIdx].removeLast();
            if (item != null) {
              System.out.println("Item " + item + " Removed from last Successfully :) ");
            } else {
              System.out.println("Cannot remove last from Empty List :( ");
            }
          }
          break;
        case 5: // delFirst
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            item = lists[listIdx].removeFirst();
            if (item != null) {
              System.out.println("Item " + item + " Removed from first Successfully :) ");
            } else {
              System.out.println("Cannot remove first from Empty List :( ");
            }
          }
          break;
        case 6: // add at index
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.print("\nEnter Item to add: ");
            item = input.next();
            System.out.print("Enter Index: ");
            idx = input.nextInt();
            lists[listIdx].addAtIndex(idx, item);
          }
          break;
        case 7: // remove from an index
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.print("\nEnter Index from where to remove: ");
            idx = input.nextInt();
            lists[listIdx].removeFrom(idx);
          }
          break;
        case 8: // display a list
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            lists[listIdx].newDisplay();
          }
          break;
        case 9: // display all lists
          int itr = 1;
          for (HSinglyLL<String> list : lists) {
            if (list != null) {
              System.out.println("\nList " + (itr++));
              list.newDisplay();
            }
          }
          break;
        case 10: // diplay head and middle
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.println("\nHead: " + lists[listIdx].getHead().data);
            System.out.println("Middle: " + lists[listIdx].middle(lists[listIdx].getHead()).data);
          }
          break;
        case 11: // count items
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.println("\nElement(s) in list: " + lists[listIdx].getSize());
          }
          break;
        case 12: // search item
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.print("\nEnter Item to search: ");
            item = input.next();
            idx = lists[listIdx].linearSearch(item);
            if (idx != -1) {
              System.out.println("\nItem " + item + " Found at Index " + idx + " :) ");
            } else {
              System.out.println("\nItem " + item + " Not Found :( ");
            }
          }
          break;
        case 13: // sort a list
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            lists[listIdx].bubbleSort();
            System.out.println("\nList Sorted Successfully :) ");
          }
          break;
        case 14: // merge 2 lists
          System.out.print("Enter First List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          System.out.print("Enter Second List Number: ");
          listNum = input.nextInt();
          listIdx_2 = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("First List Number is Invalid :( ");
          } else if (listIdx_2 == -1) {
            System.out.println("Second List Number is Invalid :( ");
          } else {
            lists[listIdx].merge(lists[listIdx_2]);
            System.out.println("\nLists Merged Successfully :) ");
          }
          break;
        case 15: // reverse a list
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            lists[listIdx].setHead(lists[listIdx].iterativeReverse(lists[listIdx].getHead()));
            System.out.println("\nList Reversed Successfully :) ");
          }
          break;
        case 16: // Reverse 2nd half
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            Node<String> headNode = lists[listIdx].getHead();
            Node<String> midNode = lists[listIdx].middle(headNode);
            midNode.next = lists[listIdx].iterativeReverse(midNode.next);
            System.out.println("\n2nd Half List Reversed Successfully :) ");
          }
          break;
        case 17: // check palindrome
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            if (lists[listIdx].isPalindrome()) {
              System.out.println("\nYes, List " + listNum + " is Palindromic :) ");
            } else {
              System.out.println("\nNo, List " + listNum + " is not Palindromic :) ");
            }
          }
          break;
        case 18: // compare lists
          System.out.print("Enter First List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          System.out.print("Enter Second List Number: ");
          listNum = input.nextInt();
          listIdx_2 = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("First List Number is Invalid :( ");
          } else if (listIdx_2 == -1) {
            System.out.println("Second List Number is Invalid :( ");
          } else {
            if (lists[listIdx].equals(lists[listIdx_2])) {
              System.out.println("\nYes, These Lists are equal");
            } else {
              System.out.println("\nNo, These Lists are not equal");
            }
          }
          break;
        case 19: // get sum
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            System.out.println("\nSum = " + lists[listIdx].getSum());
          }
          break;
        case 20:
          System.out.print("Enter List Number: ");
          listNum = input.nextInt();
          listIdx = listIdx(listNum, listsCount);
          if (listIdx == -1) {
            System.out.println("Invalid List Number :( ");
          } else {
            float avg = ((float) lists[listIdx].getSum() / (float) lists[listIdx].getSize());
            System.out.println("Average = " + avg);
          }
          break;
        case 21: // exit
          System.out.println("\n----------Happy Coding, GoodBye Yousuf :) --------- \n");
          break;
        default:
          System.out.println("Invalid Choice <:( ");
          break;
      }
    }
    input.close();
  }

  /* To check if a list number is valid or not, returns its corresponding index */
  public static int listIdx(int listNum, int listCount) {
    if (listNum > listCount || listNum <= 0) {
      return -1; // for invalid
    } else {
      return listNum - 1;
    }
  }
}
