/*------------- Implementation of circular queue ------------- */

package queue;

public class SQueue {

	private String[] elements;
	private int size;
	private int r, f;

	SQueue(int size) {
		r = f = -1;
		this.size = size;
		elements = new String[this.size];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = " ";
		}
		System.out.println("Queue Initialized of size " + this.size);
	}

	public void insert(String item) {
		if (r == size - 1) {
			System.out.println("\nCannot Insert, Queue is Full :( ");
		} else {
			// If we are adding first element
			if (f == -1) {
				f = 0;
			}
			r = r + 1; // update rear
			elements[r] = item;
			System.out.println("\nInsert Successful :) ");
		}
	}

	/* --------------Delete Element from front--------------- */
	public String del() {
		if (f == -1 || f == r + 1) { // if queue is empty
			return null;
		} else {
			String item = elements[f];
			elements[f] = " "; // to just show that item has been removed
			f += 1;
			if (f == size) {
				f = r = -1;
			}
			return item;
		}
	}

	/* ---------------Simple Version of display----------- */
	public void display() {
		if (f == -1 || f == r + 1) {
			System.out.println("Queue Empty, Nothing to display :( ");
		} else {
			System.out.println("Front : " + f + "\tRear: " + r);
			System.out.println("Index   : Item");
			for (int i = f; i <= r; i++) {
				if (f == r) {
					System.out.println(i + "\t: " + elements[i] + "\t(Front) , (Rear)");
				} else if (i == f) {
					System.out.println(i + "\t: " + elements[i] + "\t(Front)");
				} else if (i == r) {
					System.out.println(i + "\t: " + elements[i] + "\t(Rear)");
				} else {
					System.out.println(i + "\t: " + elements[i]);
				}
			}
		}
	}

	/*------------- Some private functions that will be called by newDisplay() method --------*/

	private void displayFront() {
		System.out.print("  ");
		for (int i = 0; i <= f; i++) {
			if (i != f) {
				for (int j = 0; j < 3 + elements[i].length(); j++) {
					System.out.print(" ");
				}
			} else {
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

	/* -----------New Display Method for linear queue----------- */

	public void newDisplay() {
		if (f == -1 || f == r + 1) {
			System.out.println("\nF");
			upperBound();
			elements();
			lowerBound();
			System.out.println("R");
		} else {
			System.out.println();
			displayFront();
			upperBound();
			elements();
			lowerBound();
			displayRear();
		}
	}
}
