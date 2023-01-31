package pl.edu.pjwstk.snl.five;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.player.PlayerService;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;

@Service
@RequiredArgsConstructor
public class FiveService {
    private final FiveRepository fiveRepository;
    private final FivePlayerRepository fivePlayerRepository;

    public List<Five> findAll() { return this.fiveRepository.findAll(); }

    public Optional<Five> findById(long id) {
        return this.fiveRepository.findById(id);
    }

    public void save(Five five) {
        if (!this.fiveRepository.existsByName(five.getName())) {
            this.fiveRepository.save(five);
        }
    }

    public void update(Five five) { this.fiveRepository.save(five); }

    public void deleteById(long id) {
        if (this.fiveRepository.existsById(id)) {
            Five five = this.fiveRepository.findById(id).orElse(null);
            List<FivePlayer> players = new ArrayList<>(five.getPlayers());

            for (Iterator<FivePlayer> iterator = players.iterator(); iterator.hasNext();) {
                FivePlayer player = iterator.next();
                player.removeFive(id);
                this.fivePlayerRepository.save(player);
            }

            this.fiveRepository.deleteById(id);
        }
    }

    @SneakyThrows
    public void createFiveAndAssignPlayersFromDTOsToFive(long teamId, FiveDTO fiveDTO, TeamService teamService, PlayerService playerService) {
        if (teamService.existsById(teamId)) {
            Team team = teamService.findById(teamId).orElse(null);

            // Create new five and assign team
            Five five = new Five(fiveDTO.getName());
            five.setTeam(teamService.findById(teamId).orElse(null));
            save(five);
            team.addFive(five);

            // Assign players
            List<FivePlayerDTO> playersDTO = fiveDTO.getPlayers();
            for (FivePlayerDTO playerDTO : playersDTO) {
                if (playerService.existsById(playerDTO.getId())) {
                    FivePlayer player = new FivePlayer();

                    player.setPlayer(playerService.findById(playerDTO.getId()).orElse(null));
                    player.setPosition(playerDTO.getPosition());
                    player.setNumber(playerDTO.getNumber());

                    player.addFive(five);
                    fivePlayerRepository.save(player);
                }
            }
        } else {
            throw new Exception("Team not found");
        }
    }

    @SneakyThrows
    public void createFiveAndAssignPlayersToFive(Team team, List<FivePlayer> players, String name, TeamService teamService, PlayerService playerService) {
        if (teamService.existsById(team.getId())) {
            Five five = new Five(name);
            five.setTeam(team);
            this.save(five);
            team.addFive(five);

            for (FivePlayer fivePlayer : players) {
                FivePlayer player = new FivePlayer();

                player.setPlayer(playerService.findById(fivePlayer.getPlayer().getId()).orElse(null));
                player.setPosition(fivePlayer.getPosition());
                player.setNumber(fivePlayer.getNumber());

                player.addFive(five);
                fivePlayerRepository.save(player);
            }
        } else {
            throw new Exception("Team not found");
        }
    }

    public boolean existsById(long id) { return this.fiveRepository.existsById(id); }

    public List<Five> findByTeamId(long teamId) { return this.fiveRepository.findByTeamId(teamId); }

    public List<Five> saveAll(List<Five> fives) {
        return fiveRepository.saveAll(fives);
    }

    }
