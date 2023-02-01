package pl.edu.pjwstk.snl.strategy;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.team.TeamService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StrategyServiceTest {

    @Mock
    private StrategyRepository strategyRepository;

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private TeamService teamService;

    @Test
    void findAll() {
        strategyService.save(Strategy.builder()
                .name("Super Atak 1")
                .team(teamService.findById(1L).orElse(null))
                .build());
        strategyService.save(Strategy.builder().name("Super Atak 2")
                .team(teamService.findById(1L).orElse(null))
                .build());

        List<Strategy> strategies = strategyService.findAll();

        Assertions.assertTrue(strategies.size() == 2);
    }

    @Test
    void findById() {
        strategyService.save(Strategy.builder()
                .name("Super Atak 1")
                .team(teamService.findById(1L).orElse(null))
                .build());

        Assertions.assertTrue(strategyService.findById(1L).orElse(null).getName().equals("Super Atak 1"));
    }

    @Test
    void save() {
        strategyService.save(Strategy.builder()
                .name("Super Atak 1")
                .team(teamService.findById(1L).orElse(null))
                .build());

        Assertions.assertTrue(strategyService.existsByName("Super Atak 1"));
    }

    @Test
    void deleteById() {
        strategyService.save(Strategy.builder()
                .name("Super Atak 1")
                .team(teamService.findById(1L).orElse(null))
                .build());

        strategyService.deleteById(1L);

        Assertions.assertFalse(strategyService.existsById(1L));
    }

    @Test
    void existsById() {
        Assertions.assertFalse(strategyService.existsById(1L));

        strategyService.save(Strategy.builder()
                .name("Super Atak 1")
                .team(teamService.findById(1L).orElse(null))
                .build());

        Assertions.assertTrue(strategyService.existsById(1L));
    }

    @Test
    void findByTeamId() {
        strategyService.save(Strategy.builder()
                .name("Super Atak 1")
                .team(teamService.findById(1L).orElse(null))
                .build());

        Assertions.assertTrue(
                strategyService.findByTeamId(1L).get(0)
                        .getName().equals("Super Atak 1")
        );
    }

    @Test
    void deleteByTeamId() {
        strategyService.save(Strategy.builder()
                .name("Super Atak 1")
                .team(teamService.findById(1L).orElse(null))
                .build());

        strategyService.deleteByTeamId(1L);

        Assertions.assertTrue(strategyRepository.findByTeamId(1L).size() == 0);
    }
}