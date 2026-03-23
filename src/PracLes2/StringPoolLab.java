package PracLes2;

public class StringPoolLab {
    public static void main(String[] args) {
        var s1 = "Hello";
        var s2 = "Hello";
        var s3 = new String("Hello");
        var s4 = new String("Hello");
        var s5 = s3.intern();
        var s6 = "Hel" + "lo";
        String half = "Hel";
        String s7 = half + "lo";

        System.out.printf("s1 == s2: %b\n", s1 == s2);
        System.out.printf("s1.equals(s2): %b\n\n", s1.equals(s2));

        System.out.printf("s1 == s3: %b\n", s1 == s3);
        System.out.printf("s1.equals(s3): %b\n\n", s1.equals(s3));

        System.out.printf("s3 == s4: %b\n", s3 == s4);
        System.out.printf("s3.equals(s4): %b\n\n", s3.equals(s4));

        System.out.printf("s1 == s5: %b\n", s1 == s5);
        System.out.printf("s1.equals(s5): %b\n\n", s1.equals(s5));

        System.out.printf("s1 == s6: %b\n", s1 == s6);
        System.out.printf("s1.equals(s6): %b\n\n", s1.equals(s6));

        System.out.printf("s1 == s7: %b\n", s1 == s7);
        System.out.printf("s1.equals(s7): %b\n\n", s1.equals(s7));

        var s8 = new StringBuilder();
        for (char c : new char[]{'H', 'e', 'l', 'l', 'o'}) {
            s8.append(c);
        }

        System.out.printf("s1 == s8: %b\n", s1 == s8.toString());
        System.out.printf("s1.equals(s8): %b\n\n", s1.equals(s8.toString()));
    }
}
