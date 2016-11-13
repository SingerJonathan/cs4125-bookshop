package CustomerControls;

public class CustomerControls implements CustomerControlsInterface {
    @Override
    public void buyBook(String bookName, String userName, int hardback, int signed) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void cancelOrder(String bookName, String customerName) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void returnBook(String bookName, String customerName, String returnReason) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }
}
