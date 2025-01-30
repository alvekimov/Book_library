package UsersBlock;

public class User {

    private String surname;
    private String name;
    private String password;

    public User(String surname, String name, String password) {
        this.surname = surname;
        this.name = name;
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
