package pl.edu.pjwstk.snl.strategy;

import java.io.IOException;
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
import pl.edu.pjwstk.snl.team.TeamService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StrategyController {
    private final StrategyService strategyService;

    private final TeamService teamService;

    @GetMapping("/strategies")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<List<Strategy>> getAllStrategies() {
        try {
            if (CollectionUtils.isNotEmpty(strategyService.findAll())) {
                return new ResponseEntity<>(strategyService.findAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/{id}/strategies")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<List<Strategy>> getTeamStrategies(@PathVariable(value = "id") long teamId){
        final List<Strategy> strategies= strategyService.findByTeamId(teamId);
        if (strategies.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(strategies, HttpStatus.OK);
    }

    @PostMapping("/teams/{id}/strategies")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Void> createStrategy(
            @PathVariable(value = "id") long teamId,
            @RequestParam String name,
            @RequestParam MultipartFile image
    ){
        try {
            strategyService.save(new Strategy(
                    name,
                    image.getBytes(),
                    teamService.findById(teamId).orElse(null)
            ));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/strategies/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Strategy> getStrategyById(@PathVariable("id") long id) {
        final Optional<Strategy> trainer = strategyService.findById(id);
        return trainer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("/strategies/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> updateStrategy(
            @PathVariable(value = "id") long strategyId,
            @RequestParam final String name,
            @RequestParam MultipartFile image,
            @RequestParam final String team
    ) throws IOException {
        if (strategyService.existsById(strategyId)) {
            Strategy strategyDao = strategyService.findById(strategyId).orElse(null);

            strategyDao.setName(name);
            strategyDao.setImage(image.getBytes());
            strategyDao.setTeam(teamService.findByName(team).orElse(null));

            strategyService.save(strategyDao);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/strategies/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> deleteStrategy(@PathVariable("id") long id) {
        try {
            strategyService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
