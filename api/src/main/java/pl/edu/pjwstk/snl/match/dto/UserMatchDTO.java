package pl.edu.pjwstk.snl.match.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.match.MatchStatus;

@Getter
@Setter
@AllArgsConstructor
public class UserMatchDTO {
    private long id;
    private String homeTeam;
    private String visitingTeam;
    private MatchStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private byte[] homeTeamImage;
    private byte[] visitingTeamImage;

    public UserMatchDTO(Match match) {
        this.id = match.getId();
        this.homeTeam = match.getHomeTeam().getName();
        this.visitingTeam = match.getVisitingTeam().getName();
        this.status = match.getStatus();
        this.startTime = match.getStartTime();
        this.endTime = match.getEndTime();
        this.homeTeamImage = match.getHomeTeam().getImage();
        this.visitingTeamImage = match.getVisitingTeam().getImage();
    }
}
