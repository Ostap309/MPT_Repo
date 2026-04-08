package PracLes3;

import java.util.HashSet;
import java.util.Set;

public class StringProcessor {

    private static final Set<Character> VOWELS = new HashSet<>();

    static {
        String vowels = "аеёиоуыэюяАЕЁИОУЫЭЮЯaeiouAEIOU";
        for (char c : vowels.toCharArray()) {
            VOWELS.add(c);
        }
    }

    public static int countVowels(String text) {
        if (text == null) return 0;

        int count = 0;
        for (char c : text.toCharArray()) {
            if (VOWELS.contains(c)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPalindrome(String text) {
        if (text == null) return false;

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetter(text.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetter(text.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(text.charAt(left)) !=
                    Character.toLowerCase(text.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }

    public static String reverse(String text) {
        if (text == null) return null;

        char[] chars = text.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    public static String findLongestWord(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return "";
        }

        String[] words = sentence.split("\\s+");
        String longest = "";

        for (String word : words) {
            word = word.replaceAll("^[\\P{L}\\p{M}]+|[\\P{L}\\p{M}]+$", "");
            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        String[] testCases = {
                "Привет, Java-разработчик!",
                "топот",
                "Madam",
                "hello",
                "А роза упала на лапу Азора",
                "The quick brown fox jumps over the lazy dog"
        };

        for (String text : testCases) {
            System.out.println("Текст: \"" + text + "\"");
            System.out.println("Гласных: " + countVowels(text));
            System.out.println("Палиндром: " + isPalindrome(text));
            System.out.println("Реверс: \"" + reverse(text) + "\"");
            System.out.println("Самое длинное слово: \"" + findLongestWord(text) + "\"");
            System.out.println("---");
        }
    }
}

