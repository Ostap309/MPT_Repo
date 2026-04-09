package PracLes4;

public class Library {
    private String name;
    private int capacity;

    public Library(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public class Book {
        private String title;
        private String author;
        private int year;

        public Book(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }

        public void getInfo() {
            System.out.println("Книга: " + title + " автора " + author + " (" + year + ") в библиотеке " + name);
        }
    }

    public static void main(String[] args) {
        Library lib = new Library("Городская библиотека", 1000);
        Library.Book book = lib.new Book("Война и мир", "Толстой Л.Н.", 1869);

        book.getInfo();
    }
}
