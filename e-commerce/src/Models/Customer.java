package Models;
public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Customer name cannot be null or empty");
        }
        if (balance < 0) {
            throw new Exception("Balance cannot be negative");
        }
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasSufficientBalance(double amount) {
        return amount > 0 && amount <= balance;
    }

    public void addToBalance(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void deductBalance(double amount) throws Exception {
        if (amount < 0) {
            throw new Exception("Amount cannot be negative");
        }
        if (!hasSufficientBalance(amount)) {
            throw new Exception("Insufficient balance");
        }
        balance -= amount;
    }
    
    @Override
    public String toString() {
        return "Customer Name: " + name + ", Balance: " + balance+ "\n";
    }

}
