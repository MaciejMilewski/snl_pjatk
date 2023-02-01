package pl.edu.pjwstk.snl.league;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.player.Player;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LeagueServiceTest {

    @Autowired
    private LeagueService leagueService;

    @Test
    void findAll() {
        List<League> leagues = leagueService.findAll();

        Assertions.assertTrue(leagues.size() == 2);
    }

    @Test
    void findById() {
        Assertions.assertTrue(leagueService.findById(1L).get().getName().equals("TLH"));
    }

    @Test
    void save() {
        leagueService.save(League.builder().name("PMKS").build());

        Assertions.assertTrue(leagueService.existsById(3L));
    }

    @Test
    void update() {
        leagueService.update(League.builder().id(1L).name("TLH2").build());

        Assertions.assertTrue(leagueService.findById(1L).get().getName().equals("TLH2"));
    }

    @Test
    void deleteById() {
        leagueService.deleteById(1L);

        Assertions.assertFalse(leagueService.existsById(1L));
    }

    @Test
    void existsById() {
        Assertions.assertTrue(leagueService.existsById(1L));
        Assertions.assertFalse(leagueService.existsById(3L));
    }

    @Test
    void findLeaguesByTeamsId() {
        List<League> leagues = leagueService.findLeaguesByTeamsId(1L);

        Assertions.assertTrue(leagues.get(0).getName().equals("TLH"));
    }

    @Test
    void saveAll() {
        League league1 = League.builder().name("A").build();
        League league2 = League.builder().name("B").build();

        List<League> leagues = new ArrayList<>();

        leagues.add(league1);
        leagues.add(league2);

        leagueService.saveAll(leagues);

        Assertions.assertTrue(leagueService.findAll().size() == 4);
    }
}