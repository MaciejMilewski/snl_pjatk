package pl.edu.pjwstk.snl.security.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.security.Role.ERole;
import pl.edu.pjwstk.snl.security.Role.Role;
import pl.edu.pjwstk.snl.security.Role.RoleRepository;
import pl.edu.pjwstk.snl.security.User.Trainer.TrainerDTO;
import pl.edu.pjwstk.snl.team.Team;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> findAll() { return this.userRepository.findAll(); }

    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    public void save(User user) { this.userRepository.save(user); }

    public void deleteById(long id) {
        this.userRepository.deleteById(id);
    }

    public boolean existsById(long id) { return this.userRepository.existsById(id); }

    public List<User> findByTeamId(long id) { return this.userRepository.findByTeamId(id); }

    public boolean existsByUsername(String username) { return this.userRepository.existsByUsername(username);}

    public boolean existsByEmail(String email) { return this.userRepository.existsByEmail(email);}

    @Transactional
    public List<TrainerDTO> findAllTrainers() {
         List<User> users = this.userRepository.findAll();
         List<TrainerDTO> trainers = new ArrayList<>();

         for (User user : users) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (role.getName().equals(ERole.ROLE_TRAINER)) {
                    Team team = user.getTeam();
                    if (team == null) {
                        trainers.add(new TrainerDTO(user.getUsername(), "Brak drużyny"));
                    } else {
                        trainers.add(new TrainerDTO(user.getUsername(), team.getName()));
                    }
                }
            }
         }

        return trainers;
    }

    @Transactional
    public boolean isAdmin(User user) {
        Set<Role> roles = user.getRoles();

        for (Role role : roles) {
            if (role.getName().equals(ERole.ROLE_ADMIN)) {
                return true;
            }
        }

        return false;
    }

    public void setRolesFromStringSet(Set<String> strRoles, Set<Role> roles) {
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "secretary":
                    Role secretaryRole = roleRepository.findByName(ERole.ROLE_SECRETARY)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(secretaryRole);

                    break;
                case "trainer":
                    Role trainerRole = roleRepository.findByName(ERole.ROLE_TRAINER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(trainerRole);

                    break;
                case "medicOrJudge":
                    Role medicOrJudgeRole = roleRepository.findByName(ERole.ROLE_MEDIC_OR_JUDGE)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(medicOrJudgeRole);

                    break;
                default:
                    throw new RuntimeException("Error: Role is not found.");
            }
        });
    }

}
