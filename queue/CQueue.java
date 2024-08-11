/*------------- Implementation of circular queue ------------- */
package queue;

public class CQueue {
	private int size;
	private String[] elements;
	private int f;
	private int r;

	/* ---------------Constructor----------------- */
	CQueue(int size) {
		f = r = -1;
		this.size = size;
		elements = new String[this.size];
		// Fill the queue with empty spaces, remove this if using simple display
		for (int i = 0; i < elements.length; i++) {
			elements[i] = " ";
		}
		System.out.println("Queue of Size " + this.size + " initiated.\n");
	}

	public boolean isEmpty() {
		return f == -1;
	}
	public boolean isFull() {
		return f == (r + 1) % size;
	}

	/* --------------Display current values of front and rear--------------- */
	public void printPointers() {
		System.out.println("Front : " + f);
		System.out.println("Rear : " + r);
	}

	/* --------------Insert Element from Rear--------------- */
	public void insert(String item) {
		// Queue is full if front is just ahead of rear
		if (f == (r + 1) % size) {
			System.out.println("Queue is Full !");
		} else {
			// If size = 5 (max index = 4) and r = 4 so next value of r should be 0; (4 + 1)
			// % 5 = 0
			r = (r + 1) % size;
			elements[r] = item;
			System.out.println("Insert Successful :) \n");
			if (f == -1) {
				f = 0;
			}
		}
	}

	/* --------------Delete Element from front--------------- */
	public String del() {
		String val = null;
		if (f == -1) {
			System.out.println("Queue is Empty !");

		} else {
			val = elements[f];
			// elements[f] = " "; // to show that element has been removed
			if (f == r) {
				f = -1;
				r = -1;
			} else {
				f = (f + 1) % size;
			}

		}

		return val;
	}

	public void display() {
		if (f == -1) {
			System.out.println("Queue is Empty, Cannot Display !");

		} else {
			System.out.print("Values in Queue: ");

			int i = f;
			while (i != r) {
				System.out.print(elements[i] + " ");
				i = (i + 1) % size;
			}
			System.out.print(elements[i]);
			System.out.println(); // new line
		}
	}

	/*------------- Some private functions that will be called by newDisplay() method --------*/
	private void displayFront() {
		System.out.print("  "); // 2 spaces in each case
		for (int i = 0; i <= f; i++) {
			if (i != f) {
				for (int j = 0; j < 3 + elements[i].length(); j++) {
					System.out.print(" "); // spaces based on length of current element
				}
			} else { // if i == f then there will be less spaces and then F will be displayed
				for (int j = 0; j <= elements[i].length(); j++) {
					System.out.print(" ");
				}
			}
		}
		System.out.print("F\n");
	}

	private void upperBound() {
		System.out.print("--");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 3 + elements[i].length(); j++) {
				System.out.print("-");
			}
		}
		System.out.print("---\n  |");
	}

	private void elements() {
		for (int i = 0; i < size; i++) {
			System.out.print(" " + elements[i] + " |");
		}
	}

	private void lowerBound() {
		System.out.print("\n--");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 3 + elements[i].length(); j++) {
				System.out.print("-");
			}
		}
		System.out.print("---");
		System.out.println();
	}

	private void displayRear() {
		System.out.print("  ");
		for (int i = 0; i <= r; i++) {
			if (i != r) {
				for (int j = 0; j < 3 + elements[i].length(); j++) {
					System.out.print(" ");
				}
			} else {
				for (int j = 0; j <= elements[i].length(); j++) {
					System.out.print(" ");
				}
			}
		}
		System.out.print("R\n");
	}

	/* -----------New Display Method for Circular Queue----------- */
	public void newDisplay() {
		if (f == -1) {
			System.out.println("F");
			upperBound();
			elements();
			lowerBound();
			System.out.println("R");
		} else {
			displayFront();
			upperBound();
			elements();
			lowerBound();
			displayRear();
		}
	}
}