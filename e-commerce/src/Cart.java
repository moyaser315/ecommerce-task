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
        if (!product.isAvailable(quantity)) {
            throw new Exception("not enough stock for product: " + product.getName());
        }

        CartItem item = items.get(product.getName());
        if (item == null) {
            item = new CartItem(product, quantity);
            items.put(product.getName(), item);
        } else {
            if (!product.reduceQuantity(quantity)) {
                throw new Exception("not enough stock for product: " + product.getName());
            }
            item.setQuantity(item.getQuantity() + quantity);
        }
    }
    public void remove(String productName) throws Exception {
        if (!items.containsKey(productName)) {
            throw new Exception("Product not found in cart: " + productName);
        }
        CartItem item = items.get(productName);
        item.getProduct().increaseQuantity(item.getQuantity());
        items.remove(productName);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    
    public void clear() {
        items.clear();
    }
    
    public double getSubtotal() {
        double subtotal = 0.0;
        if (items.isEmpty()) {
            return 0;
        }
        for (CartItem item : items.values()) {
            subtotal += item.getPrice();
        }
        return subtotal;
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
