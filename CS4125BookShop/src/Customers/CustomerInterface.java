package Customers;

public interface CustomerInterface {
    // Returns the customer's ID
    public int getID();
    
    // Returns the customer's Name
    public String getName();
    
    // Returns the customer's Email Address
    public String getEmail();
    
    // Returns the customer's membership (0 for none, 1 for bronze, 2 for silver, 3 for gold)
    public int getMemship();
    
    // Sets the customer's Name
    public void setName(String name);
    
    // Sets the customer's Email Address
    public void setEmail(String email);
    
    // Sets the customer's membership (0 for none, 1 for bronze, 2 for silver, 3 for gold)
    public void setMemship(int memship);
}