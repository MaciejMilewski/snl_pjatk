package pl.edu.pjwstk.snl.match.action;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.snl.match.Match;

@Repository

public interface PenaltyRepository extends JpaRepository<Penalty, Long>{
    public List<Penalty> findByTeamId(long id);

    public List<Penalty> findByMatch(Match match);
}
