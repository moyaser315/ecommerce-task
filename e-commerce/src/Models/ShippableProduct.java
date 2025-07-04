package Models;
import java.time.LocalDate;
import Interfaces.Shippable;

public class ShippableProduct extends BaseProduct implements Shippable {
    protected final double weight;
    protected LocalDate shippingDate;
    protected boolean shipped = false;

    public ShippableProduct(String name, double price, int quantity, double weight) throws Exception {
        super(name, price, quantity);
        if (weight <= 0) {
            throw new Exception("Weight must be positive");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShipped() {
        return shipped;
    }

    @Override
    public void setShippingDate(LocalDate date) throws Exception {
        this.shippingDate = date;
        this.shipped = true;
    }

    @Override
    public LocalDate getShippingDate() throws Exception {
        return shippingDate;
    }
}