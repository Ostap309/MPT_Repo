package PracLes2;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)      // доступна во время выполнения через Reflection
@Target(ElementType.METHOD)         // применяется к методам
@interface TestInfo {
    String author();

    String date();

    String description() default "";

    int priority() default 5;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotEmpty {
    String message() default "Поле не может быть пустым";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min();

    int max();

    String message() default "Значение вне допустимого диапазона";
}

class RegistrationForm {
    @NotEmpty(message = "Имя обязательно")
    String name;
    @NotEmpty
    String email;
    @Range(min = 18, max = 120, message = "Возраст должен быть от 18 до 120")
    int age;

    public RegistrationForm(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

class Validator {
    static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        for (var field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(NotEmpty.class)) {
                    NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
                    String value = (String) field.get(obj);
                    if (value == null || value.trim().isEmpty()) {
                        errors.add(notEmpty.message());
                    }
                }
                if (field.isAnnotationPresent(Range.class)) {
                    Range range = field.getAnnotation(Range.class);
                    int value = field.getInt(obj);
                    if (value < range.min() || value > range.max()) {
                        errors.add(range.message());
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Отказано в доступе! Поле: " + field.getName());
            }
        }

        return errors;
    }
}

public class ValidationFramework {
    public static void main(String[] args) {
        RegistrationForm valid = new RegistrationForm("Иван", "ivan@mail.ru", 25);
        RegistrationForm invalid = new RegistrationForm("", null, 15);

        System.out.println("=== Валидация корректной формы ===");
        List<String> errors1 = Validator.validate(valid);
        System.out.println(errors1.isEmpty() ? "Все поля валидны!" : errors1);

        System.out.println("\n=== Валидация некорректной формы ===");
        List<String> errors2 = Validator.validate(invalid);
        errors2.forEach(e -> System.out.println("  - " + e));
    }
}
