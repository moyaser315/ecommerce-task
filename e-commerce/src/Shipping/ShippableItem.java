package Shipping;
public interface ShippableItem {
    String getName();
    double getWeight() throws Exception;
}
