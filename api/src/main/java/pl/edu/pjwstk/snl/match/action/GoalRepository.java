package pl.edu.pjwstk.snl.match.action;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.snl.match.Match;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    public List<Goal> findByTeamId(long id);

    public List<Goal> findByMatch(Match match);
}