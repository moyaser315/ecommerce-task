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
            // Create new cart item (this will reserve the quantity)
            CartItem newItem = new CartItem(product, quantity);
            items.put(productName, newItem);
        } else {
            // Add to existing cart item
            existingItem.increaseQuantity(quantity);
        }
    }
    
    public void remove(String productName) throws Exception {
        CartItem item = items.get(productName);
        if (item == null) {
            throw new Exception("Product not found in cart: " + productName);
        }
        
        // Return items to inventory
        item.returnToInventory();
        items.remove(productName);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    // This method returns items to inventory - for abandoned carts
    public void clear() throws Exception {
        // Return all items to inventory before clearing
        for (CartItem item : items.values()) {
            item.returnToInventory();
        }
        items.clear();
    }
    
    // This method is for checkout - items are sold, not returned
    public void clearAfterCheckout() {
        items.clear();
    }
    
    public double getSubtotal() {
        return items.values().stream()
            .mapToDouble(CartItem::getPrice)
            .sum();
    }

    public Map<String, CartItem> getItems() {
        return items; // For internal use by CheckoutService
    }
    
    public Map<String, CartItem> getItemsCopy() {
        return new HashMap<>(items); // For external use
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