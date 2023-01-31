package pl.edu.pjwstk.snl.match.action;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ChangeGolkiperRepository extends JpaRepository<ChangeGolkiper, Long>{
    public List<ChangeGolkiper> findByTeamId(long id);
}

