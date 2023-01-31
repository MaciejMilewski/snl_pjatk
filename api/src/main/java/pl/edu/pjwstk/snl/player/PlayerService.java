package pl.edu.pjwstk.snl.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import pl.edu.pjwstk.snl.five.FivePlayer;
import pl.edu.pjwstk.snl.five.FivePlayerRepository;
import pl.edu.pjwstk.snl.match.MatchLineup;
import pl.edu.pjwstk.snl.match.MatchLineupRepository;
import pl.edu.pjwstk.snl.match.MatchPlayer;
import pl.edu.pjwstk.snl.match.MatchPlayerRepository;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamRepository;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final MatchPlayerRepository matchPlayerRepository;
    private final MatchLineupRepository matchLineupRepository;
    private final FivePlayerRepository fivePlayerRepository;

    public Page<Player> findAll(Pageable paging) {
        return this.playerRepository.findAll(paging);
    }

    public List<Player> findAllNoPaging() {
        return this.playerRepository.findAll();
    }

    public Optional<Player> findById(long id) {
        return this.playerRepository.findById(id);
    }

    public void save(Player player) {
        if (this.playerRepository.existsByName(player.getName()) && this.playerRepository.existsBySurname(player.getSurname())) {
            throw new RuntimeException("Player already exists");
        }
        this.playerRepository.save(player);
    }

    public Optional<Player> findByNameAndSurname(String name, String surname) {
        return this.playerRepository.findByNameAndSurname(name, surname);
    }

    public void update(Player player) {
        this.playerRepository.save(player);
    }

    // TODO: add five deleting
    public void deleteById(long id) {
        if (this.playerRepository.existsById(id)) {
            Player player = this.playerRepository.findById(id).orElse(null);
            List<Team> teams = new ArrayList<>(player.getTeams());
            List<MatchPlayer> matchPlayers = this.matchPlayerRepository.findByPlayerId(id);
            List<FivePlayer> fivePlayers = this.fivePlayerRepository.findByPlayerId(id);

            for (MatchPlayer matchPlayer : matchPlayers) {
                matchPlayer.setPlayer(null);
            }

            for (FivePlayer fivePlayer : fivePlayers) {
                fivePlayer.setPlayer(null);
            }

            for (Iterator<Team> iterator = teams.iterator(); iterator.hasNext();) {
                Team team = iterator.next();
                team.removePlayer(id);
                this.teamRepository.save(team);
            }

            this.playerRepository.deleteById(id);
        }
    }

    public boolean existsById(Long id) {
        return this.playerRepository.existsById(id);
    }

    public List<Player> findPlayersByTeamsId(Long id) {
        return this.playerRepository.findPlayersByTeamsId(id);
    }

    public List<Player> saveAll(List<Player> players) {
        return playerRepository.saveAll(players);
    }
}
