package Staff;

public class Staff implements StaffInterface {
    private String name = "";
    private String address;
    private String email;
    private int phoneNumber;
    private int warehouse;
    
    public Staff() {
        
    }
    
    public Staff(String name, String address, String email, int phoneNumber, int warehouse) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.warehouse = warehouse;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getAddress() {
        return this.address;
    }
    
    @Override
    public String getEmail() {
        return this.email;
    }
    
    @Override
    public int getPhoneNumber() {
        return this.phoneNumber;
    }
    
    @Override
    public int getWarehouse() {
        return this.warehouse;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void setWarehouse(int warehouse) {
        this.warehouse = warehouse;
    }
}
