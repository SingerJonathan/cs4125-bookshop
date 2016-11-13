package Orders;

public class Order implements OrderInterface {
    private int orderID;
    private int customerID;
    private int bookID;
    private double price;
    private String date;
    private int processed;
    private int cancelled;
    private int returned;
    private String returnReason;

    public Order() {
        
    }
    
    public Order(int customerID, int bookID, double price, String date)
    {
        this.orderID = 0;
        this.customerID = customerID;
        this.bookID = bookID;
        this.price = price;
        this.date = date;
        this.processed = 0;
        this.cancelled = 0;
        this.returned = 0;
        this.returnReason = "";
    }

    public Order(int orderID, int customerID, int bookID, double price, String date, int processed, int cancelled, int returned, String returnReason)
    {
        this.orderID = orderID;
        this.customerID = customerID;
        this.bookID = bookID;
        this.price = price;//calulateDiscount(bookID,customerID);
        this.date = date;
        this.processed = processed;
        this.cancelled = cancelled;
        this.returned = returned;
        this.returnReason = returnReason;
    }

    @Override
    public int getID()
    {
        return this.orderID;
    }

    @Override
    public int getCustomerID()
    {
        return this.customerID;
    }

    @Override
    public int getBookID()
    {
        return this.bookID;
    }

    @Override
    public double getPrice()
    {
        return this.price;
    }

    @Override
    public String getDate()
    {
        return this.date;
    }

    @Override
    public int getProcessed()
    {
        return this.processed;
    }

    @Override
    public int getCancelled()
    {
        return this.cancelled;
    }

    @Override
    public int getReturned()
    {
        return this.returned;
    }

    @Override
    public String getReturnReason()
    {
        return this.returnReason;
    }
}