package Staff;

public interface StaffInterface {
    // Returns the staff's Name
    public String getName();
    
    // Returns the staff's Address
    public String getAddress();
    
    // Returns the staff's Email Address
    public String getEmail();
    
    // Returns the staff's Phone Number
    public int getPhoneNumber();
    
    // Returns 1 if the staff works in the warehouse, 0 if he/she works in the store
    public int getWarehouse();
    
    // Sets the staff's Name
    public void setName(String name);
    
    // Sets the staff's Address
    public void setAddress(String address);
    
    // Sets the staff's Email Address
    public void setEmail(String email);
    
    // Sets the staff's Phone Number
    public void setPhoneNumber(int phoneNumber);
    
    // Sets the staff's Warehouse value (1 if the staff works in the warehouse, 0 if he/she works in the store)
    public void setWarehouse(int warehouse);
}
