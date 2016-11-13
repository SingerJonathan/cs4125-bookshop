package Orders;

public interface OrderInterface {
    // Returns the order's ID
    public int getID();

    // Returns the ID of the customer who placed the order
    public int getCustomerID();

    // Returns the ID of the book that was ordered
    public int getBookID();

    // Returns the Price of the order
    public double getPrice();

    // Returns the date of the order
    public String getDate();

    // Returns 1 if the order has been processed yet, 0 otherwise
    public int getProcessed();

    // Returns 1 if the order has been cancelled, 0 otherwise
    public int getCancelled();

    // Returns 1 if the book that was ordered was return, 0 otherwise
    public int getReturned();

    // Returns the Return Reason of the order
    public String getReturnReason();
}
