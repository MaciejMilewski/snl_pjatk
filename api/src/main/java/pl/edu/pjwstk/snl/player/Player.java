package pl.edu.pjwstk.snl.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import org.hibernate.annotations.Type;
import pl.edu.pjwstk.snl.five.Five;
import pl.edu.pjwstk.snl.team.Team;


@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize(using = PlayerSerializer.class)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private Integer playerNumber;
    @Enumerated(EnumType.STRING)
    private Position position;
    private LocalDate birthDate;
    private String birthplace;
    private Integer weight;
    @Enumerated(EnumType.STRING)
    private Stick stick;

    @Column(columnDefinition = "bytea")
    private byte[] image;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "players")
    @JsonIgnore
    private Set<Team> teams = new HashSet<>();

    public Player(String name, String surname, Integer playerNumber, Position position, LocalDate birthDate, String birthplace, Integer weight, Stick stick, byte[] image) {
        this.name = name;
        this.surname = surname;
        this.playerNumber = playerNumber;
        this.position = position;
        this.birthDate = birthDate;
        this.birthplace = birthplace;
        this.weight = weight;
        this.stick = stick;
        this.image = image;
    }

    public void removeTeam(long teamId) {
        Team team = this.teams.stream().filter(t -> t.getId() == teamId).findFirst().orElse(null);
        if (team != null) {
            this.teams.remove(team);
            team.getPlayers().remove(this);
        }
    }

    public enum Position {
        BR,//bramkarz
        LO,//lewy obrońca
        PO,//prawy obronca
        LS,//lewy napastnik
        SR,//środkowy napastnik
        PS//prawy napastnik
    }
    public enum Stick {
        Lewy,
        Prawy
    }
}
