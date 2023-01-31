package pl.edu.pjwstk.snl.match.action;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.snl.match.Match;

import java.util.List;

@Repository
public interface PenaltyShotRepository extends JpaRepository<PenaltyShot, Long> {
    public List<PenaltyShot> findByTeamId(long id);
    public List<PenaltyShot> findByMatch(Match match);
}
