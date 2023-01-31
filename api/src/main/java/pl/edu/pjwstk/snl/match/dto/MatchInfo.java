package pl.edu.pjwstk.snl.match.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.edu.pjwstk.snl.match.MatchLineup;
import pl.edu.pjwstk.snl.match.MatchStatus;
import pl.edu.pjwstk.snl.match_user.MatchUser;
import pl.edu.pjwstk.snl.security.User.User;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class MatchInfo {

    private String place;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
     @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private Integer attendance; //frekwencja

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team homeTeam;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team visitingTeam;

    private String referee1;//sędzia główny 1
    private String referee2;//sędzia główny 2
    private String linesmen1;//sędzia liniowy 1
    private String linesmen2;//sędzia liniowy 2

    private String homeTeamMenager;
    private String homeTeamCoach;

    private String visitingTeamMenager;
    private String visitingTeamCoach;

    private String spectator;// Obserwator

    private String timeReferee;//sędzia czasu
    private String penaltyReferee1;//sędzia kar 1
    private String penaltyReferee2;//sędzia kar 2

    private String speaker; //spiker
    private String secretary;
//    private String medicNot; //notka medyka
//    private String refereeNot;//notka sedziego/sekretarza

    @OneToMany(mappedBy = "match",
    cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<MatchUser> matchUsers;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;
}
