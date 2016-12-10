package ua.challenge.examples.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ua.challenge.examples.dto.PlayerDTO;
import ua.challenge.examples.dto.TeamDTO;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.entity.Player;
import ua.challenge.hibernate.examples.relationship.one.to.many.bidirectional.entity.Team;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by d.bakal on 05.11.2016.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamConverter {
    public static Team toEntity(TeamDTO dto) {
        if (dto == null) {
            return null;
        }

        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        /*team.setPlayers(dto.getPlayers().stream().map(PlayerConverter::toEntity)
                .collect(Collectors.toSet()));*/

        /* --- 1 --- */

        /*Set<Player> players = new HashSet<>();

        if (!CollectionUtils.isEmpty(dto.getPlayers())) {
            for (PlayerDTO playerDTO : dto.getPlayers()) {
                Player player = new Player();
                player.setId(playerDTO.getId());
                player.setFirstName(playerDTO.getFirstName());
                player.setLastName(playerDTO.getLastName());
                player.setTeam(team);

                players.add(player);
            }
        }

        team.setPlayers(players);*/

        /* --- 2 --- */

        /*Set<Player> players = dto.getPlayers().stream().map(PlayerConverter::toEntityWithoutTeam)
                .collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(players)) {
            players.forEach(player -> player.setTeam(team));
        }

        team.setPlayers(players);*/

        /* --- 3 --- */

        team.setPlayers(dto.getPlayers().stream().map(playerDTO -> PlayerConverter.toEntityWithTeam(playerDTO, team))
                .collect(Collectors.toSet()));

        /* --- */

        return team;
    }
}
