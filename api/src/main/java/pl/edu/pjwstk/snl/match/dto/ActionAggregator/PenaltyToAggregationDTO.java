package pl.edu.pjwstk.snl.match.dto.ActionAggregator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.match.action.Penalty;
import pl.edu.pjwstk.snl.match.dto.TimeLength;
import pl.edu.pjwstk.snl.match.dto.TypePenalty;

@Getter
@Setter
@AllArgsConstructor
public class PenaltyToAggregationDTO {
    private Long id;
    private String type = "PENALTY";
    private Integer gameMinute;
    private Integer gameSecond;
    private String teamName;

    @Enumerated(EnumType.STRING)
    private TimeLength timeLength;

    @Enumerated(EnumType.STRING)
    private TypePenalty typePenalty;

    private String penalizedPlayerName;
    private String penalizedPlayerSurname;
    private Integer penalizedPlayerNumber;

    private Integer totalTimeInSeconds;
    private String teamType;

    public PenaltyToAggregationDTO(Penalty penalty) {
        this.id = penalty.getId();
        this.gameMinute = penalty.getGameMinute();
        this.gameSecond = penalty.getGameSecond();
        this.teamName = penalty.getTeam().getName();
        this.timeLength = penalty.getTimeLength();
        this.typePenalty = penalty.getTypePenalty();

        this.penalizedPlayerName =penalty.getPenalized().getPlayer().getName();
        this.penalizedPlayerSurname =penalty.getPenalized().getPlayer().getSurname();
        this.penalizedPlayerNumber =penalty.getPenalized().getNumber();

        this.totalTimeInSeconds = penalty.getGameMinute()*60 + penalty.getGameSecond();

        if (penalty.getMatch().getHomeTeam().getName().equals(this.teamName)) {
            this.teamType = "homeTeam";
        } else {
            this.teamType = "visitingTeam";
        }
    }
}
