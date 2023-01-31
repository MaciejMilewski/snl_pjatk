package pl.edu.pjwstk.snl.match;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchPlayerRepository  extends JpaRepository<MatchPlayer, Long> {

    public List<MatchPlayer> findByPlayerId(long id);
}
