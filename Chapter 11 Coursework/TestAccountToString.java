import java.util.Date;

public class TestAccountToString {
    public static void main(String[] args) {
        Account regularAccount = new Account(1122, 20000.00);
        Account.setAnnualInterestRate(4.5);

        CheckingAccount checkingAccount = new CheckingAccount(1123, 1500.00, 500.00);

        SavingsAccount savingsAccount = new SavingsAccount(1124, 5000.00);

        System.out.println("             ACCOUNT TOSTRING() OUTPUT            ");
        
        System.out.println(regularAccount.toString());
        System.out.println("--------------------------------------------------");
        System.out.println(checkingAccount.toString());
        System.out.println("--------------------------------------------------");
        System.out.println(savingsAccount.toString());
    }
}

class Account {
    private int id;
    private double balance;
    private static double annualInterestRate = 0;
    private Date dateCreated;

    public Account() {
        this.id = 0;
        this.balance = 0.0;
        this.dateCreated = new Date();
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public static double getAnnualInterestRate() { return annualInterestRate; }
    public static void setAnnualInterestRate(double rate) { annualInterestRate = rate; }

    public Date getDateCreated() { return dateCreated; }

    public double getMonthlyInterestRate() { return (annualInterestRate / 100) / 12; }
    public double getMonthlyInterest() { return balance * getMonthlyInterestRate(); }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) { balance += amount; }
    }

    @Override
    public String toString() {
        return String.format("[Account Base] ID: %d | Balance: $%,.2f | Created: %s", 
                id, balance, dateCreated.toString());
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int id, double balance, double overdraftLimit) {
        super(id, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() { return overdraftLimit; }
    public void setOverdraftLimit(double overdraftLimit) { this.overdraftLimit = overdraftLimit; }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (getBalance() - amount >= -overdraftLimit)) {
            setBalance(getBalance() - amount);
        }
    }

    @Override
    public String toString() {
        return String.format("[Checking Account] ID: %d | Balance: $%,.2f | Overdraft Limit: $%,.2f", 
                getId(), getBalance(), overdraftLimit);
    }
}

class SavingsAccount extends Account {

    public SavingsAccount(int id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (getBalance() - amount >= 0)) {
            setBalance(getBalance() - amount);
        }
    }

    @Override
    public String toString() {
        return String.format("[Savings Account] ID: %d | Balance: $%,.2f (Protected from Overdraft)", 
                getId(), getBalance());
    }
}