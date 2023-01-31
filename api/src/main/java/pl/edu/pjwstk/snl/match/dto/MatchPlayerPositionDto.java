package pl.edu.pjwstk.snl.match.dto;

import lombok.*;
import pl.edu.pjwstk.snl.player.Player;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchPlayerPositionDto {

    private long playerId;
    private Integer number;
    private Player.Position position;
}
