package StaffControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class DeleteStaffControl extends StaffControls {
    
    @Override
    public void deleteStaff(String name) {
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.deleteStaff(name);
        JOptionPane.showMessageDialog(null, "Staff "+name+" deleted", "Staff Deleted", JOptionPane.INFORMATION_MESSAGE);
    }
}
