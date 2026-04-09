package PracLes4;

// 1. Базовое checked исключение для банковских операций
class BankException extends Exception {
    private String operationId;

    public BankException(String message, String operationId) {
        super(message);
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }
}

// 2. Исключение недостаточных средств
class InsufficientFundsException extends BankException {
    private double required;
    private double available;

    public InsufficientFundsException(double required, double available, String operationId) {
        super(String.format("Недостаточно средств. Требуется: %.2f, доступно: %.2f",
                required, available), operationId);
        this.required = required;
        this.available = available;
    }

    public double getRequired() {
        return required;
    }

    public double getAvailable() {
        return available;
    }
}

// 3. Unchecked исключение — неверный аргумент
class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(double amount) {
        super("Сумма должна быть положительной, получено: " + amount);
    }
}

// 4. Класс счёта
class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double initialBalance) {
        if (initialBalance < 0) {
            throw new InvalidAmountException(initialBalance);
        }
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        balance += amount;
        System.out.printf("Пополнение на %.2f. Баланс: %.2f%n", amount, balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance, "WD-" + accountId);
        }
        balance -= amount;
        System.out.printf("Снятие %.2f. Баланс: %.2f%n", amount, balance);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }
}

// 5. Тест
public class BankTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC-001", 1000.0);

        // Тест нормального пополнения и снятия
        account.deposit(500.0);
        try {
            account.withdraw(200.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест недостаточных средств
        try {
            account.withdraw(2000.0); // Должно бросить InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.printf("Не хватает: %.2f%n", e.getRequired() - e.getAvailable());
        }

        // Тест невалидной суммы (unchecked)
        try {
            account.deposit(-100); // Должно бросить InvalidAmountException
        } catch (InvalidAmountException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест невалидного начального баланса
        try {
            BankAccount badAccount = new BankAccount("BAD-001", -500);
        } catch (InvalidAmountException e) {
            System.out.println("Нельзя создать счёт: " + e.getMessage());
        }
    }
}
