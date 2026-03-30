package PracLes3;

public class MultiplicationTable {
    public static void main(String[] args) {
        // строки
        for (int i = 1; i <= 10; i++) {
            // столбцы
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%4d", i * j);
            }

            System.out.println();
        }
    }
}
