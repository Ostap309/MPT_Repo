package PracLes4;

import java.util.*;

public class WildcardDemo {

    public static void printAll(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void addDefaults(List<? super String> list) {
        list.add("default1");
        list.add("default2");
    }

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("a", "b", "c");
        printAll(stringList);

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        printAll(integerList);

        double sum = sumNumbers(integerList);
        System.out.println("Сумма: " + sum);

        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        sum = sumNumbers(doubleList);
        System.out.println("Сумма doubles: " + sum);

        List<String> strList = new ArrayList<>();
        addDefaults(strList);
        System.out.println("String list после addDefaults: " + strList);

        List<Object> objectList = new ArrayList<>();
        addDefaults(objectList);
        System.out.println("Object list после addDefaults: " + objectList);

        List<Integer> integers = Arrays.asList(1, 2, 3);

        // List<Number> numbers = integers; // Ошибка компиляции! Так как обобщения не поддерживают полимофизм

        // Решение с wildcard:
        System.out.println("\nРешение с wildcard:");
        List<? extends Number> wildcardNumbers = integers;
        // Можно присвоить: List<? extends Number> = List<Integer>, но нельзя добавлять элементы в wildcardNumbers (кроме null)

        // Корректная копия из List<Integer> в List<Number>
        System.out.println("\nКорректная копия из List<Integer> в List<Number>:");
        List<Number> numbersCopy = new ArrayList<>(integers);
        System.out.println("Скопировано: " + numbersCopy);

        // Альтернативный способ через цикл
        List<Number> numbersCopy2 = new ArrayList<>();
        for (Integer i : integers) {
            numbersCopy2.add(i);
        }
        System.out.println("Скопировано через цикл: " + numbersCopy2);
    }
}
