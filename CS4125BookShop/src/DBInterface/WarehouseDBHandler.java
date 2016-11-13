package DBInterface;

public class WarehouseDBHandler extends DBHandlerCommon {
    
    // Updates the values of a row in WAREHOUSESTOCK (book identified by id)
    public void updateWarehouseStock(int id, int amount) {
        int i = checkBookExists(id);
        if (i == 1){
            doStatement("UPDATE WAREHOUSESTOCK SET AMOUNT="+amount+" WHERE ID="+id, "UPDATE");
        }
    }
    
    // Updates the PROCESSED value of a row in ORDERS (identified by id)
    public void processOrder(int id) {
        int i = checkOrderExists(id);
        if (i == 1){
            doStatement("UPDATE ORDERS SET PROCESSED=1 WHERE ID="+id, "UPDATE");
        }
    }
}