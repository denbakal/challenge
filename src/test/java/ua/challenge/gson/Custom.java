package ua.challenge.gson;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by d.bakal on 10.12.2016.
 */
@Getter
@Setter
public class Custom {
    private Date date;
    private BigInteger integer;

    Custom(Date date, BigInteger integer) {
        this.date = date;
        this.integer = integer;
    }
}
