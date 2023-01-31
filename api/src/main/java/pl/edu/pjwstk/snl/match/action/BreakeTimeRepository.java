
package pl.edu.pjwstk.snl.match.action;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BreakeTimeRepository extends JpaRepository<BreakeTime, Long>{
    public List<BreakeTime> findByTeamId(long id);
}

