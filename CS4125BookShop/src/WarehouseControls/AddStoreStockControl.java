package WarehouseControls;

import Customers.Subject;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class AddStoreStockControl extends WarehouseControls {
    
    @Override
    public void addStoreStock(String name, int amount) {
        DBHandler db = DBHandlerFactory.getDBHandler("Warehouse");
        int id = db.getBookID(name);
        int stock = db.getStoreStockAmount(id);
        db.updateStoreStock(id, stock+amount);
        JOptionPane.showMessageDialog(null, "The store stock for "+name+" has been updated by "+amount+".\nThere is now "+(stock+amount)+" in stock.", "Store Stock Updated", JOptionPane.INFORMATION_MESSAGE);
        if(amount > 0) {
            db = DBHandlerFactory.getDBHandler("Staff");
            Subject subject = db.getStockObserverSubject();
            subject.setState(id);
            db.deleteStockObservers(id);
        }
    }
    
}
