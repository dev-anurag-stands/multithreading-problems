package Que2_Bank_Account_Synchronization;

public class Main {
    public static void main(String[] args) {
        // Create BankAccount instances with account numbers
        BankAccount acc1 = new BankAccount(101);  // Account number 101
        BankAccount acc2 = new BankAccount(202);  // Account number 202

        // Threads for acc1
        AccountOperation t1 = new AccountOperation(acc1, "deposit", 500, "Alice-Acc1");
        Thread t2 = new AccountOperation(acc1, "withdraw", 200, "Bob-Acc1");

        // Threads for acc2
        Thread t3 = new AccountOperation(acc2, "deposit", 1000, "Charlie-Acc2");
        Thread t4 = new AccountOperation(acc2, "withdraw", 300, "Diana-Acc2");

        // Start all threads simultaneously
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
