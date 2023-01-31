package pl.edu.pjwstk.snl.config.dataloader;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.security.Role.ERole;
import pl.edu.pjwstk.snl.security.Role.Role;
import pl.edu.pjwstk.snl.security.Role.RoleRepository;

@Component
@RequiredArgsConstructor
@Order(99)
public class RoleLoader implements ApplicationRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role secretaryRole = roleRepository.save(new Role(ERole.ROLE_SECRETARY));
        Role adminRole = roleRepository.save(new Role(ERole.ROLE_ADMIN));
        Role trainerRole = roleRepository.save(new Role(ERole.ROLE_TRAINER));
        Role medicOrJudgeRole = roleRepository.save(new Role(ERole.ROLE_MEDIC_OR_JUDGE));

        this.roleRepository.save(secretaryRole);
        this.roleRepository.save(adminRole);
        this.roleRepository.save(trainerRole);
        this.roleRepository.save(medicOrJudgeRole);
    }
}
