package StaffControls;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;
import Orders.Book;
import javax.swing.JOptionPane;

public class AddBookControl extends StaffControls {
    
    @Override
    public void addBook(String name, String author, String genre, String publisher, double price) {
        Book book = new Book(name, author, genre, publisher, price);
        DBHandler db = DBHandlerFactory.getDBHandler("Staff");
        db.insertBook(book);
        JOptionPane.showMessageDialog(null, "Book added:\n" +
                                            "Name: "+name+"\n" +
                                            "Author: "+author+"\n" +
                                            "Genre: "+genre+"\n" +
                                            "Publisher: "+publisher+"\n" +
                                            "Price: "+price, "Book Added", JOptionPane.INFORMATION_MESSAGE);
    }
}
