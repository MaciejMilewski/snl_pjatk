package pl.edu.pjwstk.snl.five;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjwstk.snl.player.Player;

@Entity
@Table(name = "five_players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FivePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer number;

    @Enumerated(EnumType.STRING)
    private Player.Position position;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "five_five_players",
            joinColumns = { @JoinColumn(name = "five_players_id") },
            inverseJoinColumns = { @JoinColumn(name = "five_id") })
    @JsonIgnore
    private Set<Five> fives = new HashSet<>();

    public void addFive(Five five) {
        this.fives.add(five);
        five.getPlayers().add(this);
    }

    public void removeFive(long fiveId) {
        Five five = this.fives.stream().filter(t -> t.getId() == fiveId).findFirst().orElse(null);
        if (five != null) {
            this.fives.remove(five);
            five.getPlayers().remove(this);
        }
    }
}
