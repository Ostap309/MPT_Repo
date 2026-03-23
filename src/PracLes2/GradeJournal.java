package PracLes2;

public class GradeJournal {
    static double average(int[] grades) {
        double sum = 0;
        int count = 0;
        for (int grade : grades) {
            sum += grade;
            count++;
        }

        return sum / count;
    }

    static int max(int[] grades) {
        int greatest = -1;
        for (int grade : grades) {
            if (grade > greatest) {
                greatest = grade;
            }
        }

        return greatest;
    }

    static int min(int[] grades) {
        int smallest = 6;
        for (int grade : grades) {
            if (grade < smallest) {
                smallest = grade;
            }
        }

        return smallest;
    }


    public static void main(String[] args) {
        var names = new String[]{"Алиса", "Борис", "Вера", "Глеб"};
        var names_count = names.length;
        var all_grades = new int[names_count][];
        all_grades[0] = new int[]{5, 4, 5, 5, 3}; // Алиса
        all_grades[1] = new int[]{3, 3, 4}; // Борис
        all_grades[2] = new int[]{5, 5, 5, 5, 5, 4}; // Вера
        all_grades[3] = new int[]{4, 3, 4, 5}; // Глеб

        System.out.println("=== Журнал оценок ===");
        String best_student_name = null;
        double best_student_avg = -1;
        for (int i = 0; i < names_count; i++) {
            System.out.printf("%-5s   | Оценок: %d | Средний: %.2f | Мин: %d | Макс: %d\n",
                    names[i], all_grades[i].length,
                    average(all_grades[i]),
                    min(all_grades[i]),
                    max(all_grades[i]));

            if (average(all_grades[i]) > best_student_avg) {
                best_student_name = names[i];
                best_student_avg = average(all_grades[i]);
            }
        }

        System.out.printf("\nЛучший студент: %s (средний балл: %.2f)", best_student_name, best_student_avg);

    }
}
