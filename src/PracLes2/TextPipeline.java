package PracLes2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TextPipeline {
    public static void main(String[] args) {
        // Часть A — Композиция функций

        // Определяем функции
        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> removeExtraSpaces = s -> s.replaceAll("\\s+", " ");
        Function<String, String> capitalize = s -> {
            if (s.isEmpty()) return s;
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        };

        // Компонуем функции в конвейер
        Function<String, String> normalize = trim
                .andThen(lower)
                .andThen(removeExtraSpaces)
                .andThen(capitalize);

        // Тестовые строки
        String[] testStrings = {
                "  Hello   WORLD  ",
                "JAVA    is   AWESOME",
                "   multiple    spaces    here   ",
                "single",
                ""
        };

        System.out.println("=== Часть A: Нормализация строк ===");
        for (String str : testStrings) {
            String normalized = normalize.apply(str);
            System.out.println("Исходная: \"" + str + "\"");
            System.out.println("Нормализованная: \"" + normalized + "\"");
            System.out.println("---");
        }

        // Часть B — Локальный класс WordCounter

        class WordCounter {
            private final String text;

            // Конструктор принимает строку
            public WordCounter(String text) {
                this.text = text;
            }

            // Метод возвращает частотный словарь слов
            public Map<String, Integer> count() {
                if (text == null || text.trim().isEmpty()) {
                    return new HashMap<>();
                }

                String[] words = text.split("\\s+");
                Map<String, Integer> wordCount = new HashMap<>();

                for (String word : words) {
                    word = word.toLowerCase(); // Приводим к нижнему регистру для корректного подсчёта
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
                return wordCount;
            }

            // Метод возвращает самое частое слово
            public String mostFrequent() {
                Map<String, Integer> counts = count();
                if (counts.isEmpty()) {
                    return "";
                }

                return counts.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("");
            }
        }

        System.out.println("\n=== Часть B: Анализ текста ===");

        // Нормализуем тестовые строки и анализируем их
        for (String str : testStrings) {
            String normalizedText = normalize.apply(str);

            if (!normalizedText.isEmpty()) {
                WordCounter counter = new WordCounter(normalizedText);
                Map<String, Integer> wordFrequency = counter.count();
                String mostFrequentWord = counter.mostFrequent();

                System.out.println("Текст: \"" + normalizedText + "\"");
                System.out.println("Частотный словарь: " + wordFrequency);
                System.out.println("Самое частое слово: \"" + mostFrequentWord + "\"");
                System.out.println("---");
            }
        }
    }
}