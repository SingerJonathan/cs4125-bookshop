package DBInterface;

import Orders.Book;
import Customers.Customer;
import Staff.Staff;

public class StaffDBHandler extends DBHandlerCommon {
    
    // Adds a new row to BOOKS, STORESTOCK and WAREHOUSESTOCK
    public void insertBook(Book book) {
        String name = book.getName();
        int i = checkBookExists(name);
        if (i == 0) {
            doStatement("insert into CS4125BOOKSHOP.BOOKS values (default, '"+book.getName()+"', '"+book.getAuthor()+"', '"
                    +book.getGenre()+"', '"+book.getPublisher()+"', "+book.getPrice()+", default)", "INSERT");
            doStatement("insert into CS4125BOOKSHOP.STORESTOCK values ("+getBookID(book.getName())+", 0)", "INSERT");
            doStatement("insert into CS4125BOOKSHOP.WAREHOUSESTOCK values ("+getBookID(book.getName())+", 0)", "INSERT");
        }
    }
    
    // Adds a new row to CUSTOMERS
    public void insertCustomer(Customer customer) {
        String name = customer.getName();
        int i = checkCustomerExists(name);
        if (i == 0) {
            doStatement("insert into CS4125BOOKSHOP.CUSTOMERS values (default, '"+customer.getName()+"', '"+customer.getEmail()+"', "
                    +customer.getMemship()+", default)", "INSERT");
        }
    }
    
    // Adds a new row to STAFF
    public void insertStaff(Staff staff) {
        String name = staff.getName();
        int i = checkStaffExists(name);
        if (i == 0) {
            doStatement("insert into CS4125BOOKSHOP.STAFF values (default, '"+staff.getName()+"', '"+staff.getAddress()+"', '"
                    +staff.getEmail()+"', "+staff.getPhoneNumber()+", "+staff.getWarehouse()+", default)", "INSERT");
        }
    }
    
    // Adds a new row to WATCHLIST
    public void insertStockObserver(int bookID, int customerID) {
        int bookExists = checkBookExists(bookID);
        int customerExists = checkCustomerExists(customerID);
        if (bookExists == 1 && customerExists == 1) {
            doStatement("insert into CS4125BOOKSHOP.WATCHLIST values ('"+bookID+"', '"+customerID+"')", "INSERT");
        }
    }
    
    // Removes row from BOOKS (identified by id)
    public void deleteBook(int id) {
        int i = checkBookExists(id);
        if (i == 1) {
            doStatement("UPDATE BOOKS SET EXIST=0 WHERE ID="+id, "UPDATE");
        }
    }
    
    // Removes row from BOOKS (identified by name)
    public void deleteBook(String name) {
        int i = checkBookExists(name);
        if (i == 1) {
            doStatement("UPDATE BOOKS SET EXIST=0 WHERE NAME='"+name+"'", "UPDATE");
        }
    }
    
    // Removes row from STAFF (identified by id)
    public void deleteStaff(int id) {
        int i = checkStaffExists(id);
        if (i == 1) {
            doStatement("UPDATE STAFF SET EXIST=0 WHERE ID="+id, "UPDATE");
        }
    }
    
    // Removes row from STAFF (identified by name)
    public void deleteStaff(String name) {
        int i = checkStaffExists(name);
        if (i == 1) {
            doStatement("UPDATE STAFF SET EXIST=0 WHERE NAME='"+name+"'", "UPDATE");
        }
    }
    
    // Removes row from CUSTOMER (identified by name)
    public void deleteCustomer(String name) {
        int i = checkCustomerExists(name);
        if (i == 1) {
            doStatement("UPDATE CUSTOMERS SET EXIST=0 WHERE NAME='"+name+"'", "UPDATE");
        }
    }
    
    // Removes rows from WATCHLIST (identified by bookID)
    public void deleteStockObservers(int bookID) {
        doStatement("DELETE FROM WATCHLIST WHERE BOOK_ID='"+bookID+"'", "UPDATE");
    }
    
    // Updates the values of a row in BOOKS (identified by id)
    public void updateBook(int id, Book book) {
        int i = checkBookExists(id);
        if (i == 1){
            doStatement("UPDATE BOOKS SET NAME='"+book.getName()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE BOOKS SET AUTHOR='"+book.getAuthor()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE BOOKS SET GENRE='"+book.getGenre()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE BOOKS SET PUBLISHER='"+book.getPublisher()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE BOOKS SET PRICE="+book.getPrice()+" WHERE ID="+id, "UPDATE");
        }
    }
    
    // Updates the values of a row in STAFF (identified by id)
    public void updateStaff(int id, Staff staff) {
        int i = checkStaffExists(id);
        if (i == 1){
            doStatement("UPDATE STAFF SET NAME='"+staff.getName()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE STAFF SET ADDRESS='"+staff.getAddress()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE STAFF SET EMAIL='"+staff.getEmail()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE STAFF SET PHONENUMBER='"+staff.getPhoneNumber()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE STAFF SET WAREHOUSE='"+staff.getWarehouse()+"' WHERE ID="+id, "UPDATE");
        }
    }
    
    // Updates the values of a row in CUSTOMER (identified by id)
    public void updateCustomer(int id, Customer customer) {
        int i = checkCustomerExists(id);
        if (i == 1){
            doStatement("UPDATE CUSTOMERS SET NAME='"+customer.getName()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE CUSTOMERS SET EMAIL='"+customer.getEmail()+"' WHERE ID="+id, "UPDATE");
            doStatement("UPDATE CUSTOMERS SET MEMSHIP='"+customer.getMemship()+"' WHERE ID="+id, "UPDATE");
        }
    }
    
    // Returns the amount of rows in the ORDERS table where PROCESSED=0 and CUSTOMER_ID corresponds to that of customerName
    /*public int countUnprocessedOrders(String customerName) {
        int customerID = getCustomer(customerName).getID();
        int i = 0;
        resultSet = doStatement("SELECT COUNT(*) FROM ORDERS WHERE PROCESSED=0 AND CUSTOMER_ID="+customerID, "SELECT");
        try {
            resultSet.first();
            i = resultSet.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }*/
}
