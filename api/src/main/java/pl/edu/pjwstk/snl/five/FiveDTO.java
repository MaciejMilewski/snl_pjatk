package pl.edu.pjwstk.snl.five;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FiveDTO {
    private String name;
    private List<FivePlayerDTO> players;
}
