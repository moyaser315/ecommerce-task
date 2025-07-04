import java.time.LocalDate;

import Models.Product;

public class ProductFactory {

    public static Product createProduct(String name, double price,int quantity, Double weight, LocalDate date)throws Exception{
        Product product = new Product(name, price, quantity);
        if(weight != null){
            product.setShippingInfo(weight);
        }
        if (date != null) {
            product.setExpirationInfo(date);
        }
        return product;
    }
}
