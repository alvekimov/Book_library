public class BookNotFoundException extends Exception{
    public BookNotFoundException(){
        super("Ошибка! Книга с данным названием в базе не найдена!" );
    }
}