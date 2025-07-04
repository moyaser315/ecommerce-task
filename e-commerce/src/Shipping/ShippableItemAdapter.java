package Shipping;
import Models.BaseProduct;
import Interfaces.Shippable;

public class ShippableItemAdapter implements ShippableItem {

    private BaseProduct product;
    private int quantity;

    public ShippableItemAdapter(BaseProduct product, int quantity) {
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
        if (product instanceof Shippable) {
            return ((Shippable) product).getWeight() * quantity;
        }
        throw new Exception("Product is not shippable");
    }

    public int getQuantity() {
        return quantity;
    }

    public BaseProduct getProduct() {
        return product;
    }
}