package pl.edu.pjwstk.snl.match.dto;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyShotCreationDTO {

    private IdDTO schooter;
    private IdDTO goalkiper;
    private boolean success;
    private Integer gameMinute;
    private Integer gameSecond;

}