package PracLes2.company.app;

import PracLes2.company.core.Employee;
import PracLes2.company.core.EmployeeFixed;

public class HRSystem {
    public static void main(String[] args) {
        var emp = new EmployeeFixed("Иван", 30, 80000, "secret");

        System.out.println(emp.getName());            // Строка A
        System.out.println(emp.getAge());             // Строка B
        System.out.println(emp.getSalary());          // Строка C
        System.out.println(emp.getRole());       // Строка E
        emp.promote(5000);                  // Строка F
        emp.printSummary();                      // Строка G
        System.out.println(emp.authenticate("secret"));          // Строка H
        System.out.println(emp.authenticate("123456"));
    }
}
