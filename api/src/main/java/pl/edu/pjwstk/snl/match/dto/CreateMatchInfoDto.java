package pl.edu.pjwstk.snl.match.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.edu.pjwstk.snl.team.Team;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class CreateMatchInfoDto {

    private String place;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private Integer attendance; //frekwencja

    private Long homeTeamId;
    private Long visitingTeamId;
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
    private String medicNot; //notka medyka
    private String refereeNot;//notka sedziego/sekretarza

    private Long secretaryId;
    private Long medicId;
    private Long referee1Id;
    private Long referee2Id;
}
