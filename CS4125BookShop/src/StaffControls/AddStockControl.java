package StaffControls;

import Customers.Subject;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class AddStockControl extends StaffControls {
    
    @Override
    public void addStock(String name, int amount) {
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        int id = db.getBookID(name);
        int stock = db.getStoreStockAmount(id);
        db.updateStoreStock(id, stock+amount);
        JOptionPane.showMessageDialog(null, "The stock for "+name+" has been updated by "+amount+".\nThere is now "+(stock+amount)+" in stock.", "Stock Updated", JOptionPane.INFORMATION_MESSAGE);
        if(amount > 0) {
            Subject subject = db.getStockObserverSubject();
            subject.setState(id);
            db.deleteStockObservers(id);
        }
    }
}
