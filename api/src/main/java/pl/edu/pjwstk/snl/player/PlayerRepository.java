package pl.edu.pjwstk.snl.player;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByName(String name);

    boolean existsBySurname(String surname);

    List<Player> findByName(String name);
    Optional<Player> findByNameAndSurname(String name, String surname);

    List<Player> findByPlayerNumber(Integer playerNumber);

    List<Player> findPlayersByTeamsId(Long id);
}
