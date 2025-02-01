
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class UsersLibrary {

    private Set<Book> myBooks = new HashSet<>();

    private void createUsersLibrary() {
        //TODO написать создание файла личной библиотеки и проверку на наличие такого файла.
        // Библиотека должна подгружаться после авторизации пользователя
    }

    public void getAllBooks(User user) {
        String usersSurname = user.getSurname();
        try(Scanner scanner = new Scanner(new File(usersSurname.toLowerCase(Locale.ROOT)+".txt"))) {
            scanner.useDelimiter(",|\r");
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                String name = scanner.next();
                String author = scanner.next();
                int yearOfPublish = scanner.nextInt();
                String genre = scanner.next();
                Book book = new Book(id, name, author, yearOfPublish, genre);
                myBooks.add(book);
            }
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        }
        for (Book value: myBooks) {
            System.out.println(value);
        }
    }

    public void borrowBookFromLibrary(Book book) {
        myBooks.add(book);
        createUsersLibrary();
    }
}
