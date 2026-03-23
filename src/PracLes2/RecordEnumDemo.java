package PracLes2;


record Temperature(double value, Unit unit) {
    enum Unit {CELSIUS, FAHRENHEIT, KELVIN}

    public Temperature {
        if (!(unit == Unit.CELSIUS && (value + 273.15 > 0)
                || unit == Unit.FAHRENHEIT && ((value - 32) * 5 / 9 + 273.15 > 0)
                || unit == Unit.KELVIN && (value > 0))) {
            throw new IllegalArgumentException("Температура не может быть ниже абсолютного нуля!");
        }
    }

    Temperature convertTo(Unit targetUnit) {
        double k;
        if (unit == Unit.CELSIUS) {
            k = value + 273.15;
        } else if (unit == Unit.FAHRENHEIT) {
            k = (value - 32) * 5 / 9 + 273.15;
        } else {
            k = value;
        }

        if (targetUnit == Unit.CELSIUS) {
            return new Temperature(k - 273.15, targetUnit);
        } else if (targetUnit == Unit.FAHRENHEIT) {
            return new Temperature((k - 273.15) * 9 / 5 + 32, targetUnit);
        } else {
            return new Temperature(k, targetUnit);
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}

enum MathOperation {
    ADD {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE {
        @Override
        public double apply(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Деление на ноль недопустимо");
            }
            return a / b;
        }
    };

    public abstract double apply(double a, double b);
}

public class RecordEnumDemo {
    public static void main(String[] args) {
        Temperature body = new Temperature(36.6, Temperature.Unit.CELSIUS);
        System.out.println(body);
        System.out.println(body.convertTo(Temperature.Unit.FAHRENHEIT));
        System.out.println(body.convertTo(Temperature.Unit.KELVIN));

        System.out.println();

        double a = 10, b = 3;
        for (MathOperation op : MathOperation.values()) {
            System.out.printf("%s(%g, %g) = %g%n", op.name(), a, b, op.apply(a, b));
        }
    }
}
