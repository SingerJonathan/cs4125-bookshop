package CustomerControls;

import Customers.Subject;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class ReturnBookControl extends CustomerControls {
    
    @Override
    public void returnBook(String bookName, String customerName, String returnReason) {
        DBHandler db = DBHandlerFactory.getDBHandler("Customer");
        int bookID = db.getBookID(bookName);
        int orderID = db.getOrder(bookName, customerName, 1, 0, 0).getID();
        int stock = db.getStoreStockAmount(bookID);
        db.updateStoreStock(bookID, stock+1);
        db.returnOrder(orderID, returnReason);
        JOptionPane.showMessageDialog(null, "Your Book, "+bookName+", has been processed as returned. Please return the book to the store as soon as possible.", "Book returned", JOptionPane.INFORMATION_MESSAGE);
        if(stock+1 > 0) {
            db = DBHandlerFactory.getDBHandler("Staff");
            Subject subject = db.getStockObserverSubject();
            subject.setState(bookID);
            db.deleteStockObservers(bookID);
        }
    }
}
