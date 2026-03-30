//package PracLes2;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//// Enum для жанров книг
//enum Genre {
//    FICTION("Художественная литература"),
//    SCIENCE("Наука"),
//    HISTORY("История"),
//    PROGRAMMING("Программирование"),
//    ART("Искусство");
//
//    private final String russianName;
//
//    Genre(String russianName) {
//        this.russianName = russianName;
//    }
//
//    public String getRussianName() {
//        return russianName;
//    }
//
//    public static Genre fromString(String name) {
//        for (Genre genre : Genre.values()) {
//            if (genre.getRussianName().equalsIgnoreCase(name)) {
//                return genre;
//            }
//        }
//        throw new IllegalArgumentException("Жанр не найден: " + name);
//    }
//}
//
//// Record для книги
//record Book(String title, String author, int year, Genre genre, double price) {
//    public Book {
//        if (title == null || title.trim().isEmpty()) {
//            throw new IllegalArgumentException("Название книги не может быть пустым");
//        }
//        if (author == null || author.trim().isEmpty()) {
//            throw new IllegalArgumentException("Автор книги не может быть пустым");
//        }
//        int currentYear = java.time.LocalDate.now().getYear();
//        if (year < 1450 || year > currentYear) {
//            throw new IllegalArgumentException("Год издания должен быть от 1450 до " + currentYear);
//        }
//        if (price < 0) {
//            throw new IllegalArgumentException("Цена не может быть отрицательной");
//        }
//    }
//}
//
//// Sealed interface для библиотечных элементов
//sealed interface LibraryItem permits PhysicalBook, EBook {
//    String getInfo();
//}
//
//// Record для физической книги
//record PhysicalBook(Book book, String shelf) implements LibraryItem {
//    @Override
//    public String getInfo() {
//        return String.format("Физическая книга: %s, автор: %s, год: %d, жанр: %s, цена: %.2f руб., полка: %s",
//                book.title(), book.author(), book.year(), book.genre().getRussianName(), book.price(), shelf);
//    }
//}
//
//// Record для электронной книги
//record EBook(Book book, String format, double sizeMB) implements LibraryItem {
//    @Override
//    public String getInfo() {
//        return String.format("Электронная книга: %s, автор: %s, год: %d, жанр: %s, цена: %.2f руб., формат: %s, размер: %.2f МБ",
//                book.title(), book.author(), book.year(), book.genre().getRussianName(), book.price(), format, sizeMB);
//    }
//}
//
//// Интерфейс для поиска
//interface Searchable {
//    boolean matches(String query);
//
//    default boolean matches(String query) {
//        return getInfo().toLowerCase().contains(query.toLowerCase());
//    }
//
//    static <T extends Searchable> List<T> search(List<T> items, String query) {
//        return items.stream()
//                .filter(item -> item.matches(query))
//                .collect(Collectors.toList());
//    }
//}
//
//// Класс библиотеки
//class Library implements Searchable {
//    private final List<LibraryItem> items = new ArrayList<>();
//
//    public void add(LibraryItem item) {
//        items.add(item);
//    }
//
//    public void printCatalog() {
//        System.out.println("Каталог библиотеки:");
//        for (LibraryItem item : items) {
//            switch (item) {
//                case PhysicalBook pb -> System.out.println(pb.getInfo());
//                case EBook eb -> System.out.println(eb.getInfo());
//                default -> System.out.println("Неизвестный тип книги");
//            }
//        }
//    }
//
//    public Map<Genre, List<LibraryItem>> groupByGenre() {
//        return items.stream()
//                .collect(Collectors.groupingBy(
//                        item -> switch (item) {
//                            case PhysicalBook pb -> pb.book().genre();
//                            case EBook eb -> eb.book().genre();
//                            default -> throw new IllegalStateException("Неизвестный тип библиотечного элемента");
//                        },
//                        () -> new EnumMap<>(Genre.class),
//                        Collectors.toList()
//                ));
//    }
//
//    public double totalValue() {
//        return items.stream()
//                .mapToDouble(item -> switch (item) {
//                    case PhysicalBook pb -> pb.book().price();
//                    case EBook eb -> eb.book().price();
//                    default -> 0;
//                })
//                .reduce(0, Double::sum);
//    }
//
//    public Optional<LibraryItem> mostExpensive() {
//        return items.stream()
//                .max(Comparator.comparingDouble(item -> switch (item) {
//                    case PhysicalBook pb -> pb.book().price();
//                    case EBook eb -> eb.book().price();
//                    default -> 0;
//                }));
//    }
//
//    public List<String> authorsByGenre(Genre genre) {
//        return items.stream()
//                .filter(item -> switch (item) {
//                    case PhysicalBook pb -> pb.book().genre() == genre;
//                    case EBook eb -> eb.book().genre() == genre;
//                    default -> false;
//                })
//                .map(item -> switch (item) {
//                    case PhysicalBook pb -> pb.book().author();
//                    case EBook eb -> eb.book().author();
//                    default -> "";
//                })
//                .distinct()
//                .sorted()
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public boolean matches(String query) {
//        return items.stream().anyMatch(item -> item.matches(query));
//    }
//}
//
//// Главный класс с методом main
//public class LibrarySystem {
//    public static void main(String[] args) {
//        Library library = new Library();
//
//        // Добавляем книги в библиотеку
//        library.add(new PhysicalBook(
//                new Book("Мастер и Маргарита", "Михаил Булгаков", 1966, Genre.FICTION, 500.0),
//                "A1"
//        ));
//        library.add(new EBook(
//                new Book("Чистый код", "Роберт Мартин", 2008, Genre.PROGRAMMING, 800.0),
//                "PDF", 2.5
//        ));
//        library.add(new PhysicalBook(
//                new Book("Краткая история времени", "Стивен Хокинг", 1988, Genre.SCIENCE, 600.0),
//                "B2"
//        ));
//        library.add(new EBook(
//                new Book("Sapiens: Краткая история человечества", "Юваль Ной Харари", 2011, Genre.HISTORY, 700.0),
//                "EPUB", 1.8
//        ));
//        library.add(new PhysicalBook(
//                new Book("Искусство цвета", "Иоханнес Иттен", 1961, Genre.ART, 900.0),
//                "C3"
//        ));
//        library.add(new EBook(
//                new Book("1984", "Джордж Оруэлл", 1949, Genre.FICTION, 400.0),
//                "MOBI", 0.7
//        ));
//        library.add(new PhysicalBook(
//                new Book("Программирование на Java", "Герберт Шилдт", 2020, Genre.PROGRAMMING, 1200.0),
//                "D4"
//        ));
//        library.add(new EBook(
//                new Book("История России", "Николай Карамзин", 1818, Genre.HISTORY, 1000.0),
//                "PDF", 5.2
//        ));
//
//        // Демонстрация работы методов
//        System.out.println("=== Печать каталога ===");
//        library.printCatalog();
//
//        System.out.println("\n=== Группировка по жанрам ===");
//        Map<Genre, List<LibraryItem>> grouped = library.groupByGenre();
//        grouped.forEach((genre, items) -> {
//            System.out.println("Жанр: " + genre.getRussianName());
//            items.forEach(item -> System.out.println("  - " + item.getInfo()));
//        });
//
//        System.out.println("\n=== Общая стоимость всех книг ===");
//        System.out.println("Общая стоимость: " + library.totalValue() + " руб.");
//
//        System.out.println("\n=== Самая дорогая книга ===");
//        library.mostExpensive().ifPresentOrElse(
//                item
