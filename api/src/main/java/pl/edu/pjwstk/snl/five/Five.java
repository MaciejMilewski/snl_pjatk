package pl.edu.pjwstk.snl.five;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.edu.pjwstk.snl.match.MatchPlayer;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.team.Team;

@Entity
@Table(name = "fives")
@Getter
@Setter
@Builder
//@NoArgsConstructor
@JsonSerialize(using = FiveSerializer.class)
public class Five {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "fives")
    private Set<FivePlayer> players = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Team team;

    public Five(String name) {this.name = name;}
    public Five() {}

    public Five(long id, String name, Set<FivePlayer> players, Team team) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.team = team;
    }
}
