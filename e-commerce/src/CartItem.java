public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) throws Exception {
        if (product == null) {
            throw new Exception("Product cannot be null");
        }
        this.product = product;
        setQuantity(quantity);
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

    public void setQuantity(int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Quantity must be positive");
        }
        if (!product.isAvailable(quantity)) {
            throw new Exception("Not enough stock for product: " + product.getName());
        }
        if (!product.reduceQuantity(quantity)) {
            throw new Exception("Not enough stock for product: " + product.getName());
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product : " + this.product.getName() + '\n'
            +  "price : " + this.getPrice() + '\n'
            +  "quantity : " + this.getQuantity() + '\n';
    }

}
