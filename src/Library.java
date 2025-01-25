import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private int nextId;

    public Library() {
        this.books = new ArrayList<>();
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
}