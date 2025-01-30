import UsersBlock.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private static List<Book> books;
    private int nextId;

    public Library() {
        books = new ArrayList<>();
        this.nextId = 1;
    }

    // Метод для добавления книги в библиотеку
    public void addBook(String title, String author, int year, String genre) {
        Book newBook = new Book(nextId++, title, author, year, genre);
        books.add(newBook);
        System.out.println("Книга добавлена: " + newBook);
    }

    // Метод для удаления книги из библиотеки
    public void removeBook(String scanTitleDelete) {
        boolean removed = false; // Флаг, указывающий, была ли удалена книга
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(scanTitleDelete) ||
                    Integer.toString(book.getId()).equalsIgnoreCase(scanTitleDelete)) {
                books.remove(i);
                System.out.println("Книга удалена: " + book);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Книга не найдена.");
        }
    }

    // Метод для поиска книги в библиотеке
    public void findBook(String scanTextSearch) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(scanTextSearch) ||
                    Integer.toString(book.getId()).equals(scanTextSearch)) {
                System.out.println("Найдена книга: " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Книги с заданными параметрами не найдены.");
        }
    }

    public void printAllBooks() {
        System.out.println("\nСписок книг в библиотеке:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void booksSaver () throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                String line = book.getId() + "," + book.getTitle() + ", " + book.getAuthor() + ", " +
                        book.getYear() + ", " + book.getGenre();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Книги сохранены в файл 'books.txt'."); // Уведомляем о сохранении книг.
        }
    }

    public static void booksLoader() throws IOException {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if (parts.length == 5) { // Проверяем, что все необходимые поля присутствуют
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    String genre = parts[3];
                    int year = Integer.parseInt(parts[4]);

                    books.add(new Book(id, title, author, year, genre)); // Создаём объект Book
                }
            }
        }

    }

    public void loadLibrary() { // загружаем список пользователей из файла

        try(Scanner scanner = new Scanner(new File("books.txt"))) {
            scanner.useDelimiter(",|\r");
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                String title = scanner.next();
                String author = scanner.next();
                int year = scanner.nextInt();
                String genre = scanner.next();
                Book book = new Book(id, title, author, year, genre);
                books.add(book);
            }
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        }
    }
}