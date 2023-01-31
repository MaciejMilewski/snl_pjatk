package pl.edu.pjwstk.snl.five;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.snl.match.MatchPlayer;

public interface FivePlayerRepository extends JpaRepository<FivePlayer, Long> {
    public List<FivePlayer> findByPlayerId(long id);
}
