package pl.edu.pjwstk.snl.match.dto.ActionAggregator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.match.action.Goal;
import pl.edu.pjwstk.snl.match.dto.Situation;

@Getter
@Setter
@AllArgsConstructor
public class GoalToAggregationDTO {
    private Long id;

    private String type = "GOAL";

    private String scorerName;
    private String scorerSurname;
    private Integer scorerNumber;

    private String assistant1Name;
    private String assistant1Surname;
    private Integer assistant1Number;

    private String assistant2Name;
    private String assistant2Surname;
    private Integer assistant2Number;

    private Integer gameMinute;
    private Integer gameSecond;
    private String teamName;
    private Integer totalTimeInSeconds;

    @Enumerated(EnumType.STRING)
    private Situation situation;

    private String teamType;

    private Integer goalNo;

    public GoalToAggregationDTO(Goal goal, Integer goalNo) {
        this.goalNo = goalNo;

        this.id = goal.getId();
        this.gameMinute = goal.getGameMinute();
        this.gameSecond = goal.getGameSecond();
        this.situation = goal.getSituation();
        this.teamName = goal.getTeam().getName();

        this.scorerName = goal.getScorer().getPlayer().getName();
        this.scorerSurname = goal.getScorer().getPlayer().getSurname();
        this.scorerNumber = goal.getScorer().getNumber();

        if (goal.getAssistant1() != null) {
            this.assistant1Name = goal.getAssistant1().getPlayer().getName();
            this.assistant1Surname = goal.getAssistant1().getPlayer().getSurname();
            this.assistant1Number = goal.getAssistant1().getNumber();
        }

        if (goal.getAssistant2() != null) {
            this.assistant2Name = goal.getAssistant2().getPlayer().getName();
            this.assistant2Surname = goal.getAssistant2().getPlayer().getSurname();
            this.assistant2Number = goal.getAssistant2().getNumber();
        }

        this.totalTimeInSeconds = goal.getGameMinute()*60 + goal.getGameSecond();

        if (goal.getMatch().getHomeTeam().getName().equals(this.teamName)) {
            this.teamType = "homeTeam";
        } else {
            this.teamType = "visitingTeam";
        }
    }
}
