package pl.edu.pjwstk.snl.match.dto.ActionAggregator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.match.action.BreakeTime;

@Getter
@Setter
@AllArgsConstructor
public class BreakTimeToAggregationDTO {
    private Long id;
    private String type = "BREAK_TIME";
    private Integer gameMinute;
    private Integer gameSecond;
    private String teamName;
    private Integer totalTimeInSeconds;

    private String teamType;

    public BreakTimeToAggregationDTO(BreakeTime breakTime) {
        this.id = breakTime.getId();
        this.gameMinute = breakTime.getGameMinute();
        this.gameSecond = breakTime.getGameSecond();
        this.teamName = breakTime.getTeam().getName();
        this.totalTimeInSeconds = breakTime.getGameMinute()*60 + breakTime.getGameSecond();

        if (breakTime.getMatch().getHomeTeam().getName().equals(this.teamName)) {
            this.teamType = "homeTeam";
        } else {
            this.teamType = "visitingTeam";
        }
    }
}
