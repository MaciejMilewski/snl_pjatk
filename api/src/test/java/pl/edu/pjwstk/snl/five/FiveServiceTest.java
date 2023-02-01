package pl.edu.pjwstk.snl.five;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.team.TeamService;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FiveServiceTest {

    @Autowired
    private FiveService fiveService;

    @Autowired
    private TeamService teamService;

    @Test
    void findAll() {
        List<Five> fives = fiveService.findAll();

        Assertions.assertTrue(fives.size() == 8);
    }

    @Test
    void findById() {
        Assertions.assertTrue(fiveService.findById(1L).get().getName().equals("Formacja A"));
    }

    @Test
    void save() {
        fiveService.save(Five.builder().name("Formacja XYZ").team(teamService.findById(1L).orElse(null)).build());

        Assertions.assertTrue(fiveService.findById(9L).get().getName().equals("Formacja XYZ"));
    }

    @Test
    void update() {
        Five five = fiveService.findById(1L).orElse(null);
        five.setName("Formacja XYZ");
        fiveService.update(five);

        Assertions.assertTrue(fiveService.findById(1L).get().getName().equals("Formacja XYZ"));
    }

    @Test
    void deleteById() {
        fiveService.deleteById(1L);

        Assertions.assertFalse(fiveService.existsById(1L));
    }

    @Test
    void existsById() {
        Assertions.assertTrue(fiveService.existsById(1L));
    }

    @Test
    void findByTeamId() {
        List<Five> fives = fiveService.findByTeamId(1L);

        Assertions.assertTrue(fives.size() == 4);
    }
}