package PracLes1;

public class DataTypes {
    public static void main(String[] args) {
        // Целочисленные типы
        byte myByte = -127;      // от -128 до 127
        short myShort = 6234;    // от -32768 до 32767
        int myInt = 347699600;        // от -2.1 млрд до 2.1 млрд
        long myLong = 347699600000L;     // большие числа (не забудьте L!)

        // Дробные типы
        float myFloat = 4.292769f;   // не забудьте f!
        double myDouble = 4.29276923760237;

        // Символ и логический тип
        char myChar = '\u4120';
        boolean myBoolean = false;

        // Выведите все переменные
        System.out.println("byte: " + myByte);
        System.out.println("short: " + myShort);
        System.out.println("int: " + myInt);
        System.out.println("long: " + myLong + "\n");

        System.out.println("float: " + myFloat);
        System.out.println("double: " + myDouble + "\n");

        System.out.println("char: " + myChar);
        System.out.println("boolean: " + myBoolean);
    }
}
