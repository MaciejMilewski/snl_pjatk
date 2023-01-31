package pl.edu.pjwstk.snl.security.User;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.strategy.Strategy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceTest {

    @Autowired
    private UserService userService;

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
                .email("")
                .password("")
                .build());
    }

    @Test
    void deleteById() {
    }

    @Test
    void existsById() {
    }

    @Test
    void findByTeamId() {
    }

    @Test
    void existsByUsername() {
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void findAllTrainers() {
    }

    @Test
    void isAdmin() {
    }

    @Test
    void setRolesFromStringSet() {
    }
}