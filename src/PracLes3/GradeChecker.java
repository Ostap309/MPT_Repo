package PracLes3;

import java.util.Arrays;
import java.util.List;

public class GradeChecker {
    static String getGrade(int score) {
        switch (score / 10) {
            case 9, 10:
                return "Отлично (A)";
            case 8:
                return "Хорошо (B)";
            case 7:
                return "Удовлетворительно (C)";
            case 6:
                return "Слабо (D)";
            default:
                return "Неудовлетворительно (F)";
        }
    }

    static String getGrade2(int score) {
        return switch (score / 10) {
            case 9, 10 -> "Отлично (A)";
            case 8 -> "Хорошо (B)";
            case 7 -> "Удовлетворительно (C)";
            case 6 -> "Слабо (D)";
            default -> "Неудовлетворительно (F)";
        };
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(95, 85, 73, 62, 45, 100, 0);

        System.out.println("Классический switch:");
        numbers.forEach(n -> System.out.printf("%d -> %s\n", n, getGrade(n)));

        System.out.println("\nСтрелочный switch:");
        numbers.forEach(n -> System.out.printf("%d -> %s\n", n, getGrade2(n)));
    }
}
