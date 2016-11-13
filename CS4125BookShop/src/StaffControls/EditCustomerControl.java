package StaffControls;

import Customers.Customer;
import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class EditCustomerControl extends StaffControls {
    
    @Override
    public void editCustomer(int id, String name, String email, int membership) {
        Customer customer = new Customer(name, email, membership);
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.updateCustomer(id, customer);
        String memString = "";
        switch (membership) {
            case 1:  memString = "bronze";      break;
            case 2:  memString = "silver";      break;
            case 3:  memString = "gold";        break;
            default: memString = "none";        break;
        }
        JOptionPane.showMessageDialog(null, "Customer account edited:\n" +
                                            "Name: "+name+"\n" +
                                            "Email: "+email+"\n" +
                                            "Membership: "+memString, "Customer Account Edited", JOptionPane.INFORMATION_MESSAGE);
    }
}
