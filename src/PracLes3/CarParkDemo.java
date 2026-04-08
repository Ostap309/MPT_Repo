package PracLes3;

import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    private final String brand;
    private final String model;
    private final int year;
    private double fuelLevel; // 0.0–1.0

    public Vehicle(String brand, String model, int year, double fuelLevel) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        setFuelLevel(fuelLevel);
    }

    // Геттеры
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    // Сеттер
    public void setFuelLevel(double fuelLevel) {
        if (fuelLevel < 0.0) this.fuelLevel = 0.0;
        else this.fuelLevel = Math.min(fuelLevel, 1.0);
    }

    public abstract double getFuelConsumption();

    public abstract String getType();

    public double calculateFuelNeeded(double distanceKm) {
        return getFuelConsumption() * distanceKm / 100;
    }

    public boolean canTravel(double distanceKm, double tankCapacityLiters) {
        return calculateFuelNeeded(distanceKm) <= tankCapacityLiters * getFuelLevel();
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d), fuel: %.1f%%",
                brand, model, year, fuelLevel * 100);
    }
}

class Car extends Vehicle {
    private final int doors;
    private final boolean automatic;

    public Car(String brand, String model, int year, double fuelLevel,
               int doors, boolean automatic) {
        super(brand, model, year, fuelLevel);
        this.doors = doors;
        this.automatic = automatic;
    }

    @Override
    public double getFuelConsumption() {
        return automatic ? 9.5 : 8.0;
    }

    @Override
    public String getType() {
        return "Car";
    }

    public void honk() {
        System.out.println("Биииип!");
    }

    public int getDoors() {
        return doors;
    }

    public boolean isAutomatic() {
        return automatic;
    }
}

class Truck extends Vehicle {
    private final double cargoCapacityTons;

    public Truck(String brand, String model, int year, double fuelLevel,
                 double cargoCapacityTons) {
        super(brand, model, year, fuelLevel);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public double getFuelConsumption() {
        return 20 + cargoCapacityTons * 3;
    }

    @Override
    public String getType() {
        return "Truck";
    }

    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }
}

interface Electric {
    double getBatteryLevel();

    double getRangeKm();

    void charge(double hours);
}

class ElectricCar extends Car implements Electric {
    private double batteryLevel; // 0.0–1.0
    private final double maxRangeKm;

    public ElectricCar(String brand, String model, int year, double fuelLevel,
                       int doors, boolean automatic, double batteryLevel, double maxRangeKm) {
        super(brand, model, year, fuelLevel, doors, automatic);
        setBatteryLevel(batteryLevel);
        this.maxRangeKm = maxRangeKm;
    }

    @Override
    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        if (batteryLevel < 0.0) this.batteryLevel = 0.0;
        else this.batteryLevel = Math.min(batteryLevel, 1.0);
    }

    @Override
    public double getRangeKm() {
        return maxRangeKm * batteryLevel;
    }

    @Override
    public void charge(double hours) {
        batteryLevel += hours * 0.2; // +20% в час
        if (batteryLevel > 1.0) batteryLevel = 1.0;
    }

    @Override
    public double getFuelConsumption() {
        return 0;
    }

    @Override
    public String getType() {
        return "ElectricCar";
    }
}

public class CarParkDemo {
    public static void main(String[] args) {
        List<Vehicle> fleet = new ArrayList<>();

        // Создаём автомобили для автопарка
        fleet.add(new Car("Toyota", "Camry", 2020, 0.7, 4, true));
        fleet.add(new Car("Lada", "Vesta", 2019, 0.5, 4, false));
        fleet.add(new Truck("Kamaz", "6520", 2018, 0.8, 15.0));
        fleet.add(new ElectricCar("Tesla", "Model 3", 2021, 0.6, 4, true, 0.75, 500));

        // Выводим информацию для каждого транспортного средства
        for (Vehicle vehicle : fleet) {
            System.out.println(vehicle);
            System.out.println("Тип: " + vehicle.getType());
            System.out.println("Расход топлива на 500 км: " +
                    vehicle.calculateFuelNeeded(500) + " л");

            // Для электромобилей показываем запас хода
            if (vehicle instanceof Electric electricVehicle) {
                System.out.println("Запас хода: " + electricVehicle.getRangeKm() + " км");
            }
            System.out.println("---");
        }

        // Демонстрация полиморфизма
        System.out.println("Демонстрация полиморфизма:");
        for (Vehicle vehicle : fleet) {
            if (vehicle instanceof Car car) {
                car.honk();
            }

            if (vehicle instanceof Electric electricCar) {
                System.out.println("Текущий запас хода: " + electricCar.getRangeKm() + " км");
                electricCar.charge(2); // Заряжаем на 2 часа
                System.out.println("Запас хода после зарядки: " + electricCar.getRangeKm() + " км");
            }
        }
    }
}
