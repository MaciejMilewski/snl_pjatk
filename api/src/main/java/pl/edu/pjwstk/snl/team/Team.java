package pl.edu.pjwstk.snl.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import pl.edu.pjwstk.snl.five.Five;
import pl.edu.pjwstk.snl.league.League;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.security.User.User;


@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "bytea")
    private byte[] image;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "team_players",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id") })
    private Set<Player> players = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "team_leagues",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "league_id") })
    private Set<League> leagues = new HashSet<>();

    @OneToMany(mappedBy = "team")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Five> fives;

    @OneToMany(mappedBy = "team")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Set<User> trainers;

    public Team(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        player.getTeams().add(this);
    }

    public void removePlayer(long playerId) {
        Player player = this.players.stream().filter(t -> t.getId() == playerId).findFirst().orElse(null);
        if (player != null) {
            this.players.remove(player);
            player.getTeams().remove(this);
        }
    }

    public void addLeague(League league) {
        this.leagues.add(league);
        league.getTeams().add(this);
    }

    public void removeLeague(long leagueId) {
        League league = this.leagues.stream().filter(t -> t.getId() == leagueId).findFirst().orElse(null);
        if (league != null) {
            this.leagues.remove(league);
            league.getTeams().remove(this);
        }
    }

    public void addFive(Five five) {
        this.fives.add(five);
        five.setTeam(this);
    }

//    public void removeFive(long fiveId) {
//        Five five = this.fives.stream().filter(t -> t.getId() == fiveId).findFirst().orElse(null);
//        if (five != null) {
//            this.fives.remove(five);
//        }
//    }
}
