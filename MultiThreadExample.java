public class MultiThreadExample {
  public static int[][][] arrays;
  public static int size = 100000000; // 10 crore

  public static void main(String[] args) {
    // Initialize arrays
    for (int i = 0; i < arrays.length; i++) {
      for (int j = 0; j < arrays.length; j++) {
        arrays[i][j] = new int[size];
      }
    }
    // for (int i = 0; i < arrays.length; i++) {
    // for (int j = 0; j < size; j++) {
    // arrays[i][j] = (int) (Math.random()*100);
    // }
    // }
    // Create two threads
    Thread[] threads = new Thread[5];
    int count = 1;
    for (Thread thread : threads) {
      thread = new Thread(new MyRunnable("Thread " + count++, arrays[0][1], size));
      thread.start();
    }
    // Start the threads
    

  }
}

class MyRunnable implements Runnable {

  private String threadName;

  public MyRunnable(String threadName, int[] array, int size) {
    this.threadName = threadName;
  }

  public void createArray(int[] array, int size) {

  }

  @Override
  public void run() {
    for (int i = 1; i <= 5; i++) {
      System.out.println(threadName + ": Count " + i);

      try {
        // Simulate some work being done by sleeping for a random amount of time
        // Thread.sleep((long) (Math.random() * 1000));
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println(threadName + " finished.");
  }
}
