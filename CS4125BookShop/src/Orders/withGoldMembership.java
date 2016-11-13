package Orders;

public class withGoldMembership extends Book {
    public withGoldMembership(Book book) {
        super(book.getName(), book.getAuthor(), book.getGenre(), book.getPublisher(), book.getPrice() - (book.getPrice() * 0.15));
    }
}
