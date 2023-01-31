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
import java.time.LocalDateTime;

@Entity
@Table(name = "match_changeGolkiper")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class ChangeGolkiper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer gameMinute;
    private Integer gameSecond;
   // private LocalDateTime time;
   // private Integer golkiperInNo;
   // private Integer golkiperOutNo;
   @OneToOne(cascade = {
           CascadeType.PERSIST,
           CascadeType.MERGE
   })
   private MatchPlayer golkiperIn;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private MatchPlayer golkiperOut;

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
