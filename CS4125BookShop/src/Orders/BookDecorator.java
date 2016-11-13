package Orders;

public class BookDecorator extends Book {
    private Book book;
    
    BookDecorator(Book book) {
        this.book = book;
    }
    
    public double getPrice() {
        return book.getPrice();
    }
}
/*Book bronzeHardbackSignedBook = new withBronzeMembership (
                                  new withHardback (
                                  new withSignature (
                                  new Book("Harry Potter", "J.J Rowling", "Puffin", 29.99) ) );
int price = bronzeHardbackSignedBook.getPrice(); // returns 29.99 *2 + 2.00 - 3.09 = 58.89*/