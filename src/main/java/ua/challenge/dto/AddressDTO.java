package ua.challenge.dto;

import lombok.*;

/**
 * Created by d.bakal on 30.10.2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddressDTO {
    private Long id;
    private CityDTO city;
    private String street;
}
