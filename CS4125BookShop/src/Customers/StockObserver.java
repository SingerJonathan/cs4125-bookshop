package Customers;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

// Based on Observer Design Pattern example from www.tutorialspoint.com
public class StockObserver extends Observer {
    
    int customerID;
    int bookID;
    
    public StockObserver() {
        
    }
    
    public StockObserver(Subject subject, int customerID, int bookID) {
        this.subject = subject;
        this.customerID = customerID;
        this.bookID = bookID;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        DBHandler db = DBHandlerFactory.getDBHandler("Common");
        int state = subject.getState();
        String customerName = db.getCustomer(customerID).getName();
        String bookName = db.getBook(bookID).getName();
        String email = "Email sent to customer "+customerName+": "+bookName+" is in stock. Order quick while stock lasts!";
        if(state == bookID)
            JOptionPane.showMessageDialog(null, email, "All Observers Notified", JOptionPane.INFORMATION_MESSAGE);
    }
}
