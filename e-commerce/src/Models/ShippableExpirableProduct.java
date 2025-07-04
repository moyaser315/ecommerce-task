package Models;
import java.time.LocalDate;
import Interfaces.Expirable;
import Interfaces.Shippable;

public class ShippableExpirableProduct extends BaseProduct implements Shippable, Expirable {
    protected final double weight;
    protected LocalDate shippingDate;
    protected boolean shipped = false;
    protected LocalDate expirationDate;

    public ShippableExpirableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate) throws Exception {
        super(name, price, quantity);
        if (weight <= 0) {
            throw new Exception("Weight must be positive");
        }
        if (expirationDate == null) {
            throw new Exception("Expiration date cannot be null");
        }
        this.weight = weight;
        this.expirationDate = expirationDate;
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

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(LocalDate date) throws Exception {
        if (date == null) {
            throw new Exception("Expiration date cannot be null");
        }
        this.expirationDate = date;
    }
}