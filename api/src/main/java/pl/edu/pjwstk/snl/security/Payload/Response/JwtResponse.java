package pl.edu.pjwstk.snl.security.Payload.Response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private Long teamId;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, Long teamId) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.teamId = teamId;
    }
}
