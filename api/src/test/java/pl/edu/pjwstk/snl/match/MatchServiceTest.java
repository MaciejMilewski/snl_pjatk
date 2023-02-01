package pl.edu.pjwstk.snl.match;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.ActionAggregator;
import pl.edu.pjwstk.snl.match.dto.CreateMatchInfoDto;
import pl.edu.pjwstk.snl.match.dto.ScoreContainer;
import pl.edu.pjwstk.snl.match.dto.UserMatchesByStatusDTO;
import pl.edu.pjwstk.snl.match_user.Comment.Comment;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    void findAllWithPagination() {
        Pageable paging = PageRequest.of(0, 3);
        Page<Match> pageMatches = matchService.findAllWithPagination(paging);
        List<Match> matches = pageMatches.getContent();

        String place = matches.get(0).getPlace();

        Assertions.assertTrue(place.equals("Gdańsk"));
        Assertions.assertTrue(matches.size() == 3);
    }

    @Test
    void findAll() {
        List<Match> matches = matchService.findAll();

        Assertions.assertTrue(matches.size() == 3);
    }

    @Test
    void findById() {
        Assertions.assertTrue(matchService.findById(1L).get().getPlace().equals("Gdańsk"));
    }

    @Test
    void saveAll() {
        List<Match> matches = new ArrayList<>();
        matches.add(Match.builder().spectator("Jan Kowalski").build());
        matches.add(Match.builder().spectator("Stefan Kołacz").build());

        matchService.saveAll(matches);

        Assertions.assertTrue(matchService.existsById(4L));
        Assertions.assertTrue(matchService.existsById(5L));
    }

    @Test
    void save() {
        matchService.save(Match.builder().spectator("Jan Kowalski").build());

        Assertions.assertTrue(matchService.existsById(4L));
    }

    @Test
    void existsById() {
        Assertions.assertTrue(matchService.existsById(1L));
    }

    @Test
    void getScore() {
        ScoreContainer scoreContainer = matchService.getScore(1L);

        assertEquals("0:3", scoreContainer.getRegular().getFinalScore());
    }

    @Test
    void updateMatchStatus() throws Exception {
        matchService.updateMatchStatus(3L, MatchStatus.IN_PROGRESS);

        assertEquals(matchService.findById(3L).get().getStatus(), MatchStatus.IN_PROGRESS);
    }

    @Test
    void findAllCommentsByMatchId() {
        List<Comment> comments = matchService.findAllCommentsByMatchId(1L);

        assertEquals(comments.size(), 0);
    }

    @Test
    void getMatchesByUserId() throws Exception {
        UserMatchesByStatusDTO matches = matchService.getMatchesByUserId(1L);

        assertEquals(0, matches.getInProgress().size());
    }
}