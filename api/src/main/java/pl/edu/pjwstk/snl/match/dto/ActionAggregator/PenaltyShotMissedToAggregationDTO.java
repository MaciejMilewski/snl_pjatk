package pl.edu.pjwstk.snl.match.dto.ActionAggregator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.match.action.PenaltyShot;
import pl.edu.pjwstk.snl.match.action.PenaltyShotMissed;

@Getter
@Setter
@AllArgsConstructor
public class PenaltyShotMissedToAggregationDTO {
    private Long id;
    private String type = "PENALTY_SHOT_MISSED";
    private String teamName;
    private String goalkeeperName;
    private String goalkeeperSurname;
    private Integer goalkeeperNumber;

    private String shooterName;
    private String shooterSurname;
    private Integer shooterNumber;
    private Integer totalTimeInSeconds;

    private String teamType;

    public PenaltyShotMissedToAggregationDTO(PenaltyShotMissed penaltyShotMissed) {
        this.id = penaltyShotMissed.getId();
        this.teamName = penaltyShotMissed.getTeam().getName();

        this.goalkeeperName = penaltyShotMissed.getGoalkiper().getPlayer().getName();
        this.goalkeeperSurname = penaltyShotMissed.getGoalkiper().getPlayer().getSurname();
        this.goalkeeperNumber = penaltyShotMissed.getGoalkiper().getNumber();

        this.shooterName = penaltyShotMissed.getShooter().getPlayer().getName();
        this.shooterSurname = penaltyShotMissed.getShooter().getPlayer().getSurname();
        this.shooterNumber = penaltyShotMissed.getShooter().getNumber();

        this.totalTimeInSeconds = penaltyShotMissed.getGameMinute()*60 + penaltyShotMissed.getGameSecond();

        if (penaltyShotMissed.getMatch().getHomeTeam().getName().equals(this.teamName)) {
            this.teamType = "homeTeam";
        } else {
            this.teamType = "visitingTeam";
        }
    }
}
