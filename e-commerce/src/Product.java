import java.time.LocalDate;

public class Product {
    private final String name;
    private final double price;
    private int quantity;
    
    private ShippingInfo shippingInfo;
    private ExpirationInfo expirationInfo;
    
    public Product(String name, double price, int quantity) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Please enter the product name");
        }
        if (price < 0) {
            throw new Exception("Price must be larger than 0");
        }
        if (quantity < 0) {
            throw new Exception("sotck can't be less than 0");
        }
        
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    //************************general***********************************/
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public boolean reduceQuantity(int amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be positive");
        }
        if (amount <= quantity) {
            quantity -= amount;
            return true;
        }
        return false;
    }
    
    public void increaseQuantity(int amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be positive");
        }
        quantity += amount;
    }
    
    public boolean isAvailable(int amount) {
        return amount > 0 && quantity >= amount;
    }
    
    /***************************shipping****************************************/
    public void setShippingInfo(double weight) throws Exception {
        if (weight <= 0) {
            throw new Exception("Weight must be positive");
        }
        this.shippingInfo = new ShippingInfo(weight);
    }
    
    public boolean isShippable() {
        return shippingInfo != null;
    }
    
    public double getWeight() throws Exception {
        if (!isShippable()) {
            throw new Exception("Product is not shippable");
        }
        return shippingInfo.getWeight();
    }
    
    public boolean isShipped() {
        return isShippable() && shippingInfo.isShipped();
    }
    
    public void setShippingDate(LocalDate date) throws Exception {
        if (!isShippable()) {
            throw new Exception("Product is not shippable");
        }
        shippingInfo.setShippingDate(date);
    }
    
    /***************************Expiration****************************************/
    public void setExpirationInfo(LocalDate expirationDate) throws Exception {
        if (expirationDate == null) {
            throw new Exception("Expiration date cannot be null");
        }
        this.expirationInfo = new ExpirationInfo(expirationDate);
    }
    
    public boolean isExpirable() {
        return expirationInfo != null;
    }
    
    public boolean isExpired() {
        return isExpirable() && expirationInfo.isExpired();
    }
    
    public LocalDate getExpirationDate() throws Exception {
        if (!isExpirable()) {
            throw new Exception("Product is not expirable");
        }
        return expirationInfo.getExpirationDate();
    }
    
    public void setExpirationDate(LocalDate date) throws Exception {
        if (!isExpirable()) {
            throw new Exception("Product is not expirable");
        }
        expirationInfo.setExpirationDate(date);
    }
    
    @Override
    public String toString() {
        return "name: " + name + '\n' +
               "price: " + price + '\n' +
               "quantity: " + quantity + '\n' +
               "shippable: " + isShippable() + '\n' +
               "expirable: " + isExpirable() + '\n';
    }
    
    
    private static class ShippingInfo {
        private final double weight;
        private LocalDate shippingDate;
        private boolean shipped = false;
        
        ShippingInfo(double weight) {
            this.weight = weight;
        }
        
        double getWeight() {
            return weight;
        }
        
        boolean isShipped() {
            return shipped;
        }
        
        void setShippingDate(LocalDate date) {
            this.shippingDate = date;
            this.shipped = true;
        }
        
        LocalDate getShippingDate() {
            return shippingDate;
        }
    }
    
    private static class ExpirationInfo {
        private LocalDate expirationDate;
        
        ExpirationInfo(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
        }
        
        boolean isExpired() {
            return LocalDate.now().isAfter(expirationDate);
        }
        
        LocalDate getExpirationDate() {
            return expirationDate;
        }
        
        void setExpirationDate(LocalDate date) throws Exception {
            if (date == null) {
                throw new Exception("Expiration date cannot be null");
            }
            this.expirationDate = date;
        }
    }
}