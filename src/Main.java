import UsersBlock.Authorisation;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BookNotFoundException, IOException {
        /*Система управления библиотекой
        Цель проекта: разработать приложение для управления библиотекой, позволяющее добавлять, удалять, искать и выдавать книги.
                Требования к функционалу:
        1. Возможность добавления новых книг в библиотеку (с указанием названия, автора, жанра, года издания).
        2. Удаление книги из базы по ID или названию.
        3. Поиск книги по одному из параметров (ID, названию, автору).
        4. Регистрация и управление пользователями библиотеки (добавление/удаление пользователя).
        5. Функция "выдача книги": проверка, доступна ли книга, и закрепление её за пользователем.
        6. Возможность вернуть книгу, освобождая её для других пользователей.

        Техничка:
        Использовать Java и текстовые файлы для хранения данных (например, .txt или .csv).
        Основной интерфейс программы — консольный.

        Реализовать использование коллекций Java (ArrayList, HashMap).*/

        Authorisation a = new Authorisation();
        a.loadUsers();
        System.out.println("Добрый день Вас приветствует электронный помощник библиотеки.");
        System.out.println("Please enter \"0\" to log in into your library with your surname and password or \"1\" to create a new account");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 0) {
            while (true) {
                System.out.print("Surname ");
                String surname = scanner.next();
                System.out.print("Password ");
                String password = scanner.next();
                if(!a.checkUser(surname, password)) {
                    break;
                } else {
                    System.out.println("Wrong Name and/or Password. Please click \"Enter\" to try again or put 1 - to create new account");
                    if(scanner.next().equals("1")) {
                        System.out.print("Surname ");
                        surname = scanner.next();
                        System.out.print("Name ");
                        String name = scanner.next();
                        System.out.print("Password ");
                        password = scanner.next();
                        a.addUser(surname, name, password);
                    }

                }
            }
        }else if (choice == 1) {
            System.out.println("Please enter your data:");
            System.out.print("Surname ");
            String surname = scanner.next();
            System.out.print("Name ");
            String name = scanner.next();
            System.out.print("Password ");
            String password = scanner.next();
            a.addUser(surname, name, password);
            System.out.println("Please log in into your library with your surname and password");
        }



        System.out.println("Для взаимодействия с библиотекой ознакомьтесь со списком команд:\n" +
                "1 - Добавить книгу в библиотеку;\n" +
                "2 - Удалить книгу по ID или названию;\n" +
                "3 - Поиск книги по одному из параметров (ID, названию, автору);\n" +
                "4 - Регистрация и управление пользователями библиотеки (добавление/удаление пользователя);\n" +
                "5 - Для получения книги в пользование;\n" +
                "6 - Для возвращения книги в библиотеку;\n" +
                "7 - Повторить список команд;\n" +
                "8 - Загрузить список книг;\n" +
                "9 - Выход из программы.");
        Library library = new Library();
        library.loadLibrary();
        Scanner sc = new Scanner(System.in);
        int numberCommand = 0;

        while (true) {
            numberCommand = sc.nextInt();
            switch (numberCommand) {
                case 1:
                    // Добавление книги
                    System.out.print("Введите название книги: ");
                    String scanTitle = sc.next();
                    sc.nextLine();
                    System.out.print("Введите автора книги: ");
                    String scanAuthor = sc.nextLine();
                    int scanYear = 0;
                    while (true) {
                        System.out.print("Введите год написания книги: ");
                        String yearInput = sc.nextLine();
                        try {
                            scanYear = Integer.parseInt(yearInput);
                            break; // Выход из цикла, если ввод корректен
                        } catch (NumberFormatException e) {
                            System.out.println("Пожалуйста, введите правильный год (число).");
                        }
                    }
                    System.out.print("Введите жанр книги: ");
                    String scanGenre = sc.nextLine();
                    library.addBook(scanTitle, scanAuthor, scanYear, scanGenre);
                    Library.booksSaver();
                    break;
                case 2:
                    // Удаление книги
                    System.out.print("Введите ID или название книги для удаления: ");
                    String scanTitleDelete = sc.next();
                    library.removeBook(scanTitleDelete);
                    Library.booksSaver();
                    break;
                case 3:
                    //поиск книги
                    System.out.print("Введите название книги или номер id для поиска: ");
                    String scanTextSearch = sc.next();
                    sc.nextLine();
                    library.findBook(scanTextSearch);
                    break;
                case 4:
                    //работа с пользователем
                    break;
                case 5:
                    //Получение книги
                    break;
                case 6:
                    //Возврат книги
                    break;
                case 7:
                    //Повторение команд
                    //Временно поиск всех книг, что есть
                    library.printAllBooks();
                    break;
                case 8:
                    //Загрузка списка книг
                    Library.booksLoader();
                    System.out.println("Список книг успешно загружен.");
                    break;
                case 9:
                    System.out.println("Программа завершена.");
                    sc.close();
                    return;

                default:
                    System.out.println("Введена неверная команда");
            }
        }
    }
}