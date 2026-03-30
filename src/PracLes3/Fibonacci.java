package PracLes3;

public class Fibonacci {
    public static long fibIterative(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long prev = 0;  // F(0)
        long curr = 1;  // F(1)
        int i = 2;

        // F(n), где n >= 2
        while (i <= n) {
            long next = prev + curr;
            prev = curr;
            curr = next;
            i++;
        }

        return curr;
    }

    public static long fibFor(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long prev = 0;  // F(0)
        long curr = 1;  // F(1)

        // F(n), где n >= 2
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }

        return curr;
    }

    public static void main(String[] args) {

        // Выводим F(0) до F(15) для обоих методов
        for (int i = 0; i <= 15; i++) {
            System.out.printf("n: %3d\tfibIterative: %3d\tfibFor: %3d%n", i, fibIterative(i), fibFor(i));
        }

        System.out.println("\nПоиск первого числа Фибоначчи, превышающего 1000:");

        int n = 0;

        while (fibIterative(n) <= 1000) {
            n++;
        }

        System.out.printf("Первое число Фибоначчи > 1000: F(%d) = %d%n", n, fibIterative(n));
    }
}
