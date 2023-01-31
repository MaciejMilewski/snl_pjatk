package pl.edu.pjwstk.snl.schedule;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.strategy.Strategy;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() { return this.scheduleRepository.findAll(); }

    public Optional<Schedule> findById(long id) {
        return this.scheduleRepository.findById(id);
    }

    public Optional<Schedule> findByTeamId(long teamId) {
        return this.scheduleRepository.findByTeamId(teamId);
    }

    public void save(Schedule schedule) { this.scheduleRepository.save(schedule); }

    public void deleteById(long id) {
        this.scheduleRepository.deleteById(id);
    }

    public boolean existsById(long id) { return this.scheduleRepository.existsById(id); }
}
