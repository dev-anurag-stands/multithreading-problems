package Que3_Odd_Even_Printer;

public class Printer {
    private int count = 1;
    private final int max_count = 100;
    private boolean isEven = false;

    public int getCount(){
        return count;
    }

    public void printEven(){
        try {
            while (count <= max_count) {
                while (!isEven) {
                    wait();
                }
            }

            if(count<=max_count){
                System.out.println(count++ + " ");
                count++;
                isEven = false;
                notify();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void printOdd(){
        try {
            while (count <= max_count) {
                while (!isEven) {
                    wait();
                }
            }

            if(count<=max_count){
                System.out.println(count + " ");
                count++;
                isEven = false;
                notify();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
