package DBInterface;

import Orders.Order;

public class CustomerDBHandler extends DBHandlerCommon {
    
    // Adds a new row to ORDERS
    public void insertOrder(Order order) {
        doStatement("insert into CS4125BOOKSHOP.ORDERS values (default, "+order.getCustomerID()+", "+order.getBookID()+", "
                +order.getPrice()+", '"+order.getDate()+"', default, default, default, default)", "INSERT");
    }
    
    // Updates the CANCELLED value of a row in ORDERS (identified by id)
    public void cancelOrder(int id) {
        int i = checkOrderExists(id);
        if (i == 1){
            doStatement("UPDATE ORDERS SET CANCELLED=1 WHERE ID="+id, "UPDATE");
        }
    }
    
    // Updates the RETURNED and RETURN_REASON values of a row in ORDERS (identified by id)
    public void returnOrder(int id, String returnReason) {
        int i = checkOrderExists(id);
        if (i == 1){
            doStatement("UPDATE ORDERS SET RETURNED=1 WHERE ID="+id, "UPDATE");
            doStatement("UPDATE ORDERS SET RETURN_REASON='"+returnReason+"' WHERE ID="+id, "UPDATE");
        }
    }
    
}
