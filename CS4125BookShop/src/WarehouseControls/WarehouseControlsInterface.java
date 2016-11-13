package WarehouseControls;

public interface WarehouseControlsInterface {
    // Adds stock to the store (updates database values)
    public void addStoreStock(String name, int amount);
    
    // Adds stock to the warehouse (updates database values)
    public void addWarehouseStock(String name, int amount);
    
    // Processes an order (updates database values)
    public void processOrder(int orderID);
}
