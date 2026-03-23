package PracLes2;

sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {
    String process(double amount);

    double fee(double amount);
}

record CreditCard(String cardNumber, String holder) implements PaymentMethod {

    @Override
    public String process(double amount) {
        return String.format("Оплата картой *%s: %.2f руб.", cardNumber.substring(cardNumber.length() - 4),
                amount);
    }

    @Override
    public double fee(double amount) {
        return amount * 0.02;
    }
}

record BankTransfer(String bankName, String iban) implements PaymentMethod {

    @Override
    public String process(double amount) {
        return String.format("Перевод через БАНК: %.2f руб.", amount);
    }

    @Override
    public double fee(double amount) {
        return 50;
    }
}

record CryptoWallet(String address, String currency) implements PaymentMethod {

    @Override
    public String process(double amount) {
        return String.format("Криптоплатёж (ВАЛЮТА): %.2f руб.", amount);
    }

    @Override
    public double fee(double amount) {
        return amount * 0.015;
    }
}

class PaymentProcessor {
    static void describe(PaymentMethod pm) {
        switch (pm) {
            case CreditCard cc ->
                    System.out.printf("Номер карты: %s\nДержатель карты: %s\n", cc.cardNumber(), cc.holder());
            case BankTransfer bt -> System.out.printf("Наименование банка: %s\nIBAN: %s\n", bt.bankName(), bt.iban());
            case CryptoWallet cw -> System.out.printf("Адрес: %s\nВалюта: %s\n", cw.address(), cw.currency());
            default -> System.out.println("Неизвестная операция");
        }
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentMethod[] payments = {
                new CreditCard("4111222233334444", "Иван Петров"),
                new BankTransfer("Сбербанк", "RU1234567890"),
                new CryptoWallet("0xABC123", "BTC"),
        };

        double amount = 10000;
        for (PaymentMethod pm : payments) {
            System.out.println(pm.process(amount));
            System.out.printf("  Комиссия: %.2f руб.%n", pm.fee(amount));
            PaymentProcessor.describe(pm);
            System.out.println();
        }
    }
}
