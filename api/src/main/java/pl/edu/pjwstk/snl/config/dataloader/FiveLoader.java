package pl.edu.pjwstk.snl.config.dataloader;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.five.Five;
import pl.edu.pjwstk.snl.five.FiveDTO;
import pl.edu.pjwstk.snl.five.FivePlayer;
import pl.edu.pjwstk.snl.five.FiveService;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.player.PlayerService;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component()
@Order(105)
@RequiredArgsConstructor
public class FiveLoader implements ApplicationRunner {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final FiveService fiveService;


    @Transactional
    public void run(ApplicationArguments args) {
        Team goldwell = teamService.findByName("Goldwell").get();
        Team dragons = teamService.findByName("Dragons").get();

        List<Player> goldWellPlayers = playerService.findPlayersByTeamsId(goldwell.getId());
        List<Player> dragonsPlayers = playerService.findPlayersByTeamsId(dragons.getId());


        Set<FivePlayer> fivePlayer1 = Set.of(
                FivePlayer.builder().number(1).position(Player.Position.LO).player(goldWellPlayers.get(0)).build(),
                FivePlayer.builder().number(2).position(Player.Position.PO).player(goldWellPlayers.get(1)).build(),
                FivePlayer.builder().number(3).position(Player.Position.LS).player(goldWellPlayers.get(2)).build(),
                FivePlayer.builder().number(4).position(Player.Position.SR).player(goldWellPlayers.get(3)).build(),
                FivePlayer.builder().number(5).position(Player.Position.PS).player(goldWellPlayers.get(4)).build()
                );
        Set<FivePlayer> fivePlayer2 = Set.of(
                FivePlayer.builder().number(14).position(Player.Position.LO).player(goldWellPlayers.get(5)).build(),
                FivePlayer.builder().number(22).position(Player.Position.PO).player(goldWellPlayers.get(6)).build(),
                FivePlayer.builder().number(32).position(Player.Position.LS).player(goldWellPlayers.get(7)).build(),
                FivePlayer.builder().number(42).position(Player.Position.SR).player(goldWellPlayers.get(8)).build(),
                FivePlayer.builder().number(52).position(Player.Position.PS).player(goldWellPlayers.get(9)).build()
        );
        Set<FivePlayer> fivePlayer3 = Set.of(
                FivePlayer.builder().number(13).position(Player.Position.LO).player(goldWellPlayers.get(10)).build(),
                FivePlayer.builder().number(23).position(Player.Position.PO).player(goldWellPlayers.get(11)).build(),
                FivePlayer.builder().number(33).position(Player.Position.LS).player(goldWellPlayers.get(12)).build(),
                FivePlayer.builder().number(43).position(Player.Position.SR).player(goldWellPlayers.get(13)).build(),
                FivePlayer.builder().number(53).position(Player.Position.PS).player(goldWellPlayers.get(14)).build()
        );
        Set<FivePlayer> fivePlayer4 = Set.of(
                FivePlayer.builder().number(14).position(Player.Position.LO).player(goldWellPlayers.get(15)).build(),
                FivePlayer.builder().number(24).position(Player.Position.PO).player(goldWellPlayers.get(16)).build(),
                FivePlayer.builder().number(34).position(Player.Position.LS).player(goldWellPlayers.get(17)).build(),
                FivePlayer.builder().number(44).position(Player.Position.SR).player(goldWellPlayers.get(18)).build(),
                FivePlayer.builder().number(54).position(Player.Position.PS).player(goldWellPlayers.get(19)).build()
        );

        Set<FivePlayer> fiveDragonsPlayer1 = Set.of(
                FivePlayer.builder().number(1).position(Player.Position.LO).player(dragonsPlayers.get(0)).build(),
                FivePlayer.builder().number(2).position(Player.Position.PO).player(dragonsPlayers.get(1)).build(),
                FivePlayer.builder().number(3).position(Player.Position.LS).player(dragonsPlayers.get(2)).build(),
                FivePlayer.builder().number(4).position(Player.Position.SR).player(dragonsPlayers.get(3)).build(),
                FivePlayer.builder().number(5).position(Player.Position.PS).player(dragonsPlayers.get(4)).build()
        );
        Set<FivePlayer> fiveDragonsPlayer2 = Set.of(
                FivePlayer.builder().number(14).position(Player.Position.LO).player(dragonsPlayers.get(5)).build(),
                FivePlayer.builder().number(22).position(Player.Position.PO).player(dragonsPlayers.get(6)).build(),
                FivePlayer.builder().number(32).position(Player.Position.LS).player(dragonsPlayers.get(7)).build(),
                FivePlayer.builder().number(42).position(Player.Position.SR).player(dragonsPlayers.get(8)).build(),
                FivePlayer.builder().number(52).position(Player.Position.PS).player(dragonsPlayers.get(9)).build()
        );
        Set<FivePlayer> fiveDragonsPlayer3 = Set.of(
                FivePlayer.builder().number(13).position(Player.Position.LO).player(dragonsPlayers.get(10)).build(),
                FivePlayer.builder().number(23).position(Player.Position.PO).player(dragonsPlayers.get(11)).build(),
                FivePlayer.builder().number(33).position(Player.Position.LS).player(dragonsPlayers.get(12)).build(),
                FivePlayer.builder().number(43).position(Player.Position.SR).player(dragonsPlayers.get(13)).build(),
                FivePlayer.builder().number(53).position(Player.Position.PS).player(dragonsPlayers.get(14)).build()
        );
        Set<FivePlayer> fiveDragonsPlayer4 = Set.of(
                FivePlayer.builder().number(14).position(Player.Position.LO).player(dragonsPlayers.get(15)).build(),
                FivePlayer.builder().number(24).position(Player.Position.PO).player(dragonsPlayers.get(16)).build(),
                FivePlayer.builder().number(34).position(Player.Position.LS).player(dragonsPlayers.get(17)).build(),
                FivePlayer.builder().number(44).position(Player.Position.SR).player(dragonsPlayers.get(18)).build(),
                FivePlayer.builder().number(54).position(Player.Position.PS).player(dragonsPlayers.get(19)).build()
        );

        Five goldwellFive1 = Five.builder()
                .name("Formacja A")
                .team(goldwell)
                .players(fivePlayer1)
                .build();
        Five goldwellFive2 = Five.builder()
                .name("Formacja B")
                .team(goldwell)
                .players(fivePlayer2)
                .build();
        Five goldwellFive3 = Five.builder()
                .name("Formacja C")
                .team(goldwell)
                .players(fivePlayer3)
                .build();
        Five goldwellFive4 = Five.builder()
                .name("Formacja D")
                .team(goldwell)
                .players(fivePlayer4)
                .build();
        Five dragonsFive1 = Five.builder()
                .name("Formacja A")
                .team(dragons)
                .players(fiveDragonsPlayer1)
                .build();
        Five dragonsFive2 = Five.builder()
                .name("Formacja B")
                .team(dragons)
                .players(fiveDragonsPlayer2)
                .build();
        Five dragonsFive3 = Five.builder()
                .name("Formacja C")
                .team(dragons)
                .players(fiveDragonsPlayer3)
                .build();
        Five dragonsFive4 = Five.builder()
                .name("Formacja D")
                .team(dragons)
                .players(fiveDragonsPlayer4)
                .build();

        List<Five> fives = List.of(
                goldwellFive1,
                goldwellFive2,
                goldwellFive3,
                goldwellFive4,
                dragonsFive1,
                dragonsFive2,
                dragonsFive3,
                dragonsFive4
        );

        fiveService.createFiveAndAssignPlayersToFive(
                goldwellFive1.getTeam(),
                goldwellFive1.getPlayers().stream().toList(),
                goldwellFive1.getName(),
                teamService,
                playerService);
        fiveService.createFiveAndAssignPlayersToFive(
                goldwellFive2.getTeam(),
                goldwellFive2.getPlayers().stream().toList(),
                goldwellFive2.getName(),
                teamService,
                playerService);
        fiveService.createFiveAndAssignPlayersToFive(
                goldwellFive3.getTeam(),
                goldwellFive3.getPlayers().stream().toList(),
                goldwellFive3.getName(),
                teamService,
                playerService);
        fiveService.createFiveAndAssignPlayersToFive(
                goldwellFive4.getTeam(),
                goldwellFive4.getPlayers().stream().toList(),
                goldwellFive4.getName(),
                teamService,
                playerService);
        fiveService.createFiveAndAssignPlayersToFive(
                dragonsFive1.getTeam(),
                dragonsFive1.getPlayers().stream().toList(),
                dragonsFive1.getName(),
                teamService,
                playerService);
        fiveService.createFiveAndAssignPlayersToFive(
                dragonsFive2.getTeam(),
                dragonsFive2.getPlayers().stream().toList(),
                dragonsFive2.getName(),
                teamService,
                playerService);
        fiveService.createFiveAndAssignPlayersToFive(
                dragonsFive3.getTeam(),
                dragonsFive3.getPlayers().stream().toList(),
                dragonsFive3.getName(),
                teamService,
                playerService);
        fiveService.createFiveAndAssignPlayersToFive(
                dragonsFive4.getTeam(),
                dragonsFive4.getPlayers().stream().toList(),
                dragonsFive4.getName(),
                teamService,
                playerService);
    }
}
