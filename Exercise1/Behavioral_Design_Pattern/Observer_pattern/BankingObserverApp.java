import java.util.*;

interface Observer {
    void update(String message);
}

interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String message);
}

class BankAccount implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String accountHolder;
    private float balance;

    public BankAccount(String accountHolder, float initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public void deposit(float amount) {
        balance += amount;
        String msg = "Deposit of " + amount + " successful. Balance: " + balance;
        notifyObservers(accountHolder + ": " + msg);
    }

    public void withdraw(float amount) {
        if (amount <= balance) {
            balance -= amount;
            String msg = "Withdrawal of " + amount + " successful. Balance: " + balance;
            notifyObservers(accountHolder + ": " + msg);
        } else {
            notifyObservers(accountHolder + ": Withdrawal of " + amount + " failed. Insufficient balance.");
        }
    }
}

class SMSAlert implements Observer {
    public void update(String message) {
        System.out.println("[SMS Alert] " + message);
    }
}

class EmailAlert implements Observer {
    public void update(String message) {
        System.out.println("[Email Alert] " + message);
    }
}

class MobileAppAlert implements Observer {
    public void update(String message) {
        System.out.println("[Mobile App Notification] " + message);
    }
}

public class BankingObserverApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        float initialBalance = sc.nextFloat();

        BankAccount account = new BankAccount(name, initialBalance);

        // Register observers
        account.registerObserver(new SMSAlert());
        account.registerObserver(new EmailAlert());
        account.registerObserver(new MobileAppAlert());

        System.out.println("\n=== Banking/ATM Alert System ===");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    float deposit = sc.nextFloat();
                    account.deposit(deposit);
                    break;

                case 2:
                    System.out.print("Enter withdraw amount: ");
                    float withdraw = sc.nextFloat();
                    account.withdraw(withdraw);
                    break;

                case 3:
                    System.out.println("Exiting... Thank you!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}
