package pl.edu.pjwstk.snl.match;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import pl.edu.pjwstk.snl.team.Team;

@Service
@RequiredArgsConstructor
public class MatchLineupService {
    private final MatchLineupRepository matchLineupRepository;

    public Page<MatchLineup> findAllWithPagination(Pageable paging) {
        return this.matchLineupRepository.findAll(paging);
    }

    public List<MatchLineup> saveAll(List<MatchLineup> matchLineups) {
        return matchLineupRepository.saveAll(matchLineups);
    }

    public MatchLineup save(MatchLineup matchLineup) {
        return matchLineupRepository.save(matchLineup);
    }

}
