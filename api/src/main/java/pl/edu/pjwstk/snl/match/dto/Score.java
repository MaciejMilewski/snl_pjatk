package pl.edu.pjwstk.snl.match.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Data
public class Score {
    String finalScore;
    String p1Score;
    String p2Score;
    String p3Score;
    String p4Score; //karne


}
