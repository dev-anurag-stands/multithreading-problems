package Que3_Odd_Even_Printer;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);

        oddThread.start();
        evenThread.start();
    }
}
