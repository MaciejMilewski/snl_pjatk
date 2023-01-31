package pl.edu.pjwstk.snl.config.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.league.League;
import pl.edu.pjwstk.snl.league.LeagueRepository;
import pl.edu.pjwstk.snl.league.LeagueService;
import pl.edu.pjwstk.snl.team.Team;

import java.util.List;

import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

@Component
@Order(96)
public class LeagueLoader implements ApplicationRunner {

    private final LeagueService leagueService;
    private final ResourceLoader resourceLoader;


    @Autowired
    public LeagueLoader( LeagueService leagueService, ResourceLoader resourceLoader) {
        this.leagueService = leagueService;
        this.resourceLoader = resourceLoader;

    }


    public void run(ApplicationArguments args) {



        List<League> leagues = List.of(
                new League("TLH"),
                new League("MPA"));

        leagueService.saveAll(leagues);
    }

}
