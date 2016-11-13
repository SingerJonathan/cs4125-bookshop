package WarehouseControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class AddWarehouseStockControl extends WarehouseControls {
    
    @Override
    public void addWarehouseStock(String name, int amount) {
        DBHandler db = DBHandlerFactory.getDBHandler("Warehouse");
        int id = db.getBookID(name);
        int stock = db.getWarehouseStockAmount(id);
        db.updateWarehouseStock(id, stock+amount);
        JOptionPane.showMessageDialog(null, "The warehouse stock for "+name+" has been updated by "+amount+".\nThere is now "+(stock+amount)+" in stock.", "Warehouse Stock Updated", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
