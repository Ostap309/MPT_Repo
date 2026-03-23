package PracLes2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GradeSystem {

    enum Grade {
        A("Отлично", 4.0),
        B("Хорошо", 3.0),
        C("Удовлетворительно", 2.0),
        D("Неудовлетворительно", 1.0),
        F("Провал", 0.0);

        private final String description;
        private final double gpaValue;

        Grade(String description, double gpaValue) {
            this.description = description;
            this.gpaValue = gpaValue;
        }

        public String getDescription() {
            return description;
        }

        public double getGpaValue() {
            return gpaValue;
        }

        boolean isPassing() {
            return this != F && this != D;
        }

        static Grade fromScore(int score) {
            if (score >= 90) return A;
            if (score >= 80) return B;
            if (score >= 70) return C;
            if (score >= 60) return D;
            return F;
        }
    }

    record Student(String name, int id) {
        Student {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (id <= 0) {
                throw new IllegalArgumentException("ID must be greater than 0");
            }
        }
    }


    public static void main(String[] args) {
        var students = new Student[] {
                new Student("Анна", 1),
                new Student("Борис", 2),
                new Student("Виктор", 3),
                new Student("Глеб", 4),
                new Student("Дмитрий", 5),
                new Student("Елена", 6),
                new Student("Жанна", 7)
        };

        Map<Student, Grade> achievements = new HashMap<>();
        for (Student s : students) {
            achievements.put(s, Grade.fromScore((int) (Math.sqrt(Math.random() * 37) * 100 / 6)));
        }

        achievements.forEach((s, g) -> System.out.println(s + ": " + g));
        System.out.println();

        double sum = 0;
        int n = 0;

        EnumMap<Grade, List<Student>> grade_groups = new EnumMap<>(Grade.class);
        for (Student key_student : achievements.keySet()) {
            Grade grade = achievements.get(key_student);
            sum += grade.getGpaValue();
            n++;
            if (!grade_groups.containsKey(grade)) {
                grade_groups.put(grade, new ArrayList<Student>());
            }

            grade_groups.get(grade).add(key_student);
        }

        grade_groups.forEach((g, s_list) -> System.out.println(g + ": " + s_list.toString() + " | Количество: " + s_list.size()));
        System.out.println();

        EnumSet<Grade> passing_set = EnumSet.noneOf(Grade.class);
        for (Grade grade : Grade.values()) {
            if (grade.isPassing()) {
                passing_set.add(grade);
            }
        }

        System.out.println("Проходные оценки: " + passing_set);
        System.out.println();

        System.out.printf("Средний GPA: %.3f", sum / n);
        System.out.println();

    }
}
