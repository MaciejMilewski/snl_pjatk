package pl.edu.pjwstk.snl.league;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamRepository;

@Service
@RequiredArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;

    public List<League> findAll() {
        return this.leagueRepository.findAll();
    }

    public Optional<League> findById(long id) {
        return this.leagueRepository.findById(id);
    }

    public void save(League league) {
        if (this.leagueRepository.existsByName(league.getName())) {
            throw new RuntimeException("League already exists");
        }
        this.leagueRepository.save(league);
    }

    public void update(League league) {
        this.leagueRepository.save(league);
    }

    @Transactional
    public void deleteById(long id) {
        if (this.leagueRepository.existsById(id)) {
            League league = this.leagueRepository.findById(id).orElse(null);
            Set<Team> teams = league.getTeams();

            teams.forEach((team) -> {
                team.removeLeague(id);
                this.teamRepository.save(team);
            });

            this.leagueRepository.deleteById(id);
        }
    }

    public boolean existsById(Long id) {
        return this.leagueRepository.existsById(id);
    }

    public List<League> findLeaguesByTeamsId(Long id) {
        return this.leagueRepository.findLeaguesByTeamsId(id);
    }

    public List saveAll(List<League> leagues) {
        return leagueRepository.saveAll(leagues);    }
}
