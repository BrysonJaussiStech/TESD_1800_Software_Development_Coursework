import java.util.Date;
import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Account[] accounts = new Account[10];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, 100.0);
        }

        while (true) {
            System.out.print("Enter an id: ");
            int id = input.nextInt();

            if (id < 0 || id >= accounts.length) {
                System.out.println("Invalid ID. Please enter a correct ID.\n");
                continue;
            }

            boolean runningMenu = true;
            while (runningMenu) {
                displayMainMenu();
                System.out.print("Enter a choice: ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.printf("The balance is $%,.2f\n\n", accounts[id].getBalance());
                        break;
                    case 2:
                        System.out.print("Enter an amount to withdraw: ");
                        double withdrawAmount = input.nextDouble();
                        accounts[id].withdraw(withdrawAmount);
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("Enter an amount to deposit: ");
                        double depositAmount = input.nextDouble();
                        accounts[id].deposit(depositAmount);
                        System.out.println();
                        break;
                    case 4:
                        runningMenu = false;
                        System.out.println("Exiting account session...\n");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose 1-4.\n");
                }
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("Main menu");
        System.out.println("1: check balance");
        System.out.println("2: withdraw");
        System.out.println("3: deposit");
        System.out.println("4: exit");
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