package Que2_Bank_Account_Synchronization;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    private final int accountNumber;
    private double balance = 0;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        log("deposited " + amount + ", updated balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            log("withdrew " + amount + ", remaining balance: " + balance);
        } else {
            log("encountered insufficient balance");
        }
    }

    public double getBalance() {
        return balance;
    }

    private void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] (Account #" + accountNumber + ", Thread: " +
                Thread.currentThread().getName() + ") " + message);
    }
}
