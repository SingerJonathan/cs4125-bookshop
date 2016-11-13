package StaffControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Staff.Staff;
import javax.swing.JOptionPane;

public class AddStaffControl extends StaffControls {
    
    @Override
    public void addStaff(String name, String address, String email, int phoneNumber, int warehouse) {
        Staff staff = new Staff(name, address, email, phoneNumber, warehouse);
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.insertStaff(staff);
        String warehouseString = "";
        switch (warehouse) {
            case 1:  warehouseString = "yes";      break;
            default: warehouseString = "no";        break;
        }
        JOptionPane.showMessageDialog(null, "Staff added:\n" +
                                            "Name: "+name+"\n" +
                                            "Address: "+address+"\n" +
                                            "Email: "+email+"\n" +
                                            "Phone: "+phoneNumber+"\n" +
                                            "Warehouse Staff: "+warehouseString, "Staff Added", JOptionPane.INFORMATION_MESSAGE);
    }
}
