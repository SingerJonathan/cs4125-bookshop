package StaffControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Orders.Book;
import javax.swing.JOptionPane;

public class EditBookControl extends StaffControls {
    
    @Override
    public void editBook(int id, String name, String author, String genre, String publisher, double price) {
        Book book = new Book(name, author, genre, publisher, price);
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.updateBook(id, book);
        JOptionPane.showMessageDialog(null, "Book edited:\n" +
                                            "Name: "+name+"\n" +
                                            "Author: "+author+"\n" +
                                            "Genre: "+genre+"\n" +
                                            "Publisher: "+publisher+"\n" +
                                            "Price: "+price, "Book Edited", JOptionPane.INFORMATION_MESSAGE);
    }
}
