import java.util.Date;

public class TestAccount {
    public static void main(String[] args) {
        Account myAccount = new Account(1122, 20000);

        Account.setAnnualInterestRate(4.5);

        myAccount.withdraw(2500);

        myAccount.deposit(3000);

        System.out.println("Account Summary:");
        System.out.printf("Account ID:       %d\n", myAccount.getId());
        System.out.printf("Balance:          $%,.2f\n", myAccount.getBalance());
        System.out.printf("Monthly Interest: $%,.2f\n", myAccount.getMonthlyInterest());
        System.out.println("Date Created:     " + myAccount.getDateCreated());
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(double newAnnualInterestRate) {
        annualInterestRate = newAnnualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getMonthlyInterestRate() {
        return (annualInterestRate / 100) / 12;
    }

    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
