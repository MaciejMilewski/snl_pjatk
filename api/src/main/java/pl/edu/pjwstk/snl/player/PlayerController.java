package pl.edu.pjwstk.snl.player;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pjwstk.snl.news.News;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;
import pl.edu.pjwstk.snl.util.PageResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping(value = "/players")
    @Operation(description = "Usługa do pobierania wszystkich użytkowników", summary = "Pobieranie użytkowników")
    public ResponseEntity<Map<String, Object>> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Player> players;
            Page<Player> pagePlayers = playerService.findAll(paging);
            players = pagePlayers.getContent();

            if (players.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new PageResponse<Player>().getPageResponseMap(pagePlayers);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long id){
        Optional<Player> player = playerService.findById(id);
        return player.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/teams/{id}/players")
    public ResponseEntity<List<Player>> getPlayersByTeamId(@PathVariable("id") Long id){
        if (!teamService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Player> players = playerService.findPlayersByTeamsId(id);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping(value = "/players/{id}/teams")
    public ResponseEntity<List<Team>> getTeamsByPlayerId(@PathVariable("id") Long id){
        if (!playerService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Team> teams = teamService.findTeamsByPlayersId(id);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping(value = "/teams/{id}/players")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createPlayer(
            @PathVariable(value = "id") Long teamId,
            @RequestParam Optional<Long> id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer playerNumber,
            @RequestParam Player.Position position,
            @RequestParam String birthDate,
            @RequestParam String birthPlace,
            @RequestParam Integer weight,
            @RequestParam Player.Stick stick,
            @RequestParam Optional<MultipartFile> image
    ) throws IOException {
        // check if team exists
        if (teamService.existsById(teamId)) {
            Team team = teamService.findById(teamId).orElse(null);
            // if player id is provided
            if (id.isPresent()) {
                // if player already exists in db
                if (playerService.existsById(id.get())) {
                    Player player = playerService.findById(id.get()).orElse(null);
                    team.addPlayer(player);
                    teamService.update(team);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            // new player with image
            if (image.isPresent()) {
                Player newPlayer = new Player(
                        name,
                        surname,
                        playerNumber,
                        position,
                        LocalDate.parse(birthDate),
                        birthPlace,
                        weight,
                        stick,
                        image.get().getBytes()
                );
                team.addPlayer(newPlayer);
                playerService.save(newPlayer);
            } else {
            // new player no image
                Player newPlayer = new Player(
                        name,
                        surname,
                        playerNumber,
                        position,
                        LocalDate.parse(birthDate),
                        birthPlace,
                        weight,
                        stick,
                        null
                );
                team.addPlayer(newPlayer);
                playerService.save(newPlayer);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/players/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removePlayerById(@PathVariable("id") Long id){
        try {
            playerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/players/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Player> updatePlayer(
            @PathVariable("id") Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer playerNumber,
            @RequestParam Player.Position position,
            @RequestParam String birthDate,
            @RequestParam String birthPlace,
            @RequestParam Integer weight,
            @RequestParam Player.Stick stick,
            @RequestParam MultipartFile image,
            @RequestParam Long teamId
    ) throws IOException {
        Optional<Player> playerData = playerService.findById(id);

        if (playerData.isPresent()) {
            Player playerDao = playerData.get();

            playerDao.setName(name);
            playerDao.setSurname(surname);
            playerDao.setPlayerNumber(playerNumber);
            playerDao.setPosition(position);
            playerDao.setBirthDate(LocalDate.parse(birthDate));
            playerDao.setBirthplace(birthPlace);
            playerDao.setWeight(weight);
            playerDao.setStick(stick);
            playerDao.setImage(image.getBytes());

            List<Team> teams = new ArrayList<>(playerDao.getTeams());
            for (Iterator<Team> iterator = teams.iterator(); iterator.hasNext();) {
                Team team = iterator.next();
                team.removePlayer(id);
                this.teamService.update(team);
            }

            playerService.update(playerDao);

            Team team = this.teamService.findById(teamId).orElse(null);
            team.addPlayer(playerDao);
            teamService.update(team);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
