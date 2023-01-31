package pl.edu.pjwstk.snl.match.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Data
public class PenaltiesTable {
    String finalPenalties;
    String p1Penalties;
    String p2Penalties;
    String p3Penalties;
}
