package pl.edu.pjwstk.snl.league;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueService leagueService;

    private final TeamService teamService;

    @GetMapping("/leagues")
    public ResponseEntity<List<League>> getAllLeagues() {
        try {
            if (CollectionUtils.isNotEmpty(leagueService.findAll())) {
                return new ResponseEntity<>(leagueService.findAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/leagues/{id}")
    public ResponseEntity<League> getLeagueById(@PathVariable("id") Long id){
        Optional<League> league = leagueService.findById(id);
        return league.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/teams/{id}/leagues")
    public ResponseEntity<List<League>> getLeaguesByTeamId(@PathVariable("id") Long id){
        if (!teamService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<League> leagues = leagueService.findLeaguesByTeamsId(id);
        return new ResponseEntity<>(leagues, HttpStatus.OK);
    }

    @GetMapping(value = "/leagues/{id}/teams")
    public ResponseEntity<List<Team>> getTeamsByLeagueId(@PathVariable("id") Long id){
        if (!leagueService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Team> teams = teamService.findTeamsByLeaguesId(id);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @DeleteMapping(value = "/leagues/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removeLeagueById(@PathVariable("id") Long id){
        try {
            leagueService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/leagues")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> createLeague(
            @RequestParam String name
    ) {
        try {
            leagueService.save(new League(name));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/leagues/{id}/teams")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createTeamAndAssignToLeague(
            @PathVariable(value = "id") Long leagueId,
            @RequestParam Optional<Long> id,
            @RequestParam String name,
            @RequestParam Optional<MultipartFile> image
    ) throws IOException {
        // check if league exists
        if (leagueService.existsById(leagueId)) {
            League league = leagueService.findById(leagueId).orElse(null);
            // if team id is provided
            if (id.isPresent()) {
                // if team already exists in db
                if (teamService.existsById(id.get())) {
                    Team team = teamService.findById(id.get()).orElse(null);
                    team.addLeague(leagueService.findById(leagueId).orElse(null));
                    teamService.update(team);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }
            }
            // new team with image
            if (image.isPresent()) {
                Team newTeam = new Team(
                        name,
                        image.get().getBytes()
                );
                newTeam.addLeague(leagueService.findById(leagueId).orElse(null));
                teamService.save(newTeam);
            } else {
                // new player no image
                Team newTeam = new Team(
                        name,
                        null
                );
                newTeam.addLeague(leagueService.findById(leagueId).orElse(null));
                teamService.save(newTeam);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/leagues/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> updateLeague(
            @PathVariable("id") Long id,
            @RequestParam String name
    ) {
        Optional<League> leagueData = leagueService.findById(id);

        if (leagueData.isPresent()) {
            League LeagueDao = leagueData.get();
            LeagueDao.setName(name);
            leagueService.update(LeagueDao);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
