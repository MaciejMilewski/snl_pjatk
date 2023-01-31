package pl.edu.pjwstk.snl.five;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.player.Player;

@Getter
@Setter
@AllArgsConstructor
public class FivePlayerDTO {
    private long id;
    private long fivePlayerId;
    private Player.Position position;
    private Integer number;
}
