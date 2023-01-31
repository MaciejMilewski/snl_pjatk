package pl.edu.pjwstk.snl.match.dto.ActionAggregator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pjwstk.snl.match.action.ChangeGolkiper;

@Getter
@Setter
@AllArgsConstructor
public class ChangeGoalkeeperToAggregationDTO {
    private Long id;
    private String type = "CHANGE_GOALKEEPER";
    private Integer gameMinute;
    private Integer gameSecond;
    private String teamName;

    private String goalkeeperInName;
    private String goalkeeperInSurname;
    private Integer goalkeeperInNumber;

    private String goalkeeperOutName;
    private String goalkeeperOutSurname;
    private Integer goalkeeperOutNumber;

    private Integer totalTimeInSeconds;

    private String teamType;

    public  ChangeGoalkeeperToAggregationDTO(ChangeGolkiper changeGoalkeeper) {
        Long id = changeGoalkeeper.getId();
        Integer gameMinute = changeGoalkeeper.getGameMinute();
        Integer gameSecond = changeGoalkeeper.getGameSecond();
        this.id = id;


        this.gameMinute = gameMinute;

        this.gameSecond = gameSecond;
        this.teamName = changeGoalkeeper.getTeam().getName();

        this.goalkeeperInName = changeGoalkeeper.getGolkiperIn().getPlayer().getName();
        this.goalkeeperInSurname = changeGoalkeeper.getGolkiperIn().getPlayer().getSurname();
        this.goalkeeperInNumber = changeGoalkeeper.getGolkiperIn().getNumber();

        this.goalkeeperOutName = changeGoalkeeper.getGolkiperOut().getPlayer().getName();
        this.goalkeeperOutSurname = changeGoalkeeper.getGolkiperOut().getPlayer().getSurname();
        this.goalkeeperOutNumber = changeGoalkeeper.getGolkiperOut().getNumber();

        this.totalTimeInSeconds = gameMinute *60 + gameSecond;

        if (changeGoalkeeper.getMatch().getHomeTeam().getName().equals(this.teamName)) {
            this.teamType = "homeTeam";
        } else {
            this.teamType = "visitingTeam";
        }
    }
}
