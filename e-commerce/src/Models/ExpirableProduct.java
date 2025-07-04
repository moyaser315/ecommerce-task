package Models;
import java.time.LocalDate;
import Interfaces.Expirable;

public class ExpirableProduct extends BaseProduct implements Expirable {
    protected LocalDate expirationDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) throws Exception {
        super(name, price, quantity);
        if (expirationDate == null) {
            throw new Exception("Expiration date cannot be null");
        }
        this.expirationDate = expirationDate;
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