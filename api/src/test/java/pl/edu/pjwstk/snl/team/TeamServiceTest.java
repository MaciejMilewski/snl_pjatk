package pl.edu.pjwstk.snl.team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.security.Auth.AuthController;
import pl.edu.pjwstk.snl.security.WebSecurityConfig;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;
    @Autowired
    private TeamService teamService;

    @Test
    void save() {
        teamService.save(new Team("GKS Pomorze", null));

        Team savedTeam = teamService.findByName("GKS Pomorze").orElse(null);

        Assertions.assertTrue(savedTeam.getName().equals("GKS Pomorze"));
    }

    @Test
    void findAllNoPaging() {
        List<Team> teams = teamService.findAllNoPaging();

        Assertions.assertTrue(teams.size() == 4);
    }

    @Test
    void findById() {
        Team savedTeam = teamService.findById(1L).orElse(null);
        Assertions.assertTrue(savedTeam.getName().equals("Goldwell"));
    }


    @Test
    void saveAll() {
        Team A = new Team("A", null);
        Team B = new Team("B", null);

        List<Team> teams = List.of(
                A,
                B
        );

        teamService.saveAll(teams);

        Assertions.assertTrue(teamService.findByName("A").orElse(null).getName().equals("A"));
        Assertions.assertTrue(teamService.findByName("B").orElse(null).getName().equals("B"));
    }

    @Test
    void update() {
        teamService.update(Team.builder().id(1L).name("X").build());

        Assertions.assertTrue(teamService.findById(1L).orElse(null).getName().equals("X"));
    }

    @Test
    void existsById() {
        Assertions.assertTrue(teamService.existsById(1L));
    }

    @Test
    void findByName() {
        Team savedTeam = teamService.findByName("Goldwell").orElse(null);
        Assertions.assertTrue(savedTeam.getName().equals("Goldwell"));
    }

    @Test
    void findTeamsByPlayersId() {
        List<Team> teams = teamService.findTeamsByPlayersId(1L);

        Assertions.assertTrue(teams.get(0).getName().equals("Goldwell"));
    }

    @Test
    void findTeamsByLeaguesId() {
        List<Team> teams = teamService.findTeamsByLeaguesId(1L);

        Assertions.assertTrue(teams.get(0).getName().equals("Goldwell"));
    }
}