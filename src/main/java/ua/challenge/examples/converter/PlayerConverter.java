package ua.challenge.examples.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.challenge.examples.dto.PlayerDTO;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.entity.Player;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.entity.Team;

/**
 * Created by d.bakal on 05.11.2016.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerConverter {
    public static Player toEntity(PlayerDTO dto) {
        if (dto == null) {
            return null;
        }

        Player player = new Player();
        player.setId(dto.getId());
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setTeam(TeamConverter.toEntity(dto.getTeam()));

        return player;
    }

    public static Player toEntityWithoutTeam(PlayerDTO dto) {
        if (dto == null) {
            return null;
        }

        Player player = new Player();
        player.setId(dto.getId());
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());

        return player;
    }

    public static Player toEntityWithTeam(PlayerDTO dto, Team team) {
        if (dto == null) {
            return null;
        }

        Player player = new Player();
        player.setId(dto.getId());
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setTeam(team);

        return player;
    }
}
