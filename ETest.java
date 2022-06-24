import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class ETest {
    static String username;
    static String password;

    public static void main(String[] args)throws IOException {

        Scanner keyboardInput = new Scanner(System.in);
        File bookFile = new File("Books.txt");
        File customerFile = new File("Customers.txt");

        ArrayList<Book> bookList = getCatalog(bookFile);

        System.out.println("Are you a new user? (Yes/ No)");
        String userBool = keyboardInput.next();

        if (userBool.equals("Yes")) {
            System.out.printf("Set username: ");
            username = keyboardInput.next();
            System.out.printf("Set Password: ");
            password = keyboardInput.next();
        } else {
            System.out.printf("Username: ");
            username = keyboardInput.next();
            System.out.printf("Password: ");
            password = keyboardInput.next();
        }
        User user = new User(username);
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Welcome Admin!");
        } else {
            availableBooks(bookList);
            CartSystem(user, bookList);
            System.out.println(getTotal(user.cart, user.getUsername()));
        }
        System.out.println("Ready to checkout? (Yes/ No)");
        String checkoutCheck = keyboardInput.next();
        if (checkoutCheck.equals("Yes")) {
            boolean paymentCheck = true;
            System.out.println("Enter you address(3753-Rose-Street):");
            String streetAddress = keyboardInput.next();
            System.out.println("Enter you address(Regina):");
            String city = keyboardInput.next();
            System.out.println("Enter you address(SK):");
            String province = keyboardInput.next();
            System.out.println("Enter you address(S4P3Y2):");
            String zipCode = keyboardInput.next();
            while (paymentCheck) {
                System.out.println("Enter Card Number(16 numbers long(Ex. 5189922318277396)):");
                String cardNumber = keyboardInput.next();
                System.out.println("Enter Card Expiry Month(2 numbers long(Ex. January -> 01)):");
                String cardExpMon = keyboardInput.next();
                System.out.println("Enter Card Expiry Year(4 numbers long(Ex. 2021)):");
                String cardExpYear = keyboardInput.next();
                System.out.println("Enet Card CVV(3 numbers long(Ex. 822))");
                String cardCVV = keyboardInput.next();
                int cardNumLenght = cardNumber.length();
                int cardCVVLenght = cardCVV.length();
                if (cardNumLenght == 16 && Integer.parseInt(cardExpMon) <= 12 && Integer.parseInt(cardExpYear) >= 2021
                        && cardCVVLenght == 3) {
                    System.out.println("Payment Successfull! Thank you for placing your order.");
                    System.out.println(
                            "Your order will be shipped to: " + streetAddress + ", " + city + " " + province + ", "
                                    + zipCode + ". Your order will ship within 2 weeks.");

                    BufferedWriter fWriter = new BufferedWriter(new FileWriter("Customers.txt", true)); 
                    fWriter.write("Name: " + username + "\n");
                    fWriter.write("Shipping Address: " + "\n" + streetAddress + " ," + city + " ," + province + " ," + zipCode + "\n");
                    fWriter.write(getTotal(user.cart, user.getUsername())+ "\n");
                    fWriter.write("***************************************************" + "\n");
                    fWriter.close();

                    paymentCheck = false;
                } else {
                    System.out.println("Payment Unscuessfull!");
                    paymentCheck = true;
                }
            }

        }
    }

    public static void CartSystem(User user, ArrayList<Book> e) {
        boolean doneShopping = true;
        Scanner keyboardInput = new Scanner(System.in);
        Scanner done = new Scanner(System.in);
        while (doneShopping) {
            System.out.println("\nWhat would you like to buy? (Enter Book ID)");
            String bookID = keyboardInput.next();
            for (Book book : e) {
                if (book.getBookID().equalsIgnoreCase(bookID)) {
                    Cart item = new Cart(book);
                    user.cart.add(item);
                }
            }
            System.out.println("Would you like to buy more books? (Yes/ No)");
            String check = done.next();
            if (check.equals("Yes")) {
                doneShopping = true;
            } else if (check.equals("No")) {
                doneShopping = false;
            }
        }
    }

    private static String getTotal(ArrayList<Cart> cartArray, String customerName) {
        double subtotal = 0;
        for (Cart item : cartArray) {
            subtotal += (item.getitemAdded().price);
        }
        String text;
        text = "Subtotal: $" + String.format("%.02f", subtotal);
        return text;
    }

    public static ArrayList getCatalog(File fn) {
        if (fn.exists()) {
            try {
                Scanner fileScanner = new Scanner(fn);
                ArrayList<Book> bookArray = new ArrayList();  
                while (fileScanner.hasNext()) {
                    String[] textArray = fileScanner.nextLine().split(",");
                    Book book = new Book(textArray[0], textArray[1], textArray[2], Double.parseDouble(textArray[3]));
                    bookArray.add(book);
                }
                // return the ArrayList<Book>
                return bookArray;
            } catch (FileNotFoundException e) {
                System.out.println("Not Valid");
            }
            catch (Exception e) {
                System.out.println("Not Valid");
            }

        } else {
            System.out.println(fn.getName() + " does not exist");
        }
        return null;

    }

    protected static void availableBooks(ArrayList<Book> e) {
        System.out.println("Books Available:");
        for (int i = 0; i < e.size(); i++) {
            System.out.printf("  %s%n", e.get(i).bookInfo());
        }
    }
}