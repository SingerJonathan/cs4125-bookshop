package StaffControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class RemoveStockControl extends StaffControls {
    
    @Override
    public void removeStock(String name, int amount) {
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        int id = db.getBookID(name);
        int stock = db.getStoreStockAmount(id);
        db.updateStoreStock(id, stock-amount);
        JOptionPane.showMessageDialog(null, "The stock for "+name+" has been reduced by "+amount+".\nThere is now "+(stock-amount)+" in stock.", "Stock Updated", JOptionPane.INFORMATION_MESSAGE);
    }
}
