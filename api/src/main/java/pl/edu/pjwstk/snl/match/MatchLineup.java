package pl.edu.pjwstk.snl.match;

import lombok.*;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "match_lineups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchLineup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToOne(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    private Team team;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "match_lineup_players",
            joinColumns = {@JoinColumn(name = "match_lineup_id")},
            inverseJoinColumns = {@JoinColumn(name = "match_player_id")})
    private List<MatchPlayer> matchPlayers = new ArrayList<>();

    public boolean isLineupComplete() {
        if (this.matchPlayers.size() >= 6) {
            return true;
        }
        return false;
    }
}
