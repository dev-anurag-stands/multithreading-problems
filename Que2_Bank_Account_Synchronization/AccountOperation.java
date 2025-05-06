package Que2_Bank_Account_Synchronization;

public class AccountOperation extends Thread {
    private BankAccount bank;
    private String methodName;
    private double amount;

    // Constructor accepts only the BankAccount object, method name, amount, and thread name
    AccountOperation(BankAccount bank, String methodName, double amount, String threadName) {
        super(threadName);
        this.bank = bank;  // bank object already has the account number
        this.methodName = methodName;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (methodName.equalsIgnoreCase("deposit")) {
            bank.deposit(amount);
        } else if (methodName.equalsIgnoreCase("withdraw")) {
            bank.withdraw(amount);
        }
    }
}
