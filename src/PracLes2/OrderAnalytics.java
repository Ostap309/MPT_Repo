package PracLes2;

import java.util.*;
import java.util.stream.Collectors;

record Order(String customer, String product, double price, int quantity, String category) {

    double total() {
        return price * quantity;
    }
}

public class OrderAnalytics {
    static void main() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("Борис", "Ботинки", 820, 2, "Одежда"));
        orders.add(new Order("Евгений", "Еловая доска", 1000, 10, "Стройматериалы"));
        orders.add(new Order("Евгений", "Шуруповерт", 6500, 1, "Инструменты"));
        orders.add(new Order("Зина", "Зонт", 387, 1, "-"));
        orders.add(new Order("Илья", "Ионизатор воздуха", 13900, 1, "Электроника"));
        orders.add(new Order("Виктор", "Витамины", 299, 5, "Лекарства"));
        orders.add(new Order("Глеб", "Галстук", 310, 1, "Одежда"));
        orders.add(new Order("Дарья", "Духи", 1200, 1, "Парфюмерия"));
        orders.add(new Order("Илья", "Индейка", 350, 2, "Продукты"));
        orders.add(new Order("Жанна", "Желе", 550, 2, "Продукты"));
        orders.add(new Order("Артем", "Арбуз", 400, 1, "Продукты"));

        System.out.println("=== Заказы дороже 5000 руб. ===");
        orders.stream()
                .filter(order -> order.total() > 5000)
                .forEach(System.out::println);

        System.out.println("\n=== Уникальные имена клиентов (по алфавиту) ===");
        List<String> uniqueCustomers = orders.stream()
                .map(Order::customer)
                .distinct()
                .sorted()
                .toList();
        uniqueCustomers.forEach(System.out::println);

        System.out.println("\n=== Общая выручка ===");
        double totalRevenue = orders.stream()
                .mapToDouble(Order::total)
                .sum();
        System.out.println(totalRevenue);

        System.out.println("\n=== Самый дорогой заказ ===");
        Optional<Order> mostExpensiveOrder = orders.stream()
                .max(Comparator.comparingDouble(Order::total));
        mostExpensiveOrder.ifPresent(System.out::println);

        System.out.println("\n=== Количество заказов в каждой категории ===");
        Map<String, Long> ordersByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.counting()
                ));
        ordersByCategory.forEach((category, count) ->
                System.out.println(category + ": " + count)
        );

        System.out.println("\n=== Средняя стоимость заказа по каждому клиенту ===");
        Map<String, Double> averageOrderByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.averagingDouble(Order::total)
                ));
        averageOrderByCustomer.forEach((customer, average) ->
                System.out.printf("%s: %.2f\n", customer, average)
        );

        System.out.println("\n=== Разделение заказов на дорогие (>3000) и дешёвые ===");
        Map<Boolean, List<Order>> expensiveCheapOrders = orders.stream()
                .collect(Collectors.partitioningBy(
                        order -> order.total() > 3000
                ));
        System.out.println("Дорогие (total > 3000):");
        expensiveCheapOrders.get(true).forEach(System.out::println);
        System.out.println("Дешёвые (total <= 3000):");
        expensiveCheapOrders.get(false).forEach(System.out::println);

        System.out.println("\n=== Названия товаров дороже 1000 руб., без дубликатов, в верхнем регистре ===");
        List<String> expensiveProducts = orders.stream()
                .filter(order -> order.price() > 1000)
                .map(Order::product)
                .map(String::toUpperCase)
                .distinct().toList();
        expensiveProducts.forEach(System.out::println);
    }
}
