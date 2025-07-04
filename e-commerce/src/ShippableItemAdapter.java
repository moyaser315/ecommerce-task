public class ShippableItemAdapter implements ShippableItem {

    private Product product;
    private int quantity;

    public ShippableItemAdapter(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!product.isShippable()) {
            throw new IllegalArgumentException("Product must be shippable");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getWeight() throws Exception {
        return product.getWeight() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}