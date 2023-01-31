package pl.edu.pjwstk.snl.security.User;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.security.Role.ERole;
import pl.edu.pjwstk.snl.security.Role.Role;
import pl.edu.pjwstk.snl.security.Role.RoleRepository;
import pl.edu.pjwstk.snl.security.User.Trainer.TrainerDTO;
import pl.edu.pjwstk.snl.strategy.Strategy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findAll() {
        List<User> users = userService.findAll();

        Assertions.assertTrue(users.size() == 7);
    }

    @Test
    void findById() {
        Assertions.assertTrue(userService.findById(1L).orElse(null)
                .getUsername().equals("Jan Sekretarz"));
    }

    @Test
    void save() {
        userService.save(User.builder()
                .username("Jacek Dude")
                .email("jacekd@gmail.com")
                .password("$2a$10$cRVqL4OUJvXgMGBt.qSz/uuZP4lw2PUWLIY8KEbtXIuE9QsT2DCXu")
                .build());

        Assertions.assertTrue(userService.existsByUsername("Jacek Dude"));
    }

    @Test
    void deleteById() {
        userService.deleteById(1L);

        Assertions.assertFalse(userService.existsByUsername("Jan Sekretarz"));
    }

    @Test
    void existsById() {
        Assertions.assertTrue(userService.existsById(1L));
    }

    @Test
    void findByTeamId() {
        Assertions.assertTrue(userService.findByTeamId(1L).get(0).getUsername().equals("Jan Goldwell"));
    }

    @Test
    void existsByUsername() {
        Assertions.assertTrue(userService.existsByUsername("Jan Sekretarz"));
    }

    @Test
    void existsByEmail() {
        Assertions.assertTrue(userService.existsByEmail("j.admin@snl.pl"));
    }

    @Test
    void findAllTrainers() {
        List<TrainerDTO> trainers = userService.findAllTrainers();

        Assertions.assertTrue(trainers.get(0).getName().equals("Jan Goldwell"));
        Assertions.assertTrue(trainers.get(0).getTeamName().equals("Goldwell"));
        Assertions.assertTrue(trainers.get(1).getName().equals("Jan Dragons"));
        Assertions.assertTrue(trainers.get(1).getTeamName().equals("Dragons"));
    }

    @Test
    void isAdmin() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.ROLE_ADMIN));
        User user = User.builder()
                .username("Jacek Dude")
                .email("jacekd@gmail.com")
                .password("$2a$10$cRVqL4OUJvXgMGBt.qSz/uuZP4lw2PUWLIY8KEbtXIuE9QsT2DCXu")
                .roles(roles)
                .build();

        Assertions.assertTrue(userService.isAdmin(user));
    }

    @Test
    void setRolesFromStringSet() {
        Set<String> strRoles = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        strRoles.add("admin");

        userService.setRolesFromStringSet(strRoles, roles);

        Assertions.assertTrue(roles.stream().toList().get(0).getName().equals(ERole.ROLE_ADMIN));
    }
}