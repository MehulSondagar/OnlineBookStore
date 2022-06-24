public class Cart {
    private Book itemAdded;
    private int qty;

    public Cart(Book e) {
        this.itemAdded = e;
        System.out.printf("Book Title: " + e.title + "%nPrice: " + e.price + "%n");
    }

    protected Book getitemAdded() {
        return itemAdded;
    }

    protected int getQuanity() {
        return this.qty;
    }

}
