package PracLes2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RefactorStep2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине
        cities.sort(Comparator.comparingInt(String::length));

        // 2. Вывод каждого элемента
        cities.forEach(System.out::println);

        // 3. Преобразование в верхний регистр
        Function<String, String> toUpper = String::toUpperCase;

        // 4. Проверка длины > 5
        Predicate<String> isLong = s -> s.length() > 5;
        // Лямбда с конкретным условием, поэтому нельзя заменить стандартным методом

        // 5. Формирование строки с восклицательным знаком
        Function<String, String> exclaim = s -> s + "!";
        // Не существует стандартного метода конкатенации "!" к строке - нужно писать лямбду

        // 6. Создание нового списка
        Supplier<List<String>> listFactory = ArrayList::new;

        // Использование
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);
    }
}
