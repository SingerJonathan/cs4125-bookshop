package Orders;

public interface BookInterface {
    // Returns the book's Name
    public String getName();
    
    // Returns the book's Author
    public String getAuthor();
    
    // Returns the book's Genre
    public String getGenre();
    
    // Returns the book's Publisher
    public String getPublisher();
    
    // Returns the book's Price
    public double getPrice();
    
    // Sets the book's Name
    public void setName(String name);
    
    // Sets the book's Author
    public void setAuthor(String author);
    
    // Sets the book's Genre
    public void setGenre(String genre);
    
    // Sets the book's Publisher
    public void setPublisher(String publisher);
    
    // Sets the book's Price
    public void setPrice(double price);
}
