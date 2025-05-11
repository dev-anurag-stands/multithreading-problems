package Que3_Odd_Even_Printer;

public class Printer {
    private int count = 1;
    private final int max_count = 100;

    public void printOdd() {
        synchronized (this) {
            while (count <= max_count) {
                while (count % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (count <= max_count) {
                    System.out.print(count+ " ");
                    count++;
                    notify();
                }
            }
        }
    }

    public void printEven() {
        synchronized (this) {
            while (count <= max_count) {
                while (count % 2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (count <= max_count) {
                    System.out.print(count + " ");
                    count++;
                    notify();
                }
            }
        }
    }
}
