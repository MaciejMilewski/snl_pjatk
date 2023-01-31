package pl.edu.pjwstk.snl.sponsors;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SponsorService {
    private final SponsorRepository sponsorRepository;

    public List<Sponsor> findAll() {
        return this.sponsorRepository.findAll();
    }

    public Optional<Sponsor> findById(long id) {
        return this.sponsorRepository.findById(id);
    }

    public Sponsor save(Sponsor sponsor) {
        return this.sponsorRepository.save(sponsor);
    }

    public void deleteById(long id) {
        this.sponsorRepository.deleteById(id);
    }

    public List saveAll(List<Sponsor> sponsors) {
        return sponsorRepository.saveAll(sponsors);
    }
}
