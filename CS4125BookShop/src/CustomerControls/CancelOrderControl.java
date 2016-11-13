package CustomerControls;

import Customers.Subject;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class CancelOrderControl extends CustomerControls {
    
    @Override
    public void cancelOrder(String bookName, String customerName) {
        DBHandler db = DBHandlerFactory.getDBHandler("Customer");
        int bookID = db.getBookID(bookName);
        int orderID = db.getOrder(bookName, customerName, 0, 0, 0).getID();
        int stock = db.getStoreStockAmount(bookID);
        db.updateStoreStock(bookID, stock+1);
        db.cancelOrder(orderID);
        JOptionPane.showMessageDialog(null, "Your order for "+bookName+" has been cancelled.", "Order Cancelled", JOptionPane.INFORMATION_MESSAGE);
        if(stock+1 > 0) {
            db = DBHandlerFactory.getDBHandler("Staff");
            Subject subject = db.getStockObserverSubject();
            subject.setState(bookID);
            db.deleteStockObservers(bookID);
        }
    }
    
}
