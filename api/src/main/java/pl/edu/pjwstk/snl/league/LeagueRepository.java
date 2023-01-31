package pl.edu.pjwstk.snl.league;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
    boolean existsByName(String name);

    List<League> findLeaguesByTeamsId(Long id);
}
