package Models;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> items = new HashMap<>();

    public void addItem(Product product, int quantity) throws Exception {
        if (product == null) {
            throw new Exception("Must choose a product");
        }
        if (quantity <= 0) {
            throw new Exception("Quantity must be positive");
        }

        String productName = product.getName();
        CartItem existingItem = items.get(productName);
        
        if (existingItem == null) {
 
            CartItem newItem = new CartItem(product, quantity);
            items.put(productName, newItem);
        } else {
         
            existingItem.increaseQuantity(quantity);
        }
    }
    
    public void remove(String productName) throws Exception {
        CartItem item = items.get(productName);
        if (item == null) {
            throw new Exception("Product not found in cart: " + productName);
        }
        

        item.returnToInventory();
        items.remove(productName);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    

    public void clear() throws Exception {
      
        for (CartItem item : items.values()) {
            item.returnToInventory();
        }
        items.clear();
    }
    
   
    public void clearAfterCheckout() {
        items.clear();
    }
    
    public double getSubtotal() {
        return items.values().stream()
            .mapToDouble(CartItem::getPrice)
            .sum();
    }

    public Map<String, CartItem> getItems() {
        return items; 
    }
    
    public Map<String, CartItem> getItemsCopy() {
        return new HashMap<>(items); 
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart contents:\n");
        for (CartItem item : items.values()) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("Subtotal: $").append(String.format("%.2f", getSubtotal()));
        return sb.toString();
    }
}