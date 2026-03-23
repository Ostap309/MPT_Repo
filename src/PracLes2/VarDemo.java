package PracLes2;

import PracLes2.company.core.EmployeeFixed;

import java.util.ArrayList;
import java.util.Arrays;

public class VarDemo {
    public static void main(String[] args) {
        var digit = new Integer(10);
        var string = new String("Hello Java");
        var list = new ArrayList<Double>(Arrays.asList(1.2, 3.14, 7.3670));
        var arr = new String[]{"Alpha", "Beta", "Gamma"};
        var emp = new EmployeeFixed("Ostap", 20, 120000, "password");

        System.out.println(digit + " -> " + digit.getClass().getSimpleName());
        System.out.println(string + " -> " + string.getClass().getSimpleName());
        System.out.println(list + " -> " + list.getClass().getSimpleName());
        System.out.println(Arrays.toString(arr) + " -> " + arr.getClass().getSimpleName());
        System.out.println(emp.getName() + " -> " + emp.getClass().getSimpleName());

//      Неправильное использование:
//      var a; - переменная не инициализируется
//
//      class Person {
//          var age = 5; - не используется с полями классов
//
//          void walk(var speed) { - не может использоваться в качестве параметра метода
//              ...
//          }
//      }
//
//      var none = null;

    }
}
