package pl.edu.pjwstk.snl.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import pl.edu.pjwstk.snl.five.Five;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.player.Player.Position;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.*;


@Entity
@Table(name = "match_players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer number;

    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
}
