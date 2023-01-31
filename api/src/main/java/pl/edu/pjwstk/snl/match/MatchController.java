package pl.edu.pjwstk.snl.match;


import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.snl.match.action.*;
import pl.edu.pjwstk.snl.match.dto.*;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.ActionAggregator;
import pl.edu.pjwstk.snl.match.dto.UserMatchesByStatusDTO;
import pl.edu.pjwstk.snl.match_user.Comment.Comment;
import pl.edu.pjwstk.snl.util.PageResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final GoalService goalService;


    @GetMapping("/matches")
    public ResponseEntity<Map<String, Object>> getWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable paging = PageRequest.of(page, size);

        Page<Match> pages = matchService.findAllWithPagination(paging);
        List<Match> matches = pages.getContent();

        if (matches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> response = new PageResponse<Match>().getPageResponseMap(pages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/matches/{matchId}")
    public ResponseEntity<Match> getMatch(@PathVariable("matchId") Long matchId) {
        Optional<Match> match = matchService.findById(matchId);
        return match.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{userId}/matches")
    public ResponseEntity<UserMatchesByStatusDTO> getMatchesForUser(
            @PathVariable("userId") Long userId
    ) {
        try {
            UserMatchesByStatusDTO matches = this.matchService.getMatchesByUserId(userId);
            return new ResponseEntity<>(matches, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/matches/info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> createMatchInfo(
            @RequestBody CreateMatchInfoDto createMatchInfoDto) {

        matchService.insertMatchInfo(createMatchInfoDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/matches/{id}/info")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<String> updateMatchInfo(
            @PathVariable("id") Long id,
            @RequestBody MatchInfo matchInfo) {
        try {
            if (matchService.existsById(id)) {
                Match save = matchService.updateMatchInfo(id, matchInfo);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/matches/{id}/status")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN') or hasRole('ROLE_MEDIC_OR_JUDGE')")
    public ResponseEntity<String> updateMatchStatus(
            @PathVariable("id") Long id,
            @RequestParam MatchStatus status) {
        try {
            matchService.updateMatchStatus(id, status);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/matches/{id}/status/acceptance")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN') or hasRole('ROLE_MEDIC_OR_JUDGE')")
    public ResponseEntity<String> updateMatchStatus(
            @PathVariable("id") Long matchId,
            @RequestParam Optional<MatchStatus> status,
            @RequestParam Long userId,
            @RequestParam boolean isAccepted) {
        try {
            if (status.isPresent()) {
                matchService.updateMatchStatusAndChangeUserAcceptance(matchId, status.get(), userId, isAccepted);
            } else {
                matchService.updateMatchStatusAndChangeUserAcceptance(matchId, null, userId, isAccepted);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/matches/{matchId}/{teamId}/home_team_players")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> addHomeTeamPlayers(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody List<MatchPlayerPositionDto> matchPlayerPositions) {
        matchService.addHomeTeamPlayers(matchId, teamId, matchPlayerPositions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/matches/team/{teamId}")
    public ResponseEntity<List<Match>> getMatchbyTeamId(@PathVariable("teamId") Long teamId) {
        return new ResponseEntity(matchService.getMatchesByTeamId(teamId), HttpStatus.OK);

    }

    @PostMapping("/matches/{matchId}/{teamId}/visiting_team_players")
    // admin, sekretarz,trener
    public ResponseEntity<HttpStatus> addVisitingTeamPlayers(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody List<MatchPlayerPositionDto> matchPlayerPositions) {
        matchService.addVisitingTeamPlayers(matchId, teamId, matchPlayerPositions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/matches/{matchId}/teams/{teamId}")
    public ResponseEntity<List<MatchPlayer>> getTeamLineupForMatch(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody List<MatchPlayerPositionDto> matchPlayerPositions) {
        try {
            List<MatchPlayer> lineupPLayers = matchService.getTeamLineupForMatch(matchId, teamId);
            return new ResponseEntity<>(lineupPLayers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/matches/{matchId}/goals")
    public ResponseEntity<List<Goal>> getGoals(@PathVariable("matchId") Long matchId) {

        List<Goal> goals = goalService.findByMatch(matchService.findById(matchId).get());
        // matchService.addGoal(matchId, teamId, goal);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @GetMapping("/matches/{matchId}/actions")
    public ResponseEntity<ActionAggregator> getActions(@PathVariable("matchId") Long matchId) {

        ActionAggregator actions = matchService.findActionsByMatch(matchService.findById(matchId).get());
        // matchService.addGoal(matchId, teamId, goal);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @GetMapping("/matches/upcoming")
    public ResponseEntity<Match> getUpcomingMatch() {

        Match match = matchService.findNearestUpcomingMatch();
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @PostMapping("/matches/{matchId}/{teamId}/goals")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> addGoals(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody GoalCreationDTO goalCreation) {
        matchService.addGoal(matchId, teamId, goalCreation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/matches/goals/{goalId}")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<String> removeGoalById(@PathVariable("goalId") Long goalId) {
        try {
            matchService.deleteGoal(goalId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/matches/{matchId}/score")
    public ResponseEntity<ScoreContainer> getScoreByMatchId(@PathVariable("matchId") Long matchId) {
        return new ResponseEntity(matchService.getScore(matchId), HttpStatus.OK);

    }
    @GetMapping("/matches/{matchId}/penaltiesTable")
    public ResponseEntity<PenaltiesTable> getPenaltiesTableByMatchId(@PathVariable("matchId") Long matchId) {
        return new ResponseEntity(matchService.getPenaltiesTable(matchId), HttpStatus.OK);

    }

    @PostMapping("/matches/{matchId}/{teamId}/breakes")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> addBreakes(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody BreakeTimeCreationDTO breakeTimeCreation) {
        matchService.addBreakes(matchId, teamId, breakeTimeCreation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/matches/{matchId}/{teamId}/penalties")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> addPenalties(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody PenaltyCreationDTO penaltyCreation) {
        matchService.addPenalties(matchId, teamId, penaltyCreation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/matches/penalties/{penaltyId}")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removePenaltyById(@PathVariable("penaltyId") Long penaltyId) {
        try {
            matchService.deletePenalty(penaltyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/matches/{matchId}/{teamId}/changeGolkipers")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> addChangeGolkipers(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody ChangeGolkiperCreationDTO changeGolkiperCreation) {
        matchService.addChangeGolkipers(matchId, teamId, changeGolkiperCreation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/matches/changeGolkipers/{changeGolkipersId}")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removechangeGolkipersById(@PathVariable("changeGolkipersId") Long changeGolkipersId) {
        try {
            matchService.deleteChangeGolkiper(changeGolkipersId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/matches/{matchId}/{teamId}/penaltyShotMissed")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> addPenaltyShotMissed(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody PenaltyShotMissedCreationDTO penaltyShotMissedCreation) {
        matchService.addPenaltyShotMissed(matchId, teamId, penaltyShotMissedCreation);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/matches/{matchId}/{teamId}/penaltyShot")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> addPenaltyShot(@PathVariable("matchId") Long matchId, @PathVariable("teamId") Long teamId, @RequestBody PenaltyShotCreationDTO penaltyShotCreation) {
        matchService.addPenaltyShot(matchId, teamId, penaltyShotCreation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/matches/penaltyShotMissed/{penaltyShotMissedId}")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removePenaltyShotMissedtyById(@PathVariable("penaltyShotMissedId") Long penaltyShotMissedId) {
        try {
            matchService.deletePenaltyShotMissed(penaltyShotMissedId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(value = "/matches/penaltyShot/{penaltyShotId}")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removePenaltyShottyById(@PathVariable("penaltyShotId") Long penaltyShotId) {
        try {
            matchService.deletePenaltyShot(penaltyShotId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/matches/changeGolkiper/{changeGolkiperId}")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removeChangeGolkiperById(@PathVariable("changeGolkiperId") Long changeGolkiperId) {
        try {
            matchService.deleteChangeGolkiper(changeGolkiperId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/matches/breakeTime/{breakeTimeId}")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removeBreakeTimeById(@PathVariable("breakeTimeId") Long breakeTimeId) {
        try {
            matchService.deleteBreakeTime(breakeTimeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/matches/{matchId}/comments")
    @PreAuthorize("hasRole('SECRETARY') or hasRole('ROLE_MEDIC_OR_JUDGE')")
    public ResponseEntity<HttpStatus> addComment(
            @PathVariable("matchId") Long matchId,
            @RequestParam String message,
            @RequestParam Optional<String> createdAt,
            @RequestParam Long userId)
    {
        try {
            if(createdAt.isPresent()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(createdAt.get(), formatter);
                matchService.addComment(matchId, userId, message, dateTime);
            } else {
                matchService.addComment(matchId, userId, message, LocalDateTime.now());
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/matches/{matchId}/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Comment>> getAllCommentsForMatch(@PathVariable("matchId") Long matchId) {
        List<Comment> comments = matchService.findAllCommentsByMatchId(matchId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
