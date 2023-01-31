package pl.edu.pjwstk.snl.schedule;

import io.micrometer.core.instrument.internal.Mergeable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TeamService teamService;

    @Test
    void findAll() {
        List<Schedule> schedules = scheduleService.findAll();

        Assertions.assertTrue(schedules.size() == 2);
    }

    @Test
    void findById() {
        Assertions.assertTrue(
                scheduleService.findById(1L)
                        .orElse(null)
                        .getDescription()
                        .equals("Plan spotkań na 2023")
        );
    }

    @Test
    void findByTeamId() {
        Assertions.assertTrue(
                scheduleService.findByTeamId(1L)
                        .orElse(null)
                        .getDescription()
                        .equals("Plan spotkań na 2023")
        );
    }

    @Test
    void save() {
        Assertions.assertTrue(scheduleService.existsById(1L));
        Assertions.assertTrue(scheduleService.existsById(1L));
    }

    @Test
    void deleteById() {
        scheduleService.deleteById(1L);
        Assertions.assertFalse(scheduleService.existsById(1L));
    }

    @Test
    void existsById() {
        Assertions.assertTrue(scheduleService.existsById(1L));
    }
}