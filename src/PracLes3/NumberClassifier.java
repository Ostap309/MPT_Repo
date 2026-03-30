package PracLes3;

import java.util.Arrays;
import java.util.List;

public class NumberClassifier {
    static String classify(int number) {
        if (number < 0) {
            return "отрицательное";
        } else if (number == 0) {
            return "ноль";
        } else if (number < 10) {
            return "однозначное";
        } else if (number < 100) {
            return "двузначное";
        } else if (number < 1000) {
            return "трёхзначное";
        } else {
            return "большое";
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-5, 0, 7, 42, 100, 1000, -999);
        numbers.forEach(n -> System.out.printf("%d -> %s\n", n, classify(n)));
    }
}
