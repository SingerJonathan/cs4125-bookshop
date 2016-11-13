package DBInterface;

public class DBHandlerFactory {
    public static DBHandler getDBHandler(String handlerType) {
        if(handlerType.matches("Staff"))
            return new StaffDBHandler();
        else if(handlerType.matches("Customer"))
            return new CustomerDBHandler();
        else if(handlerType.matches("Warehouse"))
            return new WarehouseDBHandler();
        else if(handlerType.matches("Common"))
            return new DBHandlerCommon();
        return null;
    }
}
