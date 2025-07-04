import java.time.LocalDate;

import Models.Cart;
import Models.Customer;
import Models.BaseProduct;
import Interfaces.Shippable;

public class Main {
    private static BaseProduct[] products;
    
    public static void main(String[] args) throws Exception {
        products = createTestProducts();
        
        System.out.println("=== Available Products ===");
        displayProducts();
        
        testSuccessfulCheckout();
        testInsufficientStock();
        testAbandonedCart();
        testMultipleUsers();
        testExpiredProduct();
    }
    
    private static void testSuccessfulCheckout() throws Exception {
        System.out.println("\n=== Test Case 1: Successful Checkout ===");
        System.out.println("Remaining cheese: " + products[0].getQuantity());
        
        Customer mo = new Customer("mo", 1000);
        Cart cart = new Cart();
        cart.addItem(products[0], 2);
        
        System.out.println("Remaining cheese: " + products[0].getQuantity());
        CheckOut.checkout(mo, cart);
    }
    
    private static void testInsufficientStock() throws Exception {
        System.out.println("\n=== Test Case 2: Insufficient Stock ===");
        Customer momo = new Customer("momo", 2000.0);
        Cart cart = new Cart();
        
        try {
            cart.addItem(products[0], 10);
        } catch (Exception e) {
            System.out.println("Error adding to cart: " + e.getMessage());
        }
    }
    
    private static void testAbandonedCart() throws Exception {
        System.out.println("\n=== Test Case 3: Abandoned Cart ===");
        Cart cart = new Cart();
        cart.addItem(products[1], 1);
        cart.addItem(products[7], 5);
        System.out.println("TV before abandonment: " + products[1].getQuantity());
        
        cart.clear();
        System.out.println("Cart abandoned");
        System.out.println("TV after abandonment: " + products[1].getQuantity());
    }
    
    private static void testMultipleUsers() throws Exception {
        System.out.println("\n=== Test Case 4: Multiple Users ===");
        Customer mohamed = new Customer("mohamed", 1000.0);
        Customer mo = new Customer("mo", 1000.0);
        
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        System.out.println("TV Remaining before mohamed: " + products[1].getQuantity());
        cart1.addItem(products[1], 4);
        System.out.println("TV Remaining After Mohamed added: " + products[1].getQuantity());
        
        try {
            cart2.addItem(products[1], 2);
        } catch (Exception e) {
            System.out.println("Mo : " + e.getMessage());
        }
        
        cart2.addItem(products[1], 1);
        System.out.println("TV Remaining: " + products[1].getQuantity());
        
        CheckOut.checkout(mohamed, cart1);
        CheckOut.checkout(mo, cart2);
    }
    
    private static void testExpiredProduct() throws Exception {
        System.out.println("\n=== Test Case 5: Expired Product ===");
        BaseProduct expiredMilk = ProductFactory.createProduct(
            "Old Milk", 5.0, 10, 1.0, LocalDate.now().minusDays(5)
        );
        
        Customer momo = new Customer("momo", 100.0);
        Cart cart = new Cart();
        try {
            cart.addItem(expiredMilk, 2);
        } catch (Exception e) {
            System.out.println("Error adding expired product: " + e.getMessage());
        }

        CheckOut.checkout(momo, cart);
        
        cart.clear();
        System.out.println("Milk inventory after failed checkout: " + expiredMilk.getQuantity());
    }
    
    private static BaseProduct[] createTestProducts() throws Exception {
        BaseProduct[] productsTest = new BaseProduct[8];
        productsTest[0] = ProductFactory.createProduct("cheese", 280 ,5 ,1000.0, LocalDate.now().plusYears(2));
        productsTest[1] = ProductFactory.createProduct("TV", 280 ,5 ,800.0, null);
        productsTest[2] = ProductFactory.createProduct("Mobile", 280 ,5 ,180.0, null);
        // productsTest[4] = ProductFactory.createProduct("", 280 ,5 ,null, LocalDate.now().plusYears(2));
        // productsTest[5] = ProductFactory.createProduct("Scratch card", 0 ,5 ,null, LocalDate.now().plusYears(2));
        // productsTest[6] = ProductFactory.createProduct("Scratch card", 280 ,-1 ,null, LocalDate.now().plusYears(2));
        productsTest[3] = ProductFactory.createProduct("Scratch card", 280 ,5 ,null, LocalDate.now().plusYears(2));
        productsTest[7] = ProductFactory.createProduct("Scratch card", 280 ,100 ,null, null);

        if (productsTest[1] instanceof Shippable) {
            ((Shippable) productsTest[1]).setShippingDate(LocalDate.now().plusDays(5));
        }
        
        return productsTest;
    }
    
    private static void displayProducts() {
        for (BaseProduct product : products) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }
}