package pl.edu.pjwstk.snl.strategy;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StrategyRepository extends JpaRepository<Strategy, Long> {
    boolean existsByName(String name);

    List<Strategy> findByTeamId(Long teamId);

    @Transactional
    void deleteByTeamId(long teamId);
}
