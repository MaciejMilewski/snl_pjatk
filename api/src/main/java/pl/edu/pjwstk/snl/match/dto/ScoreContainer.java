package pl.edu.pjwstk.snl.match.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Data
public class ScoreContainer {
    private Score regular;
    private Score powerPlay;
    private Score shortHanded;
}
