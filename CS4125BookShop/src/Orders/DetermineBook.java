package Orders;

import DBInterface.DBHandler;
import DBInterface.DBHandlerFactory;

public class DetermineBook {
    public static Book getDecoratedBook(String bookName, int membership, int hardback, int signed) {
        DBHandler db = DBHandlerFactory.getDBHandler("Customer");
        
        // No membership, not hardback, not signed
        Book book = db.getBook(bookName);
        
        // Bronze
        if (membership == 1 && hardback == 0 && signed == 0)
            book = new BookDecorator(
                new withBronzeMembership (
                db.getBook(bookName) ));
        
        // Silver
        else if (membership == 2 && hardback == 0 && signed == 0)
            book = new BookDecorator(
                new withSilverMembership (
                db.getBook(bookName) ));
        
        // Gold
        else if (membership == 3 && hardback == 0 && signed == 0)
            book = new BookDecorator(
                new withGoldMembership (
                db.getBook(bookName) ));
        
        // Bronze, Hardback, Signed
        else if (membership == 1 && hardback == 1 && signed == 1)
            book = new BookDecorator(
                new withBronzeMembership (
                new withHardback (
                new withSignature (
                db.getBook(bookName) ))));
        
        // Silver, Hardback, Signed
        else if (membership == 2 && hardback == 1 && signed == 1)
            book = new BookDecorator(
                new withSilverMembership (
                new withHardback (
                new withSignature (
                db.getBook(bookName) ))));
        
        // Gold, Hardback, Signed
        else if (membership == 3 && hardback == 1 && signed == 1)
            book = new BookDecorator(
                new withGoldMembership (
                new withHardback (
                new withSignature (
                db.getBook(bookName) ))));
        
        // Hardback, Signed
        else if (membership == 0 && hardback == 1 && signed == 1)
            book = new BookDecorator(
                new withHardback (
                new withSignature (
                db.getBook(bookName) )));
        
        // Bronze, Signed
        else if (membership == 1 && hardback == 0 && signed == 1)
            book = new BookDecorator(
                new withBronzeMembership (
                new withSignature (
                db.getBook(bookName) )));
        
        // Silver, Signed
        else if (membership == 2 && hardback == 0 && signed == 1)
            book = new BookDecorator(
                new withSilverMembership (
                new withSignature (
                db.getBook(bookName) )));
        
        // Gold, Signed
        else if (membership == 3 && hardback == 0 && signed == 1)
            book = new BookDecorator(
                new withGoldMembership (
                new withSignature (
                db.getBook(bookName) )));
        
        // Signed
        else if (membership == 0 && hardback == 0 && signed == 1)
            book = new BookDecorator(
                new withSignature (
                db.getBook(bookName) ));
        
        // Bronze, Hardback
        else if (membership == 1 && hardback == 1 && signed == 0)
            book = new BookDecorator(
                new withBronzeMembership (
                new withHardback (
                db.getBook(bookName) )));
        
        // Silver, Hardback
        else if (membership == 2 && hardback == 1 && signed == 0)
            book = new BookDecorator(
                new withSilverMembership (
                new withHardback (
                db.getBook(bookName) )));
        
        // Gold, Hardback
        else if (membership == 3 && hardback == 1 && signed == 0)
            book = new BookDecorator(
                new withGoldMembership (
                new withHardback (
                db.getBook(bookName) )));
        
        // Hardback
        else if (membership == 0 && hardback == 1 && signed == 0)
            book = new BookDecorator(
                new withHardback (
                db.getBook(bookName) ));
        
        return book;
    }
}
