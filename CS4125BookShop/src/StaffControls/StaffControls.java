package StaffControls;

public class StaffControls implements StaffControlsInterface {
    @Override
    public void addBook(String name, String author, String genre, String publisher, double price) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void addStaff(String name, String address, String email, int phoneNumber, int warehouse) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void addStock(String name, int amount) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void createAccount(String name, String email, int memship) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteBook(String name) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteCustomer(String name) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteStaff(String name) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void editBook(int id, String name, String author, String genre, String publisher, double price) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void editCustomer(int id, String name, String email, int membership) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void editStaff(int id, String name, String address, String email, int phoneNumber, int warehouse) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void removeStock(String name, int amount) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }
}
