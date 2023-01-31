package pl.edu.pjwstk.snl.match;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.snl.team.Team;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    public List<Match> findByHomeTeam(Team team);
    public List<Match> findByVisitingTeam(Team team);
    public Optional<Match> findFirstByStartTime(LocalDateTime startTime);

}

