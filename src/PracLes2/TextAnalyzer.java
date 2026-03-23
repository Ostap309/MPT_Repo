package PracLes2;

public class TextAnalyzer {
    String text;

    @Override
    public String toString() {
        return text;
    }

    TextAnalyzer(String text) {
        this.text = text.trim();
    }

    int wordCount() {
        int space_count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ' && text.charAt(i - 1) != ' ') {
                space_count++;
            }
        }

        return space_count + 1;
    }

    String longestWord() {
        int max_length = -1;
        String longest_word = null;
        for (String word : text.split(" ")) {
            if (word.length() > max_length) {
                max_length = word.length();
                longest_word = word;
            }
        }

        return longest_word;
    }

    String reverseWords() {
        StringBuilder reversed_text = new StringBuilder();
        for (String word : text.split(" ")) {
            reversed_text.insert(0, " " + word);
        }

        return reversed_text.delete(0, 1).toString();
    }

    int countOccurrences(String target) {
        int target_length = target.length();
        int target_count = 0;
        for (int i = 0; i < text.length() - target_length + 1; i++) {
            if (text.substring(i, i + target_length).equalsIgnoreCase(target)) {
                target_count++;
            }
        }

        return target_count;
    }

    boolean isPalindrome() {
        String clean_text = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toLowerCase();
        int clean_text_length = clean_text.length();
        for (int i = 0; i < clean_text_length / 2; i++) {
            if (clean_text.charAt(i) != clean_text.charAt(clean_text_length - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer(" Java Programming is Fun and Java is Powerful    ");

        System.out.println("Текст: " + ta);
        System.out.println("Слов: " + ta.wordCount());
        System.out.println("Самое длинное слово: " + ta.longestWord());
        System.out.println("Слова наоборот: " + ta.reverseWords());
        System.out.println("'Java' встречается: " + ta.countOccurrences("java") + " раз(а)");
        System.out.println("'is' встречается: " + ta.countOccurrences("is") + " раз(а)");
        System.out.println("Палиндром: " + ta.isPalindrome());

        System.out.println();

        TextAnalyzer palindrome = new TextAnalyzer("А роза упала на лапу Азора");
        System.out.println("Текст: " + palindrome);
        System.out.println("Палиндром: " + palindrome.isPalindrome());
    }
}
