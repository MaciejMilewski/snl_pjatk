package pl.edu.pjwstk.snl.five;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.player.PlayerService;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FiveController {
    private final FiveService fiveService;
    private final TeamService teamService;
    private final FivePlayerRepository fivePlayerRepository;
    private final PlayerService playerService;

    @GetMapping("/fives")
    public ResponseEntity<List<Five>> getAllFives() {
        try {
            if (CollectionUtils.isNotEmpty(fiveService.findAll())) {
                return new ResponseEntity<>(fiveService.findAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/teams/{id}/fives")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<String> createFiveWithPlayers(
            @PathVariable(value = "id") long teamId,
            @RequestBody FiveDTO fiveDTO)
    {
        try {
            this.fiveService.createFiveAndAssignPlayersFromDTOsToFive(teamId, fiveDTO, teamService, playerService);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fives/{id}")
    public ResponseEntity<Five> getFiveById(@PathVariable("id") long id) {
        final Optional<Five> five = fiveService.findById(id);
        return five.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/fives/{id}/team")
    public ResponseEntity<Team> getTeamByFiveId(@PathVariable("id") long id) {
        if (fiveService.existsById(id)) {
            return  new ResponseEntity<>(fiveService.findById(id).get().getTeam(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/fives/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> updateFive(
            @PathVariable(value = "id") long fiveId,
            @RequestParam final String name
    ) {
        if (fiveService.existsById(fiveId)) {
            Five fiveData = fiveService.findById(fiveId).orElse(null);

            fiveData.setName(name);

            fiveService.save(fiveData);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/fives/{id}/players")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> updateOrAddPlayersToFive(
            @PathVariable(value = "id") long fiveId,
            @RequestParam Long fivePlayerId,
            @RequestParam Player.Position position,
            @RequestParam Integer number
    ) {
        // if five exists
        if (fiveService.existsById(fiveId)) {
            Five five = fiveService.findById(fiveId).orElse(null);

            // if player exists
            if (fivePlayerRepository.existsById(fivePlayerId)) {
                FivePlayer player = fivePlayerRepository.findById(fivePlayerId).orElse(null);
                player.setPosition(position);
                player.setNumber(number);
                player.addFive(five);
                fivePlayerRepository.save(player);

                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/fives/{fiveId}/players/{playerId}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> removePlayerFromFive(
            @PathVariable(value = "fiveId") long fiveId,
            @PathVariable(value = "playerId") long fivePlayerId
    ) {
        // if five exists
        if (fiveService.existsById(fiveId)) {
            // if player exists
            if (fivePlayerRepository.existsById(fivePlayerId)) {
                Five five = fiveService.findById(fiveId).orElse(null);
                FivePlayer player = fivePlayerRepository.findById(fivePlayerId).orElse(null);

                player.removeFive(fiveId);
                fivePlayerRepository.save(player);
                fiveService.update(five);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/fives/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> deleteFive(@PathVariable("id") long id) {
        try {
            fiveService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{id}/fives")
    public ResponseEntity<Set<Five>> getFivesByTeamId(@PathVariable(value = "id") long teamId)
    {
        try {
            if (teamService.existsById(teamId)) {
                Team team = teamService.findById(teamId).orElse(null);
                return new ResponseEntity<>(team.getFives(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
