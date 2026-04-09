package PracLes4;

import java.util.function.Function;

class Result<T> {
    private final boolean isSuccess;
    private final T value;
    private final Exception error;

    private Result(boolean isSuccess, T value, Exception error) {
        this.isSuccess = isSuccess;
        this.value = value;
        this.error = error;
    }

    // Фабричный метод для успешного результата
    public static <T> Result<T> success(T value) {
        return new Result<>(true, value, null);
    }

    // Фабричный метод для неудачного результата
    public static <T> Result<T> failure(Exception error) {
        return new Result<>(false, null, error);
    }

    // Проверка успешности операции
    public boolean isSuccess() {
        return isSuccess;
    }

    // Получение значения (только для успешного результата)
    public T getValue() {
        if (!isSuccess) {
            throw new IllegalStateException("Cannot get value from failed Result");
        }
        return value;
    }

    // Получение ошибки (только для неудачного результата)
    public Exception getError() {
        if (isSuccess) {
            throw new IllegalStateException("Cannot get error from successful Result");
        }
        return error;
    }

    // Возвращает значение при успехе, иначе defaultValue
    public T getOrDefault(T defaultValue) {
        return isSuccess ? value : defaultValue;
    }

    // Трансформирует значение при успехе; при неуспехе возвращает Result.failure с той же ошибкой
    public <R> Result<R> map(Function<T, R> mapper) {
        if (isSuccess) {
            try {
                return success(mapper.apply(value));
            } catch (Exception e) {
                return failure(e);
            }
        } else {
            return failure(error);
        }
    }

    @Override
    public String toString() {
        if (isSuccess) {
            return "Result.success(" + value + ")";
        } else {
            return "Result.failure(" + error.getMessage() + ")";
        }
    }
}

public class GenericsResultDemo {
    public static Result<Integer> divide(int a, int b) {
        try {
            int result = a / b;
            return Result.success(result);
        } catch (ArithmeticException e) {
            return Result.failure(e);
        }
    }

    public static void main(String[] args) {
        // Демонстрация divide(10, 2) — успешный случай
        Result<Integer> result1 = divide(10, 2);
        System.out.println("divide(10, 2): " + result1);
        System.out.println("Успех?: " + result1.isSuccess());
        System.out.println("Value: " + result1.getOrDefault(-1));

        // Цепочка map для успешного результата
        Result<String> mappedResult1 = result1.map(num -> "Result: " + num * 2);
        System.out.println("После map: " + mappedResult1);

        System.out.println();

        // Демонстрация divide(10, 0) — случай ошибки
        Result<Integer> result2 = divide(10, 0);
        System.out.println("divide(10, 0): " + result2);
        System.out.println("Успех?: " + result2.isSuccess());
        System.out.println("Value(по умолчанию): " + result2.getOrDefault(-1));

        // Цепочка map для неудачного результата — ошибка сохраняется
        Result<String> mappedResult2 = result2.map(num -> "Result: " + num * 2);
        System.out.println("После map (с исключением): " + mappedResult2);
    }
}
