package pl.edu.pjwstk.snl.schedule;

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
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final TeamService teamService;

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        try {
            if (CollectionUtils.isNotEmpty(scheduleService.findAll())) {
                return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/teams/{id}/schedule")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Void> createSchedule(
            @PathVariable(value = "id") long teamId,
            @RequestParam String description,
            @RequestParam MultipartFile image
    ){
        try {
            scheduleService.save(new Schedule(
                    description,
                    image.getBytes(),
                    teamService.findById(teamId).orElse(null)
            ));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") long id) {
        final Optional<Schedule> schedule = scheduleService.findById(id);
        return schedule.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/teams/{id}/schedule")
    public ResponseEntity<Schedule> getScheduleByTeamId(@PathVariable("id") long teamId) {
        final Optional<Schedule> schedule = scheduleService.findByTeamId(teamId);
        return schedule.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("/schedules/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> updateSchedule(
            @PathVariable(value = "id") long scheduleId,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam final String team
    ) throws IOException {
        if (scheduleService.existsById(scheduleId)) {
            Schedule scheduleDao = scheduleService.findById(scheduleId).orElse(null);

            scheduleDao.setDescription(description);
            scheduleDao.setImage(image.getBytes());
            scheduleDao.setTeam(teamService.findByName(team).orElse(null));

            scheduleService.save(scheduleDao);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/schedules/{id}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("id") long id) {
        try {
            scheduleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
