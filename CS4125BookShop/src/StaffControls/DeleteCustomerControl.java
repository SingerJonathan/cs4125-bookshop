package StaffControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class DeleteCustomerControl extends StaffControls {
    
    @Override
    public void deleteCustomer(String name) {
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.deleteCustomer(name);
        JOptionPane.showMessageDialog(null, "Customer "+name+" deleted", "Customer Deleted", JOptionPane.INFORMATION_MESSAGE);
    }
}
