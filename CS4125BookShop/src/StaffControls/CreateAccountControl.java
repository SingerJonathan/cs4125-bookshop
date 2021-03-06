package StaffControls;

import DBInterface.DBHandler;
import Customers.Customer;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class CreateAccountControl extends StaffControls {
    
    @Override
    public void createAccount(String name, String email, int memship) {
        Customer customer = new Customer(name, email, memship);
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.insertCustomer(customer);
        String memString = "";
        switch (memship) {
            case 1:  memString = "bronze";      break;
            case 2:  memString = "silver";      break;
            case 3:  memString = "gold";        break;
            default: memString = "none";        break;
        }
        JOptionPane.showMessageDialog(null, "Customer account created:\n" +
                                            "Name: "+name+"\n" +
                                            "Email: "+email+"\n" +
                                            "Membership: "+memString, "Customer Account Created", JOptionPane.INFORMATION_MESSAGE);
    }
}
