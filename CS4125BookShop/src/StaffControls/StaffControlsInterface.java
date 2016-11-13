package StaffControls;

public interface StaffControlsInterface {
    // Adds a new book to the database
    public void addBook(String name, String author, String genre, String publisher, double price);

    // Adds a new staff member to the database (warehouse 1 for warehouse staff member, 0 for store staff member)
    public void addStaff(String name, String address, String email, int phoneNumber, int warehouse);

    // Adds amount to stock of book identified by name
    public void addStock(String name, int amount);

    // Creates a new customer account
    public void createAccount(String name, String email, int memship);

    // Deletes a book from the database
    public void deleteBook(String name);

    // Deletes a customer from the database
    public void deleteCustomer(String name);

    // Deletes a staff member from the database
    public void deleteStaff(String name);

    // Edits the values of a book
    public void editBook(int id, String name, String author, String genre, String publisher, double price);

    // Edits the values of a customer
    public void editCustomer(int id, String name, String email, int membership);

    // Edits the values of a staff member
    public void editStaff(int id, String name, String address, String email, int phoneNumber, int warehouse);

    // Removes amount from stock of book identified by name
    public void removeStock(String name, int amount);
}
