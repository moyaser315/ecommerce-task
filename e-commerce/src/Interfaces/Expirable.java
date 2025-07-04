package Interfaces;

import java.time.LocalDate;

public interface Expirable {
    boolean isExpired();

    LocalDate getExpirationDate();

    void setExpirationDate(LocalDate date) throws Exception;

}
