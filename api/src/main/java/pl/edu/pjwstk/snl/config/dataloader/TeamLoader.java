package pl.edu.pjwstk.snl.config.dataloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.league.LeagueService;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.player.PlayerService;
import pl.edu.pjwstk.snl.team.Team;

import java.util.List;
import java.util.stream.Collectors;

import pl.edu.pjwstk.snl.team.TeamService;

import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

@Component()
@Order(98)
@RequiredArgsConstructor
public class TeamLoader implements ApplicationRunner {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final ResourceLoader resourceLoader;
    private final LeagueService leagueService;

    @Transactional
    public void run(ApplicationArguments args) throws IOException {

        Team goldwellTeam = new Team("Goldwell", getTeamImage("goldwell.png"));
        Team nadDogsTeam = new Team("Mad Dogs", getTeamImage("maddogs.png"));
        Team dragonsTeam = new Team("Dragons", getTeamImage("dragons.png"));
        Team ohtTeams = new Team("OHT", getTeamImage("oliwa.png"));

        goldwellTeam.addLeague(leagueService.findById(1).orElse(null));
        nadDogsTeam.addLeague(leagueService.findById(1).orElse(null));
        dragonsTeam.addLeague(leagueService.findById(1).orElse(null));
        ohtTeams.addLeague(leagueService.findById(1).orElse(null));

        List<Team> teams = List.of(
                goldwellTeam,
                nadDogsTeam,
                dragonsTeam,
                ohtTeams
        );
        teamService.saveAll(teams);

        List<Player> players = playerService.findAllNoPaging();
        List<Player> goldwellPlayers = players.stream().filter(p -> p.getId() < 23).limit(22).collect(Collectors.toList());
        List<Player> dragonsPlayers = players.stream().filter(p -> p.getId() >= 23).limit(22).collect(Collectors.toList());
        List<Player> ohtPlayers = players.stream().filter(p -> p.getId() >= 45).limit(22).collect(Collectors.toList());
        List<Player> maddogsPlayers = players.stream().filter(p -> p.getId() >= 67).limit(22).collect(Collectors.toList());
        addPlayersToTeam("Goldwell", goldwellPlayers);
        addPlayersToTeam("Dragons", dragonsPlayers);
        addPlayersToTeam("OHT", ohtPlayers);
        addPlayersToTeam("Mad Dogs", maddogsPlayers);

    }

    public void addPlayersToTeam(String teamName, List<Player> players) {
        Team team = this.teamService.findByName(teamName).orElse(null);
        for (Player p : players) {
            team.addPlayer(p);
            teamService.update(team);
        }
    }

    private byte[] getTeamImage(String imageName) throws IOException {
        try {
            Path path = Paths.get("/maciek/lib/resources/pl/edu/pjwstk/snl/config/dataloader/team/" + imageName);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            return null;
        }
    }
}
