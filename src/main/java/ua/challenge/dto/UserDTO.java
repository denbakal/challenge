package ua.challenge.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Created by d.bakal on 30.10.2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private AddressDTO address;
    private List<DepartmentDTO> departments;
}
