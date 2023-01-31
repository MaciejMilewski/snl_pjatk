package pl.edu.pjwstk.snl.match.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.match.MatchPlayer;
import pl.edu.pjwstk.snl.match.dto.TimeLength;
import pl.edu.pjwstk.snl.match.dto.TypePenalty;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.*;


@Entity
@Table(name = "match_penalties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // private LocalDateTime startTime;
    //private LocalDateTime endTime;
    //private Integer number;
    private Integer gameMinute;
    private Integer gameSecond;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    private MatchPlayer penalized;


    @Enumerated(EnumType.STRING)
    private TimeLength timeLength;
    @Enumerated(EnumType.STRING)
    private TypePenalty typePenalty;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    @JsonIgnore
    private Match match;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Team team;

}
