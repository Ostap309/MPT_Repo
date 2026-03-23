package PracLes2.company.core;

public class EmployeeFixed {
    private String name;
    private int age;
    private double salary;              // какой модификатор?
    private String password;

    public EmployeeFixed(String name, int age, double salary, String password) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.password = password;
    }

    public String getRole() {
        return "Employee";
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void promote(double raise) {
        this.salary += raise;
    }

    public void printSummary() {
        System.out.println(name + ", " + age + " лет, " + salary + " руб.");
    }

    private boolean validatePassword(String input) {
        return password.equals(input);
    }

    public String authenticate(String input) {
        return "Аутентификация: " + this.validatePassword(input);
    }
}
