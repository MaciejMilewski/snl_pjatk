package pl.edu.pjwstk.snl.match.dto.ActionAggregator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.match.action.PenaltyShot;

@Getter
@Setter
@AllArgsConstructor
public class PenaltyShotToAggregationDTO {
    private Long id;
    private String type = "PENALTY_SHOT";
    private String teamName;
    private boolean success;

    private String goalkeeperName;
    private String goalkeeperSurname;
    private Integer goalkeeperNumber;

    private String shooterName;
    private String shooterSurname;
    private Integer shooterNumber;

    private Integer totalTimeInSeconds;

    private String teamType;

    public PenaltyShotToAggregationDTO(PenaltyShot penaltyShot) {
        this.id = penaltyShot.getId();
        this.teamName = penaltyShot.getTeam().getName();
        this.success = penaltyShot.isSuccess();

        this.goalkeeperName = penaltyShot.getGoalkiper().getPlayer().getName();
        this.goalkeeperSurname = penaltyShot.getGoalkiper().getPlayer().getSurname();
        this.goalkeeperNumber = penaltyShot.getGoalkiper().getNumber();

        this.shooterName = penaltyShot.getSchooter().getPlayer().getName();
        this.shooterSurname = penaltyShot.getSchooter().getPlayer().getSurname();
        this.shooterNumber = penaltyShot.getSchooter().getNumber();

        this.totalTimeInSeconds = penaltyShot.getGameMinute()*60 + penaltyShot.getGameSecond();

        if (penaltyShot.getMatch().getHomeTeam().getName().equals(this.teamName)) {
            this.teamType = "homeTeam";
        } else {
            this.teamType = "visitingTeam";
        }
    }
}
