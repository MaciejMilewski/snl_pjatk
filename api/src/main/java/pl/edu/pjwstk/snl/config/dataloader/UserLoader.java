package pl.edu.pjwstk.snl.config.dataloader;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.security.Role.ERole;
import pl.edu.pjwstk.snl.security.Role.Role;
import pl.edu.pjwstk.snl.security.Role.RoleRepository;
import pl.edu.pjwstk.snl.security.User.User;
import pl.edu.pjwstk.snl.security.User.UserService;
import pl.edu.pjwstk.snl.team.TeamService;

@Component
@RequiredArgsConstructor
@Order(100)
public class UserLoader implements ApplicationRunner {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final TeamService teamService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Set<Role> secretaryRoles = new HashSet<>();
        Set<Role> refereeOrMedicRoles = new HashSet<>();
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> trainerRoles = new HashSet<>();

        secretaryRoles.add(roleRepository.findByName(ERole.ROLE_SECRETARY).orElse(null));
        refereeOrMedicRoles.add(roleRepository.findByName(ERole.ROLE_MEDIC_OR_JUDGE).orElse(null));
        adminRoles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElse(null));
        trainerRoles.add(roleRepository.findByName(ERole.ROLE_TRAINER).orElse(null));

        String password = "$2a$10$cRVqL4OUJvXgMGBt.qSz/uuZP4lw2PUWLIY8KEbtXIuE9QsT2DCXu";
        User secretary = new User("Jan Sekretarz", "j.sekretarz@snl.pl", password);
        User medic = new User("Jan Lekarz", "m.lekarz@snl.pl", password);
        User referee1 = new User("Jan Sędziowski", "b.sedziowski@snl.pl", password);
        User referee2 = new User("Jan Sędziwy", "s.sendziwy@snl.pl", password);
        User admin = new User("Jan Admin", "j.admin@snl.pl", password);
        User trainer1 = new User("Jan Goldwell", "m.treningowy@snl.pl", password);
        User trainer2 = new User("Jan Dragons", "k.trenisz@snl.pl", password);

        secretary.setRoles(secretaryRoles);
        medic.setRoles(refereeOrMedicRoles);
        referee1.setRoles(refereeOrMedicRoles);
        referee2.setRoles(refereeOrMedicRoles);
        admin.setRoles(adminRoles);

        trainer1.setRoles(trainerRoles);
        trainer1.setTeam(this.teamService.findByName("Goldwell").orElse(null));
        trainer2.setRoles(trainerRoles);
        trainer2.setTeam(this.teamService.findByName("Dragons").orElse(null));

        userService.save(secretary);
        userService.save(medic);
        userService.save(referee1);
        userService.save(referee2);
        userService.save(trainer1);
        userService.save(trainer2);
        userService.save(admin);
    }
}
