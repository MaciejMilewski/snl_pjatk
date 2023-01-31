package pl.edu.pjwstk.snl.match.action;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.match.MatchPlayer;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.*;

@Entity
@Table(name = "match_penaltyShot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class PenaltyShot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private MatchPlayer schooter;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private MatchPlayer goalkiper;

    private Integer gameMinute;
    private Integer gameSecond;

    @ManyToOne
    @JoinColumn(name="match_id", nullable=false)
    @JsonIgnore
    private Match match;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Team team;

    private boolean success;
}
