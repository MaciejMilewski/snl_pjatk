package pl.edu.pjwstk.snl.five;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiveRepository extends JpaRepository<Five, Long> {
    @Transactional
    List<Five> findByTeamId(Long teamId);

    boolean existsByName(String name);
}
