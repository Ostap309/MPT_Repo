package PracLes2;

import java.util.Locale;

abstract class Employee {
    protected String name;
    protected double baseSalary;

    Employee(String name, double salary) {
        this.name = name;
        this.baseSalary = salary;
    }

    String getName() {
        return name;
    }

    double getBaseSalary() {
        return baseSalary;
    }

    abstract double calculateBonus();

    double totalCompensation() {
        return baseSalary + calculateBonus();
    }

    @Override
    public String toString() {
        return String.format("%s | Оклад: %.1f | Бонус: %.1f | Итого: %.1f", name, baseSalary, calculateBonus(),
                totalCompensation());
    }
}

class Manager extends Employee {
    int teamSize;

    Manager(String name, double salary, int teamSize) {
        super(name, salary);
        this.teamSize = teamSize;
    }

    @Override
    double calculateBonus() {
        return baseSalary * 0.15 + teamSize * 5000;
    }
}

class Developer extends Employee {
    String language;

    Developer(String name, double salary, String language) {
        super(name, salary);
        this.language = language;
    }

    @Override
    double calculateBonus() {
        return baseSalary * 0.12;
    }
}

class Intern extends Employee {

    Intern(String name, double salary) {
        super(name, salary);
    }

    @Override
    double calculateBonus() {
        return 10000;
    }
}

public class EmployeeBonus {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Employee[] team = {
                new Manager("Ольга", 120000, 5),
                new Developer("Андрей", 95000, "Java"),
                new Developer("Мария", 100000, "Python"),
                new Intern("Стажёр Петя", 30000)
        };

        System.out.println("=== Расчёт бонусов ===");
        double totalBudget = 0;
        for (Employee e : team) {
            System.out.println(e);
            totalBudget += e.totalCompensation();
        }
        System.out.printf("\nОбщий бюджет: %.0f руб.%n", totalBudget);
    }
}

