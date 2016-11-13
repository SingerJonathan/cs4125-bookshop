package CustomerControls;

public interface CustomerControlsInterface {
    // Performs the necessary operations for a book to be removed from stock and a new order added
    public void buyBook(String bookName, String userName, int hardback, int signed);
    
    // Processes the cancellation of an order
    public void cancelOrder(String bookName, String customerName);
    
    // Processes the return of a book
    public void returnBook(String bookName, String customerName, String returnReason);
}
