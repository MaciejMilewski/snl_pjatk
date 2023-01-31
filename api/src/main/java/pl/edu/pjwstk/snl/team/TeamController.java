package pl.edu.pjwstk.snl.team;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import pl.edu.pjwstk.snl.util.PageResponse;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping(value = "/teams")
    public ResponseEntity<Map<String, Object>> getAllTeams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Team> teams;
            Page<Team> pageTeams = teamService.findAll(paging);;
            teams = pageTeams.getContent();

            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new PageResponse<Team>().getPageResponseMap(pageTeams);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/teams")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createTeam(
            @RequestParam String name,
            @RequestParam MultipartFile image
    ){
        try {
            teamService.save(new Team(
                    name,
                    image.getBytes()
            ));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") Long id){
        Optional<Team> team = teamService.findById(id);
        return team.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/teams/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> updateTeam(
            @PathVariable("id") Long id,
            @RequestParam String name,
            @RequestParam MultipartFile image
    ) throws IOException {
        Optional<Team> teamData = teamService.findById(id);

        if (teamData.isPresent()) {
            Team teamDao = teamData.get();
            teamDao.setName(name);
            teamDao.setImage(image.getBytes());
            teamService.update(teamDao);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/teams/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removeTeam(@PathVariable("id") Long id){
        try {
            teamService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
