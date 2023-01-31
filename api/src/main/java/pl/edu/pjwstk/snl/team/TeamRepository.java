package pl.edu.pjwstk.snl.team;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
    Optional<Team> findByName(String name);

    List<Team> findTeamsByPlayersId(Long id);

    List<Team> findTeamsByLeaguesId(Long id);
}
