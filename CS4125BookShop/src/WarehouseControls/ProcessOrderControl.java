package WarehouseControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Orders.Order;
import javax.swing.JOptionPane;

public class ProcessOrderControl extends WarehouseControls {
    
    @Override
    public void processOrder(int orderID) {
        DBHandler db = DBHandlerFactory.getDBHandler("Warehouse");
        Order order = db.getOrder(orderID);
        int stock = db.getWarehouseStockAmount(order.getBookID());
        db.updateWarehouseStock(order.getBookID(), stock-1);
        db.processOrder(orderID);
        String customerName = db.getCustomer(order.getCustomerID()).getName();
        String bookName = db.getBook(order.getBookID()).getName();
        double orderPrice = order.getPrice();
        String orderDate = order.getDate();
        
        JOptionPane.showMessageDialog(null, "Customer: "+customerName+"\nBook: "+bookName+"\nPrice: "+orderPrice+"\nDate: "+orderDate,
                "Order "+orderID+" Details", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
