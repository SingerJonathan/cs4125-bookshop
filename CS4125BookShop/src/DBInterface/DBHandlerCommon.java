package DBInterface;

import Customers.Customer;
import Customers.StockObserver;
import Customers.Subject;
import Orders.Book;
import Orders.Order;
import Staff.Staff;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHandlerCommon implements DBHandler {
    
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String ip = "localhost";
    private String db = "cs4125bookshop";
    private String user = "root";
    private String pw = "";
    
    // Default constructor creates a connection to MySQL using IP localhost, database cs4125bookshop, 
    // username sqluser, password sqluserpw
    // Taken from (and edited) JDBC use example by UL Staff Member: James Murphy
    public DBHandlerCommon() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Set up the connection with the DBMS
            connect = DriverManager.getConnection("jdbc:mysql://"+ip+"/?"+"user="+user+"&password="+pw);
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            // Create the database if it doesn't already exist
            statement.execute("CREATE DATABASE IF NOT EXISTS cs4125bookshop");
            
            // Connect to the Bookshop DB
            connect = DriverManager.getConnection("jdbc:mysql://"+ip+"/"+db+"?user="+user+"&password="+pw);
            statement = connect.createStatement();
            
            // Create the BOOKS table if it doesn't already exist
            statement.execute("CREATE TABLE IF NOT EXISTS BOOKS (" +
                            "ID INT NOT NULL AUTO_INCREMENT, " +
                            "NAME VARCHAR(30) NOT NULL, " +
                            "AUTHOR VARCHAR(30) NOT NULL, " +
                            "GENRE VARCHAR(30) NOT NULL, " +
                            "PUBLISHER VARCHAR(30) NOT NULL," +
                            "PRICE DECIMAL(4,2) DEFAULT 0.00, " +
                            "EXIST INT DEFAULT 1," +
                            "PRIMARY KEY (ID))");
            
            // Create the STAFF table if it doesn't already exist
            statement.execute("CREATE TABLE IF NOT EXISTS STAFF (" +
                            "ID INT NOT NULL AUTO_INCREMENT, " +
                            "NAME VARCHAR(30) NOT NULL, " +
                            "ADDRESS VARCHAR(60) NOT NULL, " +
                            "EMAIL VARCHAR(30) NOT NULL, " +
                            "PHONE INT NOT NULL, " +
                            "WAREHOUSE INT DEFAULT 0, " +
                            "EXIST INT DEFAULT 1," +
                            "PRIMARY KEY (ID))");
            
            // Create the CUSTOMERS table if it doesn't already exist
            statement.execute("CREATE TABLE IF NOT EXISTS CUSTOMERS (" +
                            "ID INT NOT NULL AUTO_INCREMENT, " +
                            "NAME VARCHAR(30) NOT NULL, " +
                            "EMAIL VARCHAR(30) NOT NULL, " +
                            "MEMSHIP INT, " +
                            "EXIST INT DEFAULT 1," +
                            "PRIMARY KEY (ID))");
            
            // Create the ORDERS table if it doesn't already exist
            statement.execute("CREATE TABLE IF NOT EXISTS ORDERS (" +
                            "ID INT NOT NULL AUTO_INCREMENT, " +
                            "CUSTOMER_ID INT NOT NULL, " +
                            "BOOK_ID INT NOT NULL, " +
                            "PRICE DECIMAL(4,2) DEFAULT 0.00, " +
                            "DATE VARCHAR(10) NOT NULL, " +
                            "PROCESSED INT DEFAULT 0, " +
                            "CANCELLED INT DEFAULT 0, " +
                            "RETURNED INT DEFAULT 0, " +
                            "RETURN_REASON VARCHAR(60), " +
                            "PRIMARY KEY (ID)," +
                            "FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(ID)," +
                            "FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID))");
            
            // Create the STORESTOCK table if it doesn't already exist
            statement.execute("CREATE TABLE IF NOT EXISTS STORESTOCK (" +
                            "ID INT NOT NULL, " +
                            "AMOUNT INT NOT NULL, " +
                            "FOREIGN KEY (ID) REFERENCES BOOKS(ID))");
            
            // Create the WAREHOUSESTOCK table if it doesn't already exist
            statement.execute("CREATE TABLE IF NOT EXISTS WAREHOUSESTOCK (" +
                            "ID INT NOT NULL, " +
                            "AMOUNT INT NOT NULL, " +
                            "FOREIGN KEY (ID) REFERENCES BOOKS(ID))");
            
            // Create the WATCHLIST table if it doesn't already exist
            statement.execute("CREATE TABLE IF NOT EXISTS WATCHLIST (" +
                            "BOOK_ID INT NOT NULL, " +
                            "CUSTOMER_ID INT NOT NULL, " +
                            "FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID), " +
                            "FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(ID))");
        } catch (Exception e) {
        }
    }
    
    // Returns a ResultSet if the type is SELECT, otherwise it executes the command
    // Taken from JDBC use example by UL Staff Member: James Murphy
    @Override
    public ResultSet doStatement(String sql, String type) {
        ResultSet result = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            switch(type) {
                case "SELECT": result = statement.executeQuery(sql);    break;
                default:       statement.executeUpdate(sql);            break;
            }
        } catch (Exception e) {
        }
        return result;
    }
    
    // Returns 1 if the book exists, 0 if it doesn't
    @Override
    public int checkBookExists(String bookName) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM BOOKS WHERE NAME='"+bookName+"' AND EXIST=1)", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    @Override
    public int checkBookExists(int id) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM BOOKS WHERE ID="+id+" AND EXIST=1)", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }

    // Returns 1 if the customer exists, 0 if it doesn't
    @Override
    public int checkCustomerExists(String customerName) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM CUSTOMERS WHERE NAME='"+customerName+"' AND EXIST=1)", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    @Override
    public int checkCustomerExists(int id) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM CUSTOMERS WHERE ID="+id+" AND EXIST=1)", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }

    // Returns 1 if the staff exists, 0 if it doesn't, warehouse 0 for store staff, 1 for warehouse staff
    @Override
    public int checkStaffExists(String staffName) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM STAFF WHERE NAME='"+staffName+"' AND EXIST=1)", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    @Override
    public int checkStaffExists(int id) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM STAFF WHERE ID="+id+" AND EXIST=1)", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    @Override
    public int checkOrderExists(int id) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM ORDERS WHERE ID="+id+")", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    @Override
    public int checkOrderExists(String bookName, String customerName) {
        int customerID = getCustomer(customerName).getID();
        int bookID = getBookID(bookName);
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID+")", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    @Override
    public int checkStockObserverExists(int bookID, int customerID) {
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM WATCHLIST WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID+")", "SELECT");
        int i = 0;
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    // Returns 0 if the user is not found, 1 if the user is a customer, 2 if the user is a store staff member, or 3 if the user is a warehouse worker
    @Override
    public int checkUser(String name) {
        int i = checkCustomerExists(name);
        if (i == 1)
            return 1;
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM STAFF WHERE NAME='"+name+"' AND EXIST=1 AND WAREHOUSE=0)", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        if (i == 1)
            return 2;
        resultSet = doStatement("SELECT EXISTS(SELECT * FROM STAFF WHERE NAME='"+name+"' AND EXIST=1 AND WAREHOUSE=1)", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        if (i == 1)
            return 3;
        return 0;
    }
    
    @Override
    public int getBookID(String bookName) {
        resultSet = doStatement("SELECT ID FROM BOOKS WHERE NAME='"+bookName+"'", "SELECT");
        int id = 0;
        try {
            resultSet.first();
            id = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return id;
    }
    
    @Override
    public int getStaffID(String staffName) {
        resultSet = doStatement("SELECT ID FROM STAFF WHERE NAME='"+staffName+"'", "SELECT");
        int id = 0;
        try {
            resultSet.first();
            id = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return id;
    }
    
    // Returns the amount of rows in the BOOKS table where EXIST=1 (book hasn't been deleted)
    @Override
    public int countBooks() {
        int i = 0;
        resultSet = doStatement("SELECT COUNT(*) FROM BOOKS WHERE EXIST=1", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    // Returns the amount of rows in the BOOKS table whether they have been "deleted" or not
    @Override
    public int countAllBooks() {
        int i = 0;
        resultSet = doStatement("SELECT COUNT(*) FROM BOOKS", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    // Returns the amount of rows in the STAFF table whether they have been "deleted" or not
    @Override
    public int countAllStaff() {
        int i = 0;
        resultSet = doStatement("SELECT COUNT(*) FROM STAFF", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }

    @Override
    public int countAllCustomers() {
        int i = 0;
        resultSet = doStatement("SELECT COUNT(*) FROM CUSTOMERS", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    // Returns the amount of rows in the ORDERS table
    @Override
    public int countOrders() {
        int i = 0;
        resultSet = doStatement("SELECT COUNT(*) FROM ORDERS", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }

    @Override
    public int countStockObservers() {
        int i = 0;
        resultSet = doStatement("SELECT COUNT(*) FROM WATCHLIST", "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    // Returns the amount associated with a row in STORESTOCK identified by (book) id
    @Override
    public int getStoreStockAmount(int id) {
        resultSet = doStatement("SELECT AMOUNT FROM STORESTOCK WHERE ID="+id, "SELECT");
        int amount = 0;
        try {
            resultSet.first();
            amount = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return amount;
    }
    
    // Returns the amount associated with a row in WAREHOUSESTOCK identified by (book) id
    @Override
    public int getWarehouseStockAmount(int id) {
        resultSet = doStatement("SELECT AMOUNT FROM WAREHOUSESTOCK WHERE ID="+id, "SELECT");
        int amount = 0;
        try {
            resultSet.first();
            amount = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return amount;
    }
    
    @Override
    public Book getBook(String name) {
        String author = "";
        String genre = "";
        String publisher = "";
        double price = 0;
        Book book = new Book();
        int i = checkBookExists(name);
        if (i == 1){
            resultSet = doStatement("SELECT AUTHOR FROM BOOKS WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                author = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT GENRE FROM BOOKS WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                genre = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PUBLISHER FROM BOOKS WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                publisher = resultSet.getString(1);
            } catch (Exception e) {
            }            
            resultSet = doStatement("SELECT PRICE FROM BOOKS WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                price = resultSet.getDouble(1);
            } catch (Exception e) {
            }
            book = new Book(name, author, genre, publisher, price);
        }
        return book;
    }
    
    @Override
    public Book getBook(int id) {
        String name = "";
        String author = "";
        String genre = "";
        String publisher = "";
        double price = 0;
        Book book = new Book();
        int i = checkBookExists(id);
        if (i == 1){
            resultSet = doStatement("SELECT NAME FROM BOOKS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                name = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT AUTHOR FROM BOOKS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                author = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT GENRE FROM BOOKS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                genre = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PUBLISHER FROM BOOKS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                publisher = resultSet.getString(1);
            } catch (Exception e) {
            }            
            resultSet = doStatement("SELECT PRICE FROM BOOKS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                price = resultSet.getDouble(1);
            } catch (Exception e) {
            }
            book = new Book(name, author, genre, publisher, price);
        }
        return book;
    }
    
    @Override
    public Staff getStaff(String name) {
        String address = "";
        String email = "";
        int phoneNumber = 0;
        int warehouse = 0;
        Staff staff = new Staff();
        int i = checkStaffExists(name);
        if (i == 1){
            resultSet = doStatement("SELECT ADDRESS FROM STAFF WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                address = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT EMAIL FROM STAFF WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                email = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PHONE FROM STAFF WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                phoneNumber = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT WAREHOUSE FROM STAFF WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                warehouse = resultSet.getInt(1);
            } catch (Exception e) {
            }
            staff = new Staff(name, address, email, phoneNumber, warehouse);
        }
        return staff;
    }
    
    @Override
    public Customer getCustomer(String name) {
        int id = 0;
        String email = "";
        int memship = 0;
        Customer customer = new Customer();
        int i = checkCustomerExists(name);
        if (i == 1){
            resultSet = doStatement("SELECT ID FROM CUSTOMERS WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                id = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT EMAIL FROM CUSTOMERS WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                email = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT MEMSHIP FROM CUSTOMERS WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                memship = resultSet.getInt(1);
            } catch (Exception e) {
            }
            customer = new Customer(id, name, email, memship);
        }
        return customer;
    }
    
    @Override
    public Customer getCustomer(int id) {
        String name = "";
        String email = "";
        int memship = 0;
        Customer customer = new Customer();
        int i = checkCustomerExists(id);
        if (i == 1){
            resultSet = doStatement("SELECT NAME FROM CUSTOMERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                name = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT EMAIL FROM CUSTOMERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                email = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT MEMSHIP FROM CUSTOMERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                memship = resultSet.getInt(1);
            } catch (Exception e) {
            }
            customer = new Customer(id, name, email, memship);
        }
        return customer;
    }
    
    @Override
    public Staff getStaff(int id) {
        String name = "";
        String address = "";
        String email = "";
        int phoneNumber = 0;
        int warehouse = 0;
        Staff staff = new Staff();
        int i = checkStaffExists(id);
        if (i == 1){
            resultSet = doStatement("SELECT NAME FROM STAFF WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                name = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT ADDRESS FROM STAFF WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                address = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT EMAIL FROM STAFF WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                email = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PHONE FROM STAFF WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                phoneNumber = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT WAREHOUSE FROM STAFF WHERE NAME='"+name+"'", "SELECT");
            try {
                resultSet.first();
                warehouse = resultSet.getInt(1);
            } catch (Exception e) {
            }
            staff = new Staff(name, address, email, phoneNumber, warehouse);
        }
        return staff;
    }
    
    @Override
    public Order getOrder(String bookName, String customerName) {
        int id = 0;
        int customerID = getCustomer(customerName).getID();
        int bookID = getBookID(bookName);
        double price = 0;
        String date = "";
        int processed = 0;
        int cancelled = 0;
        int returned = 0;
        String returnReason = "";
        Order order = new Order();
        int i = checkOrderExists(bookName, customerName);
        if (i == 1){
            resultSet = doStatement("SELECT ID FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID, "SELECT");
            try {
                resultSet.first();
                id = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PRICE FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID, "SELECT");
            try {
                resultSet.first();
                price = resultSet.getDouble(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT DATE FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID, "SELECT");
            try {
                resultSet.first();
                date = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PROCESSED FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID, "SELECT");
            try {
                resultSet.first();
                processed = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT CANCELLED FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID, "SELECT");
            try {
                resultSet.first();
                cancelled = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT RETURNED FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID, "SELECT");
            try {
                resultSet.first();
                returned = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT RETURN_REASON FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID, "SELECT");
            try {
                resultSet.first();
                returnReason = resultSet.getString(1);
            } catch (Exception e) {
            }
            order = new Order(id, customerID, bookID, price, date, processed, cancelled, returned, returnReason);
        }
        return order;
    }
    
    @Override
    public Order getOrder(String bookName, String customerName, int processed, int cancelled, int returned) {
        int id = 0;
        int customerID = getCustomer(customerName).getID();
        int bookID = getBookID(bookName);
        double price = 0;
        String date = "";
        String returnReason = "";
        Order order = new Order();
        int i = checkOrderExists(bookName, customerName);
        if (i == 1){
            resultSet = doStatement("SELECT ID FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID+
                    " AND RETURNED="+returned+" AND PROCESSED="+processed+" AND CANCELLED="+cancelled, "SELECT");
            try {
                resultSet.first();
                id = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PRICE FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID+
                    " AND RETURNED="+returned+" AND PROCESSED="+processed+" AND CANCELLED="+cancelled, "SELECT");
            try {
                resultSet.first();
                price = resultSet.getDouble(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT DATE FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID+
                    " AND RETURNED="+returned+" AND PROCESSED="+processed+" AND CANCELLED="+cancelled, "SELECT");
            try {
                resultSet.first();
                date = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT RETURN_REASON FROM ORDERS WHERE CUSTOMER_ID="+customerID+" AND BOOK_ID="+bookID+
                    " AND RETURNED="+returned+" AND PROCESSED="+processed+" AND CANCELLED="+cancelled, "SELECT");
            try {
                resultSet.first();
                returnReason = resultSet.getString(1);
            } catch (Exception e) {
            }
            order = new Order(id, customerID, bookID, price, date, processed, cancelled, returned, returnReason);
        }
        return order;
    }
    
    @Override
    public Order getOrder(int id) {
        int customerID = 0;
        int bookID = 0;
        double price = 0;
        String date = "";
        int processed = 0;
        int cancelled = 0;
        int returned = 0;
        String returnReason = "";
        Order order = new Order();
        int i = checkOrderExists(id);
        if (i == 1){
            resultSet = doStatement("SELECT CUSTOMER_ID FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                customerID = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT BOOK_ID FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                bookID = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PRICE FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                price = resultSet.getDouble(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT DATE FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                date = resultSet.getString(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT PROCESSED FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                processed = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT CANCELLED FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                cancelled = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT RETURNED FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                returned = resultSet.getInt(1);
            } catch (Exception e) {
            }
            resultSet = doStatement("SELECT RETURN_REASON FROM ORDERS WHERE ID="+id, "SELECT");
            try {
                resultSet.first();
                returnReason = resultSet.getString(1);
            } catch (Exception e) {
            }
            order = new Order(id, customerID, bookID, price, date, processed, cancelled, returned, returnReason);
        }
        return order;
    }

    @Override
    public Subject getStockObserverSubject() {
        Subject subject = new Subject();
        int bookID = 0;
        int customerID = 0;
        resultSet = doStatement("SELECT * FROM WATCHLIST", "SELECT");
        try {
            while(resultSet.next()) {
                bookID = resultSet.getInt(1);
                customerID = resultSet.getInt(2);
                new StockObserver(subject, customerID, bookID);
            }
        } catch (Exception e) {
        }
        return subject;
    }
    
    // Closes the ResultSet, Statement and database Connection
    @Override
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void updateStoreStock(int id, int amount) {
        int i = checkBookExists(id);
        if (i == 1){
            doStatement("UPDATE STORESTOCK SET AMOUNT="+amount+" WHERE ID="+id, "UPDATE");
        }
    }

    @Override
    public void insertBook(Book book) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void insertCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void insertStaff(Staff staff) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void insertOrder(Order order) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteBook(int id) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteBook(String name) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteStaff(int id) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteStaff(String name) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteCustomer(String name) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void updateBook(int id, Book book) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void updateStaff(int id, Staff staff) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void updateCustomer(int id, Customer customer) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void returnOrder(int id, String returnReason) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void cancelOrder(int id) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void processOrder(int id) {
        throw new UnsupportedOperationException("Not supported by this class.");
    } 

    @Override
    public void updateWarehouseStock(int id, int amount) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void insertStockObserver(int bookID, int customerID) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }

    @Override
    public void deleteStockObservers(int bookID) {
        throw new UnsupportedOperationException("Not supported by this class.");
    }
}
