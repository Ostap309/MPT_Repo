package PracLes4;

import java.util.*;
import java.util.List;

class NumberUtils {

    public static double sum(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static double average(List<? extends Number> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть пустым или null");
        }
        return sum(numbers) / numbers.size();
    }

    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть null или пустым");
        }

        T max = list.getFirst();
        for (T element : list) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<T>> T findMin(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть null или пустым");
        }

        T min = list.getFirst();
        for (T element : list) {
            if (element.compareTo(min) < 0) {
                min = element;
            }
        }
        return min;
    }

    public static void fillWithIntegers(List<? super Integer> list, int n) {
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
    }
}


public class NumberUtilsTest {
    public static void main(String[] args) {
        // Тест 1: сумма [1, 2, 3, 4, 5]
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Сумма [1,2,3,4,5]: " + NumberUtils.sum(intList));

        // Тест 2: сумма [1.5, 2.5, 3.5]
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);
        System.out.println("Сумма [1.5,2.5,3.5]: " + NumberUtils.sum(doubleList));

        // Тест 3: среднее значение
        System.out.println("Среднее [1,2,3,4,5]: " + NumberUtils.average(intList));
        try {
            System.out.println("Среднее пустой список: " + NumberUtils.average(new ArrayList<>()));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при вычислении среднего для пустого списка: " + e.getMessage());
        }

        // Тест 4: максимум строк ["яблоко", "апельсин", "банан"]
        List<String> stringList = Arrays.asList("яблоко", "апельсин", "банан");
        System.out.println("Максимум строк: " + NumberUtils.findMax(stringList));
        System.out.println("Минимум строк: " + NumberUtils.findMin(stringList));

        // Тест 5: fillWithIntegers в List<Number>
        List<Number> numberList = new ArrayList<>();
        NumberUtils.fillWithIntegers(numberList, 5);
        System.out.println("List<Number> после fillWithIntegers(5): " + numberList);
    }
}

