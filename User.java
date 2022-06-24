import java.util.ArrayList;

public class User {
    private String username;
    ArrayList<Cart> cart = new ArrayList();

    public User(String username) {
        this.username = username;
    }

    protected String getUsername() {
        return this.username;
    }

}
