import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Authorisation {

    private ArrayList<User> users = new ArrayList<>();

    public void loadUsers() { // загружаем список пользователей из файла

        try(Scanner scanner = new Scanner(new File("users.txt"))) {
            scanner.useDelimiter(",|\r");
            while (scanner.hasNext()) {
                String surname = scanner.next();
                String name = scanner.next();
                String password = scanner.next();
                User user = new User(surname, name, password);
                users.add(user);
            }
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        }
    }

    public boolean checkUser(String surnameCheck, String passwordCheck) { // авторизация по фамилии и паролю
        for(User value: users) {
            if(surnameCheck.equals(value.getSurname()) & passwordCheck.equals(value.getPassword())) {
                System.out.println(value.getSurname() + " " + value.getName() + " добро пожаловать в библиотеку");
                return false;
            }
        }
        return true;
    }

    public void addUser(String surname, String name, String password) { // добавление нового пользователя
        for(User value: users) {
            if(value.getSurname().equalsIgnoreCase(surname) && value.getName().equalsIgnoreCase(name)) { // проверка существует ли уже такая учётная запись
                System.out.println("Такой пользователь уже существует. Пожалуйста проверьте данные или заргистрируйтесь");
                break;
            } else { // создание новой учётной записи и добавление в файл БД
                try(FileWriter writer = new FileWriter("users.txt", true)) {
                    String newUser = "\r"+ surname + "," + name + "," + password;
                    writer.write(newUser);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                System.out.println("Новый пользователь " + surname + " " + name + " создан");
                break;
            }
        }
        loadUsers(); // перезагружаем данные БД в массив для работы
    }
}
