package Orders;

public class withSilverMembership extends Book {
    public withSilverMembership(Book book) {
        super(book.getName(), book.getAuthor(), book.getGenre(), book.getPublisher(), book.getPrice() - (book.getPrice() * 0.1));
    }
}
