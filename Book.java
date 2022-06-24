public class Book {
    private String BookID;
    String title;
    double price;
    String author;

    public Book(String BookID, String title, String author, double price) {
        this.BookID = BookID;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    protected String getBookID() {
        return this.BookID;
    }

    public String bookInfo() {
        String name = this.getBookID() + " - " + this.title + " - $" + this.price;
        return name;
    }
}