package PracLes2;

import java.util.*;

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
        orders.add(new Order("Зина", "Зонт", 387, 1, "-"));
        orders.add(new Order("Илья", "Ионизатор воздуха", 13900, 1, "Электроника"));
        orders.add(new Order("Виктор", "Витамины", 299, 5, "Лекарства"));
        orders.add(new Order("Глеб", "Галстук", 310, 1, "Одежда"));
        orders.add(new Order("Дарья", "Духи", 1200, 1, "Парфюмерия"));
        orders.add(new Order("Илья", "Индейка", 350, 2, "Продукты"));
        orders.add(new Order("Жанна", "Желе", 550, 2, "Продукты"));
        orders.add(new Order("Артем", "Арбуз", 400, 1, "Продукты"));


    }
}
