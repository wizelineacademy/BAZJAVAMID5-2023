package paquete4;

public class BankAccount {
    private String holderName;
    private double balance;

    public BankAccount(String holderName, double balance) {
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double withdraw(Transaction transaction) {
        if(balance - transaction.getAmount() > 0) {
            balance -= transaction.getAmount();
            return transaction.getAmount();
        } else {
            throw new RuntimeException();
        }
    }
    public double deposit(Transaction transaction) {
        return balance += transaction.getAmount();
    }
}
