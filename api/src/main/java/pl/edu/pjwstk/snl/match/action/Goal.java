package pl.edu.pjwstk.snl.match.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.match.MatchPlayer;
import pl.edu.pjwstk.snl.match.dto.Situation;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.*;


@Entity
@Table(name = "match_goals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private MatchPlayer scorer;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private MatchPlayer assistant1;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private MatchPlayer assistant2;

    private Integer gameMinute;
    private Integer gameSecond;
    @Enumerated(EnumType.STRING)
    private Situation situation;

    @ManyToOne
    @JoinColumn(name="match_id", nullable=false)
    @JsonIgnore
    private Match match;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Team team;

}
