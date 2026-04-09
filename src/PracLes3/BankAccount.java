package PracLes3;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final String owner;
    private int failedAttempts;
    private boolean blocked;
    private final String pin;

    // Конструктор для инициализации счёта
    public BankAccount(String accountNumber, String owner, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.pin = pin;
        this.balance = initialBalance;
        this.failedAttempts = 0;
        this.blocked = false;
    }

    public boolean withdraw(String enteredPin, double amount) {
        if (blocked) {
            System.out.println("Операция отклонена: счёт заблокирован.");
            return false;
        }

        if (!validatePin(enteredPin)) {
            failedAttempts++;
            System.out.println("Неверный PIN. Осталось попыток: " + (3 - failedAttempts));

            if (failedAttempts >= 3) {
                blocked = true;
                System.out.println("Счёт заблокирован из‑за трёх неверных попыток ввода PIN.");
            }
            return false;
        }

        // PIN верный — сбрасываем счётчик неудачных попыток
        failedAttempts = 0;

        if (amount <= 0) {
            System.out.println("Сумма для снятия должна быть положительной.");
            return false;
        }

        if (amount > balance) {
            System.out.println("Недостаточно средств на счёте.");
            return false;
        }

        balance -= amount;
        System.out.println("Успешное снятие: " + amount + ". Текущий баланс: " + balance);
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма пополнения должна быть положительной.");
            return false;
        }

        balance += amount;
        System.out.println("Счёт пополнен на: " + amount + ". Текущий баланс: " + balance);
        return true;
    }

    private boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    public String getMaskedBalance() {
        if (balance > 100_000) {
            return "Баланс скрыт (сумма превышает 100 000)";
        } else {
            return "Баланс: " + balance;
        }
    }

    @Override
    public String toString() {
        String status = blocked ? " [ЗАБЛОКИРОВАН]" : "";
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", owner='" + owner + '\'' +
                ", failedAttempts=" + failedAttempts +
                status +
                '}';
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456789", "Иван Иванов", "1234", 50000);
        boolean bool_result;
        System.out.println(account);
        System.out.println(account.getMaskedBalance());

        // Успешное снятие
        bool_result = account.withdraw("1234", 10000);
        System.out.println("withdraw Успех?: " + bool_result + "]");

        // Пополнение
        bool_result = account.deposit(20000);
        System.out.println("[deposit Успех?: " + bool_result + "]");

        // Попытка с неверным PIN
        account.withdraw("0000", 5000);
        account.withdraw("0000", 5000);
        bool_result = account.withdraw("0000", 5000);
        System.out.println("[withdraw Успех?: " + bool_result + "]");

        System.out.println(account);

    }
}
