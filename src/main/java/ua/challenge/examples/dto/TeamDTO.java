package ua.challenge.examples.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * Created by d.bakal on 05.11.2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TeamDTO {
    private long id;
    private String name;
    private Set<PlayerDTO> players;
}
