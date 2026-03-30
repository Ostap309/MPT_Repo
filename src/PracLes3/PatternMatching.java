package PracLes3;

public class PatternMatching {
    public static String describe(Object obj) {
        return switch (obj) {
            case null -> "Объект равен null";
            case Integer i when i > 0 -> "Целое число: " + i + " (положительное)";
            case Integer i -> "Целое число: " + i + " (не положительное)";
            case String s when s.isEmpty() -> "Пустая строка";
            case String s -> "Строка: \"" + s + "\"";
            case Double d -> "Вещественное число: " + d;
            case int[] arr -> "Массив int: " + java.util.Arrays.toString(arr);
            default -> "Иной объект типа " + obj.getClass().getSimpleName();
        };
    }

    public static void main(String[] args) {
        Object[] testCases = {null, 42, -5, "", "Привет", 3.14, new int[]{1, 2, 3}, true};

        for (Object testCase : testCases) {
            System.out.println(describe(testCase));
        }
    }
}
