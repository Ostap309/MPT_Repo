package PracLes2;

public class BankAccount {

    private String owner;
    private double balance;
    private String accountNumber;
    private static int totalAccounts;
    private static String bankName;

    static {
        bankName = "Java Bank";
        System.out.println("Банковская система инициализирована");
    }

    {
        totalAccounts++;
        System.out.println("Создание счёта #" + totalAccounts);
    }

    BankAccount(String owner, double initialBalance) {
        accountNumber = "ACC-" + totalAccounts;
        this.owner = owner;
        this.balance = initialBalance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: сумма должна быть положительной");
        } else {
            this.balance += amount;
        }
    }

    void withdraw(double amount) {
        if (this.balance < amount) {
            System.out.println("Ошибка: недостаточно средств");
        } else {
            this.balance -= amount;
        }
    }

    static int getTotalAccounts() {
        return totalAccounts;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f руб.", this.accountNumber, this.owner, this.balance);
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("Алиса", 1000);
        BankAccount a2 = new BankAccount("Борис", 500);

        System.out.println(a1);
        System.out.println(a2);

        a1.deposit(500);
        System.out.println("После пополнения: " + a1);

        a1.withdraw(200);
        System.out.println("После снятия: " + a1);

        a1.withdraw(5000);

        a2.deposit(-100);

        System.out.println("Всего счетов: " + BankAccount.getTotalAccounts());
    }
}
