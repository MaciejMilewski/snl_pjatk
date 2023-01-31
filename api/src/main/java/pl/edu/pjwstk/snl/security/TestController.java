package pl.edu.pjwstk.snl.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content. No need for authentication";
    }

    @GetMapping("/everyone")
    @PreAuthorize("isAuthenticated()")
    public String anyAccess() {
        return "Content for anyone who is authenticated.";
    }

    @GetMapping("/loggedInUsers")
    @PreAuthorize("hasRole('TRAINER') or hasRole('SECRETARY') or hasRole('ADMIN') or hasRole('ROLE_MEDIC_OR_JUDGE')")
    public String userAccess() {
        return "Content for trainers, secretaries, medics/judges and admin. Any of them can access this endpoint.";
    }

    @GetMapping("/trainer")
    @PreAuthorize("hasRole('TRAINER')")
    public String trainerAccess() {
        return "Trainer Board.";
    }

    @GetMapping("/secretary")
    @PreAuthorize("hasRole('SECRETARY')")
    public String secretaryAccess() {
        return "Secretary Board.";
    }

    @GetMapping("/medicOrJudge")
    @PreAuthorize("hasRole('ROLE_MEDIC_OR_JUDGE')")
    public String medicOrJudgeAccess() {
        return "Medic or judge Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
