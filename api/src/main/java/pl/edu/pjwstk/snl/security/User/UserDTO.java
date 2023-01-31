package pl.edu.pjwstk.snl.security.User;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
