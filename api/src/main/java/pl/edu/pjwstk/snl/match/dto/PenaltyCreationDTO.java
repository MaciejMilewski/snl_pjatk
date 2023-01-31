package pl.edu.pjwstk.snl.match.dto;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCreationDTO {

    private Integer gameMinute;
    private Integer gameSecond;
    private TimeLength timeLength;
    private TypePenalty typePenalty;
    private IdDTO penalized;


}