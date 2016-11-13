package Orders;

public class withBronzeMembership extends Book {
    public withBronzeMembership(Book book) {
        super(book.getName(), book.getAuthor(), book.getGenre(), book.getPublisher(), book.getPrice() - (book.getPrice() * 0.05));
    }
}
