package Orders;

public class withHardback extends Book {
    public withHardback(Book book) {
        super(book.getName(), book.getAuthor(), book.getGenre(), book.getPublisher(), book.getPrice() + 2);
    }
}
