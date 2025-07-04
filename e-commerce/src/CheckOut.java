import java.util.*;

import Models.Cart;
import Models.CartItem;
import Models.Customer;
import Models.BaseProduct;
import Interfaces.Expirable;
import Shipping.ShippableItem;
import Shipping.ShippableItemAdapter;

public class CheckOut {
    private static final double SHIPPING_RATE_PER_G = 0.005; // 1 KG of cheese = 5 pounds shipping
    private static final double MIN_SHIPPING_FEE = 35.0;
    
    public static boolean checkout(Customer customer, Cart cart) {
        System.out.println("\n*** Processing checkout for " + customer.getName() + " ***");
        
        try {
            if (cart.isEmpty()) {
                throw new Exception("Cart is empty. Cannot proceed with checkout.");
            }
            
            List<ShippableItem> shippableItems = new ArrayList<>();
            
            for (CartItem item : cart.getItems().values()) {
                BaseProduct product = item.getProduct();
                
                if (product.isExpirable() && product instanceof Expirable) {
                    if (((Expirable) product).isExpired()) {
                        throw new Exception(product.getName() + " is expired. Cannot proceed with checkout.");
                    }
                }
                
                if (product.isShippable()) {
                    shippableItems.add(new ShippableItemAdapter(product, item.getQuantity()));
                }
            }
            
            double subtotal = cart.getSubtotal();
            double shippingFee = calculateShippingFee(shippableItems);
            double totalAmount = subtotal + shippingFee;
            
            if (!customer.hasSufficientBalance(totalAmount)) {
                throw new Exception("Insufficient balance. Required: $" + 
                                  String.format("%.2f", totalAmount) + 
                                  ", Available: $" + String.format("%.2f", customer.getBalance()));
            }
            
            customer.deductBalance(totalAmount);
            
            if (!shippableItems.isEmpty()) {
                ship(shippableItems);
            }
            
            printReceipt(cart, subtotal, shippingFee, totalAmount, customer.getBalance());
            
            cart.getItems().clear();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }
    
    private static double calculateShippingFee(List<ShippableItem> items) throws Exception {
        if (items.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = 0;
        for (ShippableItem item : items) {
            totalWeight += item.getWeight();
        }
        
        return Math.max(totalWeight * SHIPPING_RATE_PER_G, MIN_SHIPPING_FEE);
    }
    
    private static void ship(List<ShippableItem> items) throws Exception {
        System.out.println("\n** Shipment notice **");
        double totalWeight = 0;
        
        for (ShippableItem item : items) {
            if (item instanceof ShippableItemAdapter) {
                ShippableItemAdapter adapter = (ShippableItemAdapter) item;
                double itemWeight = item.getWeight();
                
                String weightStr;
                if (itemWeight < 1) {
                    weightStr = String.format("%.0fg", itemWeight * 1000);
                } else {
                    weightStr = String.format("%.1fg", itemWeight);
                }
                
                System.out.printf("%dx %s %s%n", 
                    adapter.getQuantity(), 
                    item.getName(), 
                    weightStr);
                    
                totalWeight += itemWeight;
            }
        }
        
        System.out.printf("Total package weight: %.1fg%n", totalWeight);
    }
    
    private static void printReceipt(Cart cart, double subtotal, double shippingFee, 
                                   double totalAmount, double remainingBalance) {
        System.out.println("\n** Checkout receipt **");
        
        for (CartItem item : cart.getItems().values()) {
            System.out.printf("%dx %s $%.2f%n", 
                item.getQuantity(), 
                item.getProduct().getName(), 
                item.getPrice());
        }
        
        System.out.println("----------------------");
        System.out.printf("Subtotal: $%.2f%n", subtotal);
        System.out.printf("Shipping: $%.2f%n", shippingFee);
        System.out.printf("Amount: $%.2f%n", totalAmount);
        System.out.printf("\nCustomer balance after payment: $%.2f%n", remainingBalance);
    }
}