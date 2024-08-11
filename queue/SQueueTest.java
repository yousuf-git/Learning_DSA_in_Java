package queue;

import java.util.Scanner;

public class SQueueTest {

	public static void main(String[] args) {
		System.out.print("\nEnter Size of Queue: ");
		Scanner input = new Scanner(System.in);
		int size = input.nextInt();
		SQueue queue = new SQueue(size);

		int ch = 0;
		// String item;
		while (ch != 4) {
			System.out.println("\n1. Insert");
			System.out.println("2. Delete");
			System.out.println("3. Display");
			System.out.println("4. Quit");
			System.out.print("Enter Choice: ");
			try {
				ch = input.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Input :<( ");
			}
			System.out.println(); // new line
			switch (ch) {
				case 1:
					System.out.print("Enter Item to insert: ");
					String item = input.next();
					queue.insert(item);
					break;
				case 2:
					String delItem = queue.del();
					if (delItem != null) {
						System.out.println("Deleted Item: " + delItem);
					} else {
						System.out.println("Cannot Delete, Queue is Empty :( ");
					}
					break;
				case 3:
					// queue.display();
					queue.newDisplay();
					break;
				case 4:
					System.out.println("--------- Happy Coding, Goodbye Yousuf ;) -----");
					break;
				default:
					System.out.println("Invalid Choice >:( \n ");
			}
		}
		input.close();
	}
}
