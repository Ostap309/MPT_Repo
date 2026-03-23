package PracLes1;

public class SwapValues {
    public static void main(String[] args) {

        System.out.println("Сложение/вычитание:");

        int a = 10;
        int b = 25;

        System.out.println("До обмена: a = " + a + ", b = " + b);

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("После обмена: a = " + a + ", b = " + b);

        System.out.println("\nXOR:");

        a = 10;
        b = 25;

        System.out.println("До обмена: a = " + a + ", b = " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("После обмена: a = " + a + ", b = " + b);
    }
}
