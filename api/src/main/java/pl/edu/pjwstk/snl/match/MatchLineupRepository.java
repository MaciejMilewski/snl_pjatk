package pl.edu.pjwstk.snl.match;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.snl.team.Team;

@Repository
public interface MatchLineupRepository extends JpaRepository<MatchLineup, Long> {
}

