package pl.edu.pjwstk.snl.match.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoalCreationDTO {

    private Integer gameMinute;
    private Integer gameSecond;
    private Situation situation;

    private IdDTO scorer;
    private IdDTO assistant1;
    private IdDTO assistant2;

}
