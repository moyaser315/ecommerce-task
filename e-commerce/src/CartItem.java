public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) throws Exception {
        if (product == null) {
            throw new Exception("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new Exception("Quantity must be positive");
        }
        
        this.product = product;
        this.quantity = 0;
        
        increaseQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return product.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }


    public void increaseQuantity(int additionalQuantity) throws Exception {
        if (additionalQuantity <= 0) {
            throw new Exception("Additional quantity must be positive");
        }
        
        if (!product.reduceQuantity(additionalQuantity)) {
            throw new Exception("Not enough stock for product: " + product.getName());
        }
        
        this.quantity += additionalQuantity;
    }
    
    public void decreaseQuantity(int reduceBy) throws Exception {
        if (reduceBy <= 0) {
            throw new Exception("Reduction amount must be positive");
        }
        if (reduceBy > quantity) {
            throw new Exception("Cannot reduce more than current quantity");
        }
        

        product.increaseQuantity(reduceBy);
        this.quantity -= reduceBy;
    }
    
    public void returnToInventory() throws Exception {
        if (quantity > 0) {
            product.increaseQuantity(quantity);
            quantity = 0;
        }
    }

    @Override
    public String toString() {
        return "Product: " + this.product.getName() + '\n'
            +  "Price: $" + String.format("%.2f", this.getPrice()) + '\n'
            +  "Quantity: " + this.getQuantity() + '\n';
    }
}