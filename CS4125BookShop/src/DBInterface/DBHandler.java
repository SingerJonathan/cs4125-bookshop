package DBInterface;

import Customers.Customer;
import Customers.Subject;
import Orders.Book;
import Orders.Order;
import Staff.Staff;
import java.sql.ResultSet;

public interface DBHandler {
    // Returns a ResultSet if the type is SELECT, otherwise it executes the command
    // Taken from JDBC use example by UL Staff Member: James Murphy
    public ResultSet doStatement(String sql, String type);
    
    // Returns 1 if the book exists, 0 if it doesn't
    public int checkBookExists(String bookName);
    public int checkBookExists(int id);

    // Returns 1 if the book exists, 0 if it doesn't
    public int checkCustomerExists(String customerName);
    public int checkCustomerExists(int id);

    // Returns 1 if the staff exists, 0 if it doesn't
    public int checkStaffExists(String staffName);
    public int checkStaffExists(int id);
    
    // Returns 1 if the order exists, 0 if it doesn't
    public int checkOrderExists(String bookName, String customerName);
    public int checkOrderExists(int id);
    
    //Returns 1 if the corresponding row from WATCHLIST exists, 0 if it doesn't
    public int checkStockObserverExists(int bookID, int customerID);
    
    // Returns 0 if the user is not found, 1 if the user is a customer, 2 if the user is a store staff member, or 3 if the user is a warehouse worker
    public int checkUser(String name);
    
    // Returns the ID of book identified by bookName
    public int getBookID(String bookName);
    
    // Returns the ID of staff identified by staffName
    public int getStaffID(String staffName);
    
    // Returns the amount of rows in the BOOKS table where EXIST=1 (book hasn't been deleted)
    public int countBooks();
    
    // Returns the amount of rows in the BOOKS table whether they have been "deleted" or not
    public int countAllBooks();
    
    // Returns the amount of rows in the STAFF table whether they have been "deleted" or not
    public int countAllStaff();
    
    // Returns the amount of rows in the CUSTOMERS table whether they have been "deleted" or not
    public int countAllCustomers();
    
    // Returns the amount of rows in the ORDERS table
    public int countOrders();
    
    // Returns the amount of rows in the WATCHLIST table
    public int countStockObservers();
    
    // Adds a new row to BOOKS, STORESTOCK and WAREHOUSESTOCK
    public void insertBook(Book book);
    
    // Adds a new row to CUSTOMERS
    public void insertCustomer(Customer customer);
    
    // Adds a new row to STAFF
    public void insertStaff(Staff staff);
    
    // Adds a new row to ORDERS
    public void insertOrder(Order order);
    
    // Adds a new row to WATCHLIST
    public void insertStockObserver(int bookID, int customerID);
    
    // Removes row from BOOKS (identified by id or name)
    public void deleteBook(int id);
    public void deleteBook(String name);
    
    // Removes row from STAFF (identified by id or name)
    public void deleteStaff(int id);
    public void deleteStaff(String name);

    // Removes row from CUSTOMER (identified by name)
    public void deleteCustomer(String name);
    
    // Removes rows from WATCHLIST (identified by bookID)
    public void deleteStockObservers(int bookID);
    
    // Updates the values of a row in BOOKS (identified by id)
    public void updateBook(int id, Book book);
    
    // Updates the values of a row in STAFF (identified by id)
    public void updateStaff(int id, Staff staff);

    // Updates the values of a row in CUSTOMERS (identified by id)
    public void updateCustomer(int id, Customer customer);
    
    // Updates the RETURNED and RETURN_REASON values of a row in ORDERS (identified by id)
    public void returnOrder(int id, String returnReason);
    
    // Updates the CANCELLED value of a row in ORDERS (identified by id)
    public void cancelOrder(int id);
    
    // Updates the PROCESSED value of a row in ORDERS (identified by id)
    public void processOrder(int id);
    
    // Updates the values of a row in STORESTOCK (book identified by id)
    public void updateStoreStock(int id, int amount);
    
    // Returns the amount associated with a row in STORESTOCK identified by (book) id
    public int getStoreStockAmount(int id);
    
    // Updates the values of a row in WAREHOUSESTOCK (book identified by id)
    public void updateWarehouseStock(int id, int amount);
    
    // Returns the amount associated with a row in WAREHOUSESTOCK identified by (book) id
    public int getWarehouseStockAmount(int id);
    
    // Returns a Book object defined by values from the database
    public Book getBook(String name);
    public Book getBook(int id);
    
    // Returns a Staff object defined by values from the database
    public Staff getStaff(String name);
    public Staff getStaff(int id);
    
    // Returns a Customer object defined by values from the database
    public Customer getCustomer(String name);
    public Customer getCustomer(int id);
    
    // Returns an Order object defined by values from the database
    public Order getOrder(String bookName, String customerName);
    public Order getOrder(String bookName, String customerName, int processed, int cancelled, int returned);
    public Order getOrder(int id);
    
    // Returns a Subject object defined by all rows from the WATCHLIST table
    public Subject getStockObserverSubject();
    
    // Closes the ResultSet, Statement and database Connection
    public void close();
}
