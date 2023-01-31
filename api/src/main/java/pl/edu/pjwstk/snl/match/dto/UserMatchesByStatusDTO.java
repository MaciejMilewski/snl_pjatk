package pl.edu.pjwstk.snl.match.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMatchesByStatusDTO {
    List<UserMatchDTO> created;
    List<UserMatchDTO> ready;
    List<UserMatchDTO> inProgress;
    List<UserMatchDTO> finished;
    List<UserMatchDTO> protocolFinalized;
    List<UserMatchDTO> disapproval;
    List<UserMatchDTO> corrected;
}
