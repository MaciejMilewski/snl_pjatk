package pl.edu.pjwstk.snl.match.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_breaks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class BreakeTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //private LocalDateTime time;
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
}
