package pl.edu.pjwstk.snl.sponsors;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    public boolean existsByName(String name);
    @Transactional
    public void deleteByName(String name);
}
