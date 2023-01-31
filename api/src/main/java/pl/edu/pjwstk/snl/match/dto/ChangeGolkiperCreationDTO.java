package pl.edu.pjwstk.snl.match.dto;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeGolkiperCreationDTO {

    private Integer gameMinute;
    private Integer gameSecond;
    private IdDTO golkiperIn;
    private IdDTO golkiperOut;



}
