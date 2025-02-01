public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int year;


    public Book(int id, String title, String author, int year, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Книга: '" + title + "', Автор: '" + author + "', Год: " + year + ", Жанр: '" + genre + "'";
    }
}