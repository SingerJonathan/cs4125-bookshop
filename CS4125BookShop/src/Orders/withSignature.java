package Orders;

public class withSignature extends Book {
    public withSignature(Book book) {
        super(book.getName(), book.getAuthor(), book.getGenre(), book.getPublisher(), book.getPrice() * 2);
    }
}
