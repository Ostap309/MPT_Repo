package PracLes4;

import java.util.Arrays;

class EmptyStackException extends RuntimeException {
    public EmptyStackException(String message) {
        super(message);
    }
}

class Stack<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public Stack() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void push(T element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        T element = (T) elements[--size];
        elements[size] = null; // Убираем ссылку для сборки мусора
        return element;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Stack[]";

        StringBuilder sb = new StringBuilder("Stack[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}


public class StackDemo {
    public static void main(String[] args) {
        // Демонстрация 1: Stack<String>
        System.out.println("=== Stack<String> Demo ===");
        Stack<String> stringStack = new Stack<>();

        stringStack.push("First");
        stringStack.push("Second");
        stringStack.push("Third");

        System.out.println("После добавления трёх строк: " + stringStack);
        System.out.println("peek(): " + stringStack.peek());
        System.out.println("pop(): " + stringStack.pop());
        System.out.println("После pop(): " + stringStack);

        // Демонстрация 2: Stack<Integer>
        System.out.println("\n=== Stack<Integer> Demo ===");
        Stack<Integer> integerStack = new Stack<>();

        for (int i = 10; i <= 50; i += 10) {
            integerStack.push(i);
        }

        System.out.println("Заполненный стек: " + integerStack);
        System.out.println("Извлечение всех элементов:");

        while (!integerStack.isEmpty()) {
            System.out.println("pop(): " + integerStack.pop());
        }

        System.out.println("Стек пуст: " + integerStack.isEmpty());
    }
}

