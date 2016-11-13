package StaffControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import javax.swing.JOptionPane;

public class DeleteBookControl extends StaffControls {
    
    @Override
    public void deleteBook(String name) {
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.deleteBook(name);
        JOptionPane.showMessageDialog(null, "Book "+name+" deleted", "Book Deleted", JOptionPane.INFORMATION_MESSAGE);
    }
}
