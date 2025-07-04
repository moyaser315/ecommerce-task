package Models;

import Interfaces.Expirable;
import Interfaces.Shippable;

public abstract class BaseProduct {
    protected final String name;
    protected final double price;
    protected int quantity;

    public BaseProduct(String name, double price, int quantity) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Please enter the product name");
        }
        if (price < 0) {
            throw new Exception("Price must be larger than 0");
        }
        if (quantity < 0) {
            throw new Exception("stock can't be less than 0");
        }

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

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

    public boolean isShippable() {
        return this instanceof Shippable;
    }

    public boolean isExpirable() {
        return this instanceof Expirable;
    }

    @Override
    public String toString() {
        String res = "name: " + name + '\n' +
                "price: " + price + '\n' +
                "quantity: " + quantity + '\n' +
                "shippable: " + isShippable() + '\n' +
                "expirable: " + isExpirable() + '\n';

        if (isShippable() && this instanceof ShippableProduct) {
            ShippableProduct sp = (ShippableProduct) this;
            if (sp.shippingDate != null) {
                try {
                    res += "shipping Date: " + sp.getShippingDate() + '\n';
                } catch (Exception e) {
                    // Handle exception
                }
            }
        }

        return res;
    }
}