package pl.edu.pjwstk.snl.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjwstk.snl.team.Team;

@Entity
@Table(name = "leagues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "leagues")
    @JsonIgnore
    private Set<Team> teams = new HashSet<>();

    public League(String name) {
        this.name = name;
    }

    public void removeTeam(long teamId) {
        Team team = this.teams.stream().filter(t -> t.getId() == teamId).findFirst().orElse(null);
        if (team != null) {
            this.teams.remove(team);
            team.getLeagues().remove(this);
        }
    }
}
