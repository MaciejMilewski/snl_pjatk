package pl.edu.pjwstk.snl.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.edu.pjwstk.snl.five.Five;
import pl.edu.pjwstk.snl.match.action.*;
import pl.edu.pjwstk.snl.match.dto.MatchInfo;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Match extends MatchInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, orphanRemoval = true)
    private MatchLineup homeTeamMatchLineup;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MatchLineup visitingTeamMatchLineup;

    @OneToMany(mappedBy="match",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Goal> goals;

    @OneToMany(mappedBy="match",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<BreakeTime> breakeTimes;

    @OneToMany(mappedBy="match",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Penalty> penalties;

    @OneToMany(mappedBy="match",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ChangeGolkiper> changeGolkipers;

    @OneToMany(mappedBy="match",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PenaltyShot> penaltyShots;

    @OneToMany(mappedBy="match",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PenaltyShotMissed> penaltyShotsMissed;

}

