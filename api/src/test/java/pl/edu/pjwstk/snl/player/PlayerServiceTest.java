package pl.edu.pjwstk.snl.player;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    void findAll() {
        Pageable paging = PageRequest.of(0, 10);
        Page<Player> pagePlayers = playerService.findAll(paging);
        List<Player> players = pagePlayers.getContent();

        String name = players.get(0).getName();

        Assertions.assertTrue(name.equals("Tomasz"));
        Assertions.assertTrue(players.size() == 10);
    }

    @Test
    void findAllNoPaging() {
        List<Player> players = playerService.findAllNoPaging();

        Assertions.assertTrue(players.size() == 89);
    }

    @Test
    void findById() {
        Assertions.assertTrue(playerService.findById(1L).get().getName().equals("Tomasz"));
    }

    @Test
    void save() {
        playerService.save(
                Player.builder()
                .name("Krzesimir")
                .build());

        Assertions.assertTrue(playerService.findById(90L).get().getName().equals("Krzesimir"));
    }

    @Test
    void findByNameAndSurname() {
        Assertions.assertTrue(playerService.findByNameAndSurname("Tomasz", "Nowak").get().getId() == 1L);
    }

    @Test
    void update() {
        playerService.update(
                Player.builder()
                .id(1L)
                .name("Jacek")
                .build()
        );

        Assertions.assertTrue(playerService.findById(1L).get().getName().equals("Jacek"));
    }

    @Test
    void deleteById() {
        playerService.deleteById(1L);

        Assertions.assertFalse(playerService.existsById(1L));
    }

    @Test
    void existsById() {
        Assertions.assertTrue(playerService.existsById(1L));
    }

    @Test
    void findPlayersByTeamsId() {
        List<Player> players = playerService.findPlayersByTeamsId(1L);

        Assertions.assertTrue(players.size() == 22);
    }

    @Test
    void saveAll() {
        List<Player> players = new ArrayList<>();
        players.add(Player.builder().name("A").build());
        players.add(Player.builder().name("B").build());

        playerService.saveAll(players);

        Assertions.assertTrue(playerService.existsById(90L));
        Assertions.assertTrue(playerService.existsById(91L));
    }
}