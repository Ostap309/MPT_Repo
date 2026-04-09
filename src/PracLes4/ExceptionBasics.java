package PracLes4;

public class ExceptionBasics {
    public static void main(String[] args) {
        // Обработка ArithmeticException
        int[] divisors = {2, 0, 5, 0, 1};
        System.out.println("=== ДЕЛЕНИЕ ===");
        for (int divisor : divisors) {
            try {
                int result = 100 / divisor;
                System.out.println("100 / " + divisor + " = " + result);
            } catch (ArithmeticException e) {
                System.out.println("Ошибка при делении на " + divisor + ": " + e.getMessage());
            }
        }

        // Обработка ArrayIndexOutOfBoundsException
        int[] array = {10, 20, 30};
        int[] indices = {0, 1, 5, 2, -1};
        System.out.println("\n=== ДОСТУП К МАССИВУ ===");
        for (int index : indices) {
            try {
                int value = array[index];
                System.out.println("array[" + index + "] = " + value);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка доступа к array[" + index + "]: " + e.getMessage());
            }
        }

        // Обработка NumberFormatException
        String[] strings = {"42", "abc", "100", "3.14", "-7"};
        System.out.println("\n=== ПАРСИНГ ЧИСЕЛ ===");
        for (String str : strings) {
            try {
                int number = Integer.parseInt(str);
                System.out.println("parseInt(\"" + str + "\") = " + number);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга \"" + str + "\": " + e.getMessage());
            }
        }
    }
}
