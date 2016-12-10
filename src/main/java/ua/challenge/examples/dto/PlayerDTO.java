package ua.challenge.examples.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by d.bakal on 05.11.2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PlayerDTO {
    private long id;
    private String firstName;
    private String lastName;
    private TeamDTO team;
}
