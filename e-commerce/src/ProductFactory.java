import java.time.LocalDate;
import Models.*;

public class ProductFactory {

    public static BaseProduct createProduct(String name, double price, int quantity, Double weight, LocalDate date) throws Exception {
        if (weight != null && date != null) {
            return new ShippableExpirableProduct(name, price, quantity, weight, date);
        } else if (weight != null) {
            return new ShippableProduct(name, price, quantity, weight);
        } else if (date != null) {
            return new ExpirableProduct(name, price, quantity, date);
        } else {
            return new SimpleProduct(name, price, quantity);
        }
    }
}