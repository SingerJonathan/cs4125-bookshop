package Orders;

public class Book implements BookInterface {
    private String name = "";
    private String author;
    private String genre;
    private String publisher;
    private double price;
    
    public Book() {
        
    }
    
    public Book(String name, String author, String genre, String publisher, double price) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.price = price;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getAuthor() {
        return this.author;
    }
    
    @Override
    public String getGenre() {
        return this.genre;
    }
    
    @Override
    public String getPublisher() {
        return this.publisher;
    }
    
    @Override
    public double getPrice() {
        return this.price;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
    
    @Override
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    @Override
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
