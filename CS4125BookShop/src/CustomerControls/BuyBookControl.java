package CustomerControls;

import Customers.Customer;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Orders.Book;
import Orders.DetermineBook;
import Orders.Order;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class BuyBookControl extends CustomerControls {
    
    @Override
    public void buyBook(String bookName, String userName, int hardback, int signed) {
        DBHandler db = DBHandlerFactory.getDBHandler("Customer");
        Customer customer = db.getCustomer(userName);
        int membership = customer.getMemship();
        Book book = DetermineBook.getDecoratedBook(bookName, membership, hardback, signed);
        int bookID = db.getBookID(bookName);
        int customerID = customer.getID();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Order order = new Order(customer.getID(), db.getBookID(bookName), book.getPrice(), dateFormat.format(date));
        int stock = db.getStoreStockAmount(bookID);
        
        if(stock > 0) {
            db.updateStoreStock(bookID, stock-1);
            db.insertOrder(order);
            JOptionPane.showMessageDialog(null, "Your order for "+bookName+" has been placed.", "Order Placed", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            if (JOptionPane.showConfirmDialog(null, "Unforunately this book is not in stock.\n"
                    + "Would you like to be notified once it becomes available?", "Purchase Error", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                db = DBHandlerFactory.getDBHandler("Staff");
                db.insertStockObserver(bookID, customerID);
            }
        }   
    }
}
