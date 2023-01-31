package pl.edu.pjwstk.snl.schedule;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository  extends JpaRepository<Schedule, Long> {
    boolean existsByDescription(String description);

    Optional<Schedule> findByTeamId(Long teamId);

    @Transactional
    void deleteByTeamId(long teamId);
}
