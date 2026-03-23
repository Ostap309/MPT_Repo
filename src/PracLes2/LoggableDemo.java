package PracLes2;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

interface Loggable {
    abstract String getComponentName();

    private String formatTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        formatter.withZone(ZoneId.of("Europe/Moscow"));

        return formatter.format(LocalTime.now());
    }

    default void log(String message) {
        System.out.printf("[%s] [%s] %s\n", formatTimestamp(), getComponentName(), message);
    }

    default void logError(String message) {
        System.out.printf("[%s] [%s] ОШИБКА: %s\n", formatTimestamp(), getComponentName(), message);
    }

    static String getLogLevel() {
        return "INFO";
    }

}

class DatabaseService implements Loggable {

    @Override
    public String getComponentName() {
        return "DatabaseService";
    }

    void connect(String url) {
        log("Подключение к " + url);
        log("Подключение установлено");
    }
}

class AuthService implements Loggable {

    @Override
    public String getComponentName() {
        return "AuthService";
    }

    void login(String username, boolean success) {
        if (success) {
            log(String.format("Вход пользователя: %s — успешно", username));
        } else {
            logError(String.format("Вход пользователя: %s — отказано", username));
        }
    }
}

public class LoggableDemo {
    public static void main(String[] args) {
        DatabaseService db = new DatabaseService();
        AuthService auth = new AuthService();

        System.out.println("Уровень логирования: " + Loggable.getLogLevel());
        System.out.println();

        db.connect("jdbc:postgresql://localhost/mydb");
        System.out.println();

        auth.login("admin", true);
        auth.login("hacker", false);
    }
}
