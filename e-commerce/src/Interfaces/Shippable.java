package Interfaces;

import java.time.LocalDate;

public interface Shippable {
    double getWeight();

    boolean isShipped();

    void setShippingDate(LocalDate date) throws Exception;

    LocalDate getShippingDate() throws Exception;
}
