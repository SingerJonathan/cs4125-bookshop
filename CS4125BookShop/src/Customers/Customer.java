package Customers;

public class Customer implements CustomerInterface {
    private String name = "";
    private String email;
    private int memship;
    private int id;
    
    public Customer() {
        
    }
    
    public Customer(String name, String email, int memship) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.memship = memship;
    }
    
    public Customer(int id, String name, String email, int memship) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.memship = memship;
    }
    
    @Override
    public int getID() {
        return this.id;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getEmail() {
        return this.email;
    }
    
    @Override
    public int getMemship() {
        return this.memship;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public void setMemship(int memship) {
        this.memship = memship;
    }
}