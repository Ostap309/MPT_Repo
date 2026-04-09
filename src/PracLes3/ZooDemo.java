package PracLes3;

import java.util.*;
import java.util.stream.Collectors;

abstract class Animal {
    protected String name;
    protected int age;
    protected double weight;
    protected int energyLevel;

    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.energyLevel = 50; // начальный уровень энергии
    }

    public void eat(int calories) {
        energyLevel = Math.min(100, energyLevel + calories / 10);
        System.out.println(name + " поел, уровень энергии: " + energyLevel);
    }

    public void sleep(int hours) {
        energyLevel = Math.min(100, energyLevel + hours * 5);
        System.out.println(name + " поспал " + hours + " часов, уровень энергии: " + energyLevel);
    }

    public abstract void makeSound();

    // Геттеры
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }
}

abstract class Predator extends Animal {
    public Predator(String name, int age, double weight) {
        super(name, age, weight);
    }

    public void hunt() {
        energyLevel = Math.min(100, energyLevel + 30);
        weight = Math.max(0, weight - 0.5);
        System.out.println(name + " поохотился, уровень энергии: " + energyLevel + ", вес: " + weight);
    }
}

abstract class Herbivore extends Animal {
    public Herbivore(String name, int age, double weight) {
        super(name, age, weight);
    }

    public void graze() {
        energyLevel = Math.min(100, energyLevel + 15);
        System.out.println(name + " пасся, уровень энергии: " + energyLevel);
    }
}

class Lion extends Predator implements Trainable {
    private java.util.List<String> commands = new java.util.ArrayList<>();

    public Lion(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void makeSound() {
        System.out.println(name + ": Р-р-р!");
    }

    public void roar() {
        System.out.println(name + " громко рычит: РРРРРР!");
    }

    @Override
    public void train(String command) {
        commands.add(command);
        System.out.println(name + " выучил команду: " + command);
    }

    @Override
    public void listCommands() {
        if (commands.isEmpty()) {
            System.out.println(name + " не знает команд");
        } else {
            System.out.println(name + " знает команды: " + commands);
        }
    }
}

class Elephant extends Herbivore {
    public Elephant(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void makeSound() {
        System.out.println(name + ": Тууут!");
    }

    public void trumpet() {
        System.out.println(name + " трубит: ТУУУУТ!");
    }
}

interface Trainable {
    void train(String command);

    void listCommands();
}

class Zoo {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal a) {
        animals.add(a);
        System.out.println("В зоопарк добавлен: " + a.getName());
    }

    public void feedAll() {
        for (Animal animal : animals) {
            animal.eat(20);
        }
    }

    public void makeNoise() {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    public List<Animal> getHungryAnimals() {
        return animals.stream()
                .filter(animal -> animal.getEnergyLevel() < 30)
                .collect(Collectors.toList());
    }

    public Optional<Animal> findAnimal(String name) {
        return animals.stream()
                .filter(animal -> animal.getName().equals(name))
                .findFirst();
    }
}


public class ZooDemo {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        // Создаём животных
        Lion simba = new Lion("Симба", 5, 190.0);
        Elephant dumbo = new Elephant("Дамбо", 10, 4500.0);

        // Добавляем в зоопарк
        zoo.addAnimal(simba);
        zoo.addAnimal(dumbo);

        System.out.println("\n--- Демонстрация действий животных ---");
        simba.makeSound();
        simba.roar();
        dumbo.makeSound();
        dumbo.trumpet();

        System.out.println("\n--- Обучение льва ---");
        simba.train("сидеть");
        simba.train("лежать");
        simba.listCommands();

        System.out.println("\n--- Действия животных ---");
        simba.hunt();
        dumbo.graze();
        simba.sleep(3);

        System.out.println("\n--- Кормление всех животных ---");
        zoo.feedAll();

        System.out.println("\n--- Все животные издают звуки ---");
        zoo.makeNoise();

        System.out.println("\n--- Голодные животные (энергия < 30) ---");
        List<Animal> hungry = zoo.getHungryAnimals();
        if (hungry.isEmpty()) {
            System.out.println("Голодных животных нет");
        } else {
            hungry.forEach(animal ->
                    System.out.println(animal.getName() + " (энергия: " + animal.getEnergyLevel() + ")"));
        }

        System.out.println("\n--- Поиск животных ---");
        Optional<Animal> found = zoo.findAnimal("Симба");
        if (found.isPresent()) {
            System.out.println("Найден: " + found.get().getName());
        } else {
            System.out.println("Животное не найдено");
        }

        Optional<Animal> notFound = zoo.findAnimal("Горилла");
        if (!notFound.isPresent()) {
            System.out.println("Животное 'Горилла' не найдено");
        }
    }
}
