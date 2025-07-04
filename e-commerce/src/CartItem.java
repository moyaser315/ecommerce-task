public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product.getName();
    }

    public double getPrice() {
        return product.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product : " + this.product.getName() + '\n'
            +  "price : " + this.getPrice() + '\n'
            +  "quantity : " + this.getQuantity() + '\n';
    }

}
