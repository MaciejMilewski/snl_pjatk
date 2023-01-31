package pl.edu.pjwstk.snl.config.dataloader;

import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.snl.match.*;
import pl.edu.pjwstk.snl.match.action.*;
import pl.edu.pjwstk.snl.match.dto.Situation;

import pl.edu.pjwstk.snl.match.dto.TimeLength;
import pl.edu.pjwstk.snl.match.dto.TypePenalty;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.player.PlayerService;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Component
@Order(99)
public class MatchLoader implements ApplicationRunner {

    private final MatchService matchService;
    private final MatchLineupService matchLineupServiceService;
    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public MatchLoader(MatchService matchService, MatchLineupService matchLineupServiceService, TeamService teamService, PlayerService playerService) {
        this.matchService = matchService;
        this.matchLineupServiceService = matchLineupServiceService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        Team goldwell = teamService.findByName("Goldwell").get();
        Team dragons = teamService.findByName("Dragons").get();
        Team oht = teamService.findByName("OHT").get();
        Team madDogs = teamService.findByName("Mad Dogs").get();

        List<Player> goldWellPlayers = playerService.findPlayersByTeamsId(goldwell.getId());
        List<Player> ohtPlayers = playerService.findPlayersByTeamsId(oht.getId());
        List<Player> madDogsPlayers = playerService.findPlayersByTeamsId(madDogs.getId());
        List<Player> dragonsPlayers = playerService.findPlayersByTeamsId(dragons.getId());


        List<MatchPlayer> goldWellMachPlayers = Arrays.asList(
                MatchPlayer.builder().number(1).position(Player.Position.LO).player(goldWellPlayers.get(0)).build(),
                MatchPlayer.builder().number(2).position(Player.Position.PO).player(goldWellPlayers.get(1)).build(),
                MatchPlayer.builder().number(3).position(Player.Position.LS).player(goldWellPlayers.get(2)).build(),
                MatchPlayer.builder().number(4).position(Player.Position.SR).player(goldWellPlayers.get(3)).build(),
                MatchPlayer.builder().number(5).position(Player.Position.PS).player(goldWellPlayers.get(4)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(goldWellPlayers.get(5)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.PO).player(goldWellPlayers.get(6)).build(),
                MatchPlayer.builder().number(8).position(Player.Position.LS).player(goldWellPlayers.get(7)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(goldWellPlayers.get(8)).build(),
                MatchPlayer.builder().number(10).position(Player.Position.PS).player(goldWellPlayers.get(9)).build(),
                MatchPlayer.builder().number(11).position(Player.Position.LO).player(goldWellPlayers.get(10)).build(),
                MatchPlayer.builder().number(12).position(Player.Position.PO).player(goldWellPlayers.get(11)).build(),
                MatchPlayer.builder().number(13).position(Player.Position.LS).player(goldWellPlayers.get(12)).build(),
                MatchPlayer.builder().number(14).position(Player.Position.SR).player(goldWellPlayers.get(13)).build(),
                MatchPlayer.builder().number(15).position(Player.Position.PS).player(goldWellPlayers.get(14)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(goldWellPlayers.get(15)).build(),
                MatchPlayer.builder().number(18).position(Player.Position.PO).player(goldWellPlayers.get(16)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.LS).player(goldWellPlayers.get(17)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(goldWellPlayers.get(18)).build(),
                MatchPlayer.builder().number(21).position(Player.Position.LS).player(goldWellPlayers.get(19)).build(),
                MatchPlayer.builder().number(20).position(Player.Position.BR).player(goldWellPlayers.get(20)).build(),
                MatchPlayer.builder().number(22).position(Player.Position.BR).player(goldWellPlayers.get(21)).build()


        )
                ;

        List<MatchPlayer> ohtMachPlayers = Arrays.asList(
                MatchPlayer.builder().number(1).position(Player.Position.LO).player(ohtPlayers.get(0)).build(),
                MatchPlayer.builder().number(2).position(Player.Position.PO).player(ohtPlayers.get(1)).build(),
                MatchPlayer.builder().number(3).position(Player.Position.LS).player(ohtPlayers.get(2)).build(),
                MatchPlayer.builder().number(4).position(Player.Position.SR).player(ohtPlayers.get(3)).build(),
                MatchPlayer.builder().number(5).position(Player.Position.PS).player(ohtPlayers.get(4)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(ohtPlayers.get(5)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.PO).player(ohtPlayers.get(6)).build(),
                MatchPlayer.builder().number(8).position(Player.Position.LS).player(ohtPlayers.get(7)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(ohtPlayers.get(8)).build(),
                MatchPlayer.builder().number(10).position(Player.Position.PS).player(ohtPlayers.get(9)).build(),
                MatchPlayer.builder().number(11).position(Player.Position.LO).player(ohtPlayers.get(10)).build(),
                MatchPlayer.builder().number(12).position(Player.Position.PO).player(ohtPlayers.get(11)).build(),
                MatchPlayer.builder().number(13).position(Player.Position.LS).player(ohtPlayers.get(12)).build(),
                MatchPlayer.builder().number(14).position(Player.Position.SR).player(ohtPlayers.get(13)).build(),
                MatchPlayer.builder().number(15).position(Player.Position.PS).player(ohtPlayers.get(14)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(ohtPlayers.get(15)).build(),
                MatchPlayer.builder().number(18).position(Player.Position.PO).player(ohtPlayers.get(16)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.LS).player(ohtPlayers.get(17)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(ohtPlayers.get(18)).build(),
                MatchPlayer.builder().number(21).position(Player.Position.LS).player(ohtPlayers.get(19)).build(),
                MatchPlayer.builder().number(20).position(Player.Position.BR).player(ohtPlayers.get(20)).build(),
                MatchPlayer.builder().number(22).position(Player.Position.BR).player(ohtPlayers.get(21)).build()
        );


        List<MatchPlayer> madDogsTeamPlayers = Arrays.asList(
                MatchPlayer.builder().number(1).position(Player.Position.LO).player(madDogsPlayers.get(0)).build(),
                MatchPlayer.builder().number(2).position(Player.Position.PO).player(madDogsPlayers.get(1)).build(),
                MatchPlayer.builder().number(3).position(Player.Position.LS).player(madDogsPlayers.get(2)).build(),
                MatchPlayer.builder().number(4).position(Player.Position.SR).player(madDogsPlayers.get(3)).build(),
                MatchPlayer.builder().number(5).position(Player.Position.PS).player(madDogsPlayers.get(4)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(madDogsPlayers.get(5)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.PO).player(madDogsPlayers.get(6)).build(),
                MatchPlayer.builder().number(8).position(Player.Position.LS).player(madDogsPlayers.get(7)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(madDogsPlayers.get(8)).build(),
                MatchPlayer.builder().number(10).position(Player.Position.PS).player(madDogsPlayers.get(9)).build(),
                MatchPlayer.builder().number(11).position(Player.Position.LO).player(madDogsPlayers.get(10)).build(),
                MatchPlayer.builder().number(12).position(Player.Position.PO).player(madDogsPlayers.get(11)).build(),
                MatchPlayer.builder().number(13).position(Player.Position.LS).player(madDogsPlayers.get(12)).build(),
                MatchPlayer.builder().number(14).position(Player.Position.SR).player(madDogsPlayers.get(13)).build(),
                MatchPlayer.builder().number(15).position(Player.Position.PS).player(madDogsPlayers.get(14)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(madDogsPlayers.get(15)).build(),
                MatchPlayer.builder().number(18).position(Player.Position.PO).player(madDogsPlayers.get(16)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.LS).player(madDogsPlayers.get(17)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(madDogsPlayers.get(18)).build(),
                MatchPlayer.builder().number(21).position(Player.Position.LS).player(madDogsPlayers.get(19)).build(),
                MatchPlayer.builder().number(20).position(Player.Position.BR).player(madDogsPlayers.get(20)).build(),
                MatchPlayer.builder().number(22).position(Player.Position.BR).player(madDogsPlayers.get(21)).build()
        );

        List<MatchPlayer> dragonsMatchPlayers = Arrays.asList(
                MatchPlayer.builder().number(1).position(Player.Position.LO).player(dragonsPlayers.get(0)).build(),
                MatchPlayer.builder().number(2).position(Player.Position.PO).player(dragonsPlayers.get(1)).build(),
                MatchPlayer.builder().number(3).position(Player.Position.LS).player(dragonsPlayers.get(2)).build(),
                MatchPlayer.builder().number(4).position(Player.Position.SR).player(dragonsPlayers.get(3)).build(),
                MatchPlayer.builder().number(5).position(Player.Position.PS).player(dragonsPlayers.get(4)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(dragonsPlayers.get(5)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.PO).player(dragonsPlayers.get(6)).build(),
                MatchPlayer.builder().number(8).position(Player.Position.LS).player(dragonsPlayers.get(7)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(dragonsPlayers.get(8)).build(),
                MatchPlayer.builder().number(10).position(Player.Position.PS).player(dragonsPlayers.get(9)).build(),
                MatchPlayer.builder().number(11).position(Player.Position.LO).player(dragonsPlayers.get(10)).build(),
                MatchPlayer.builder().number(12).position(Player.Position.PO).player(dragonsPlayers.get(11)).build(),
                MatchPlayer.builder().number(13).position(Player.Position.LS).player(dragonsPlayers.get(12)).build(),
                MatchPlayer.builder().number(14).position(Player.Position.SR).player(dragonsPlayers.get(13)).build(),
                MatchPlayer.builder().number(15).position(Player.Position.PS).player(dragonsPlayers.get(14)).build(),
                MatchPlayer.builder().number(16).position(Player.Position.LO).player(dragonsPlayers.get(15)).build(),
                MatchPlayer.builder().number(18).position(Player.Position.PO).player(dragonsPlayers.get(16)).build(),
                MatchPlayer.builder().number(17).position(Player.Position.LS).player(dragonsPlayers.get(17)).build(),
                MatchPlayer.builder().number(19).position(Player.Position.SR).player(dragonsPlayers.get(18)).build(),
                MatchPlayer.builder().number(21).position(Player.Position.LS).player(dragonsPlayers.get(19)).build(),
                MatchPlayer.builder().number(20).position(Player.Position.BR).player(dragonsPlayers.get(20)).build(),
                MatchPlayer.builder().number(22).position(Player.Position.BR).player(dragonsPlayers.get(21)).build()
        );

        MatchLineup homeTeamMatchLineup1 = MatchLineup.builder()
                .matchPlayers(goldWellMachPlayers)
                .build();

        matchLineupServiceService.save(homeTeamMatchLineup1);

        MatchLineup visitingTeamMatchLineup1 = MatchLineup.builder()
                .matchPlayers(dragonsMatchPlayers)
                .build();
        matchLineupServiceService.save(visitingTeamMatchLineup1);

        Match match1 = Match.builder()
                .place("Gdańsk")
                .startTime(LocalDateTime.of(2022, Month.DECEMBER, 12, 8, 0, 0))
                .endTime(LocalDateTime.of(2022, Month.DECEMBER, 12, 9, 30, 0))
                .attendance(1000)
                .referee1("sędzia główny 1")
                .referee2("sędzia główny 2")
                .linesmen1("sedzia liniowy1")
                .linesmen2("sedzia liniowy2")
                .homeTeamMenager("kierownik gospodarzy")
                .homeTeamCoach("trener gospodarzy")
                .visitingTeamMenager("kierownik gosci")
                .visitingTeamCoach("trener gosci")
                .spectator("spiker1")
                .timeReferee("sedzia czasu")
                .penaltyReferee1("sedzia kar1")
                .penaltyReferee2("sedzia kar2")
                .speaker("spiker")
                .status(MatchStatus.PROTOCOL_FINALIZED)
//                .medicNot("To jest notata medyka, jeśli coś się stanie na meczu to wpisuje tutaj swoje uwagi")
//                .refereeNot("To jest notatka z meczu, tworzy ją sekretarz lub sędzia. Opisuje się tutaj kary większe i jakies dziwne zajścia")
                .homeTeamMatchLineup(homeTeamMatchLineup1)
                .visitingTeamMatchLineup(visitingTeamMatchLineup1)
                .homeTeam(goldwell)
                .visitingTeam(oht)
                .build();

        List<Goal> match1goals = Arrays.asList(
                Goal.builder()
                        .scorer(match1.getHomeTeamMatchLineup().getMatchPlayers().get(0))
                        .assistant1(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .assistant2(match1.getHomeTeamMatchLineup().getMatchPlayers().get(0))
                        .situation(Situation.EN)
                        .gameMinute(11)
                        .gameSecond(21)
                        .match(match1)
                        .team(match1.getHomeTeam())
                        .build(),
                Goal.builder()
                        .scorer(match1.getHomeTeamMatchLineup().getMatchPlayers().get(0))
                        .assistant1(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .assistant2(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .situation(Situation.SH1)
                        .gameMinute(1)
                        .gameSecond(34)                        .match(match1)
                        .team(match1.getHomeTeam())
                        .build(),
                Goal.builder()
                        .scorer(match1.getHomeTeamMatchLineup().getMatchPlayers().get(0))
                        .assistant1(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .assistant2(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .situation(Situation.SH1)
                        .gameMinute(51)
                        .gameSecond(21)                        .match(match1)
                        .team(match1.getVisitingTeam())
                        .build()
        );
        match1.setGoals(match1goals);

        List<BreakeTime> match1breakes = Arrays.asList(
                BreakeTime.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(6)
                        .gameSecond(15)
                        .match(match1)
                        .team(match1.getVisitingTeam())
                        .build(),
                BreakeTime.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(13)
                        .gameSecond(46)
                        .match(match1)
                        .team(match1.getHomeTeam())
                        .build()
        );
        match1.setBreakeTimes(match1breakes);

        List<Penalty> match1penalty = Arrays.asList(
                Penalty.builder()
                       // .startTime(LocalDateTime.now())
                       // .endTime(LocalDateTime.now())
                       // .number(12)
                        .gameMinute(20)
                        .gameSecond(20)
                        .gameMinute(12)
                        .gameSecond(1)
                        .penalized(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .match(match1)
                        .timeLength(TimeLength.TWO)
                        .typePenalty(TypePenalty.HOLD)
                        .team(match1.getVisitingTeam())
                        .build(),
                Penalty.builder()
                        //.startTime(LocalDateTime.now())
                        //.endTime(LocalDateTime.now())
                        //.number(12)
                        .gameMinute(17)
                        .gameSecond(71)
                        .penalized(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .match(match1)
                        .timeLength(TimeLength.TWO)
                        .typePenalty(TypePenalty.SLASH)
                        .team(match1.getVisitingTeam())
                        .build(),
                Penalty.builder()
                        //.startTime(LocalDateTime.now())
                        //.endTime(LocalDateTime.now())
                        //.number(1)
                        .gameMinute(18)
                        .gameSecond(19)
                        .penalized(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(1))
                        .match(match1)
                        .timeLength(TimeLength.FOUR)
                        .typePenalty(TypePenalty.HOLD)
                        .team(match1.getHomeTeam())
                        .build(),
                Penalty.builder()
                        //.startTime(LocalDateTime.now())
                       // .endTime(LocalDateTime.now())
                       // .number(1)
                        .gameMinute(56)
                        .gameSecond(12)
                        .penalized(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(1))
                        .match(match1)
                        .timeLength(TimeLength.TWENTY)
                        .typePenalty(TypePenalty.GAMI)
                        .team(match1.getHomeTeam())
                        .build()

        );
        match1.setPenalties(match1penalty);


        List<ChangeGolkiper> match1changeGolkiper = Arrays.asList(
                ChangeGolkiper.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(10)
                        .gameSecond(12)
                        //.golkiperInNo(11)
                        .golkiperIn(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(1))
                        .golkiperOut(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(2))
                        //golkiperOutNo(12)
                        .match(match1)
                        .team(match1.getVisitingTeam())
                        .build(),
                ChangeGolkiper.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(30)
                        .gameSecond(58)
                        .golkiperIn(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(2))
                        .golkiperOut(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(1))
                        //.golkiperInNo(12)
                        //.golkiperOutNo(11)
                        .match(match1)
                        .team(match1.getVisitingTeam())
                        .build(),
                ChangeGolkiper.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(25)
                        .gameSecond(25)
                        .golkiperIn(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        .golkiperOut(match1.getHomeTeamMatchLineup().getMatchPlayers().get(2))
                        //.golkiperInNo(1)
                        //.golkiperOutNo(2)
                        .match(match1)
                        .team(match1.getHomeTeam())
                        .build(),
                ChangeGolkiper.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(46)
                        .gameSecond(36)
                        .golkiperIn(match1.getHomeTeamMatchLineup().getMatchPlayers().get(2))
                        .golkiperOut(match1.getHomeTeamMatchLineup().getMatchPlayers().get(1))
                        //.golkiperInNo(2)
                        //.golkiperOutNo(1)
                        .match(match1)
                        .team(match1.getHomeTeam())
                        .build()

        );
        match1.setChangeGolkipers(match1changeGolkiper);

        List<PenaltyShot> match1penaltyshot = Arrays.asList(
                PenaltyShot.builder()
                        .schooter(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(11))
                        .goalkiper(match1.getHomeTeamMatchLineup().getMatchPlayers().get(2))
                        .match(match1)
                        .gameMinute(50)
                        .gameSecond(58)
                        .team(match1.getVisitingTeam())
                        .success(false)
                        .build(),
                PenaltyShot.builder()

                        .schooter(match1.getHomeTeamMatchLineup().getMatchPlayers().get(11))
                        .goalkiper(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(2))
                        .match(match1)
                        .gameMinute(62)
                        .gameSecond(12)
                        .team(match1.getHomeTeam())
                        .success(true)
                        .build()
        );

        match1.setPenaltyShots(match1penaltyshot);


        List<PenaltyShotMissed> match1penaltyshotmissed = Arrays.asList(
                PenaltyShotMissed.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(46)
                        .gameSecond(36)
                        .shooter(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(11))
                        .goalkiper(match1.getHomeTeamMatchLineup().getMatchPlayers().get(2))
                        .match(match1)
                        .team(match1.getVisitingTeam())
                        .build(),
                PenaltyShotMissed.builder()
                        //.time(LocalDateTime.now())
                        .gameMinute(11)
                        .gameSecond(15)
                        //.schooter(12)
                        //.goalkiper(11)
                        .shooter(match1.getHomeTeamMatchLineup().getMatchPlayers().get(11))
                        .goalkiper(match1.getVisitingTeamMatchLineup().getMatchPlayers().get(2))
                        .match(match1)
                        .team(match1.getHomeTeam())
                        .build()
        );

        match1.setPenaltyShotsMissed(match1penaltyshotmissed);

        MatchLineup homeTeamMatchLineup2 = MatchLineup.builder()
                .matchPlayers(goldWellMachPlayers)
                .build();

        matchLineupServiceService.save(homeTeamMatchLineup2);

        MatchLineup visitingTeamMatchLineup2 = MatchLineup.builder()
                .matchPlayers(madDogsTeamPlayers)
                .build();
        matchLineupServiceService.save(visitingTeamMatchLineup2);


        Match match2 = Match.builder()
                .place("Gdynia")
                .startTime(LocalDateTime.of(2022, Month.DECEMBER, 13, 10, 0, 0))
                .endTime(LocalDateTime.of(2022, Month.DECEMBER, 13, 11, 30, 0))
                .attendance(100)
                .referee1("sędzia główny 2.1")
                .referee2("sędzia główny 2.2")
                .linesmen1("sedzia liniowy2.1")
                .linesmen2("sedzia liniowy2.2")
                .homeTeamMenager("kierownik gospodarzy2")
                .homeTeamCoach("trener gospodarzy2")
                .visitingTeamMenager("kierownik gości2")
                .visitingTeamCoach("trener gosci2")
                .spectator("spiker2")
                .timeReferee("sedzia czasu2")
                .penaltyReferee1("sedzia kar2.1")
                .penaltyReferee2("sedzia kar2.2")
                .speaker("spiker2")
                .status(MatchStatus.PROTOCOL_FINALIZED)
//                .medicNot("To jest notata medyka, jeśli coś się stanie na meczu to wpisuje tutaj swoje uwagi")
//                .refereeNot("To jest notatka z meczu, tworzy ją sekretarz lub sędzia. Opisuje się tutaj kary większe i jakies dziwne zajścia")
                .homeTeamMatchLineup(homeTeamMatchLineup2)
                .visitingTeamMatchLineup(visitingTeamMatchLineup2)
                .homeTeam(goldwell)
                .visitingTeam(dragons)
                .build();

        MatchLineup homeTeamMatchLineup3 = MatchLineup.builder()
                .matchPlayers(ohtMachPlayers)
                .build();

        matchLineupServiceService.save(homeTeamMatchLineup3);

        MatchLineup visitingTeamMatchLineup3 = MatchLineup.builder()
                .matchPlayers(goldWellMachPlayers)
                .build();
        matchLineupServiceService.save(visitingTeamMatchLineup3);

        Match match3 = Match.builder()
                .place("Gdynia")
                .startTime(LocalDateTime.of(2023, Month.MARCH, 1, 10, 0, 0))
                .endTime(LocalDateTime.of(2023, Month.MARCH, 1, 11, 15, 0))
                .attendance(100)
                .referee1("sędzia główny 2.1")
                .referee2("sędzia główny 2.2")
                .linesmen1("sedzia liniowy2.1")
                .linesmen2("sedzia liniowy2.2")
                .homeTeamMenager("kierownik gospodarzy2")
                .homeTeamCoach("trener gospodarzy2")
                .visitingTeamMenager("kierownik gosci2")
                .visitingTeamCoach("trener gosci2")
                .spectator("spiker2")
                .timeReferee("sedzia czasu2")
                .penaltyReferee1("sedzia kar2.1")
                .penaltyReferee2("sedzia kar2.2")
                .speaker("spiker2")
                .status(MatchStatus.READY)
//                .medicNot("To jest notata medyka, jeśli coś się stanie na meczu to wpisuje tutaj swoje uwagi")
//                .refereeNot("To jest notatka z meczu, tworzy ją sekretarz lub sędzia. Opisuje się tutaj kary większe i jakies dziwne zajścia")
                .homeTeamMatchLineup(homeTeamMatchLineup3)
                .visitingTeamMatchLineup(visitingTeamMatchLineup3)
                .homeTeam(madDogs)
                .visitingTeam(goldwell)
                .build();

        List<Match> matches = List.of(
                match1, match2, match3
        );

        try {
            matchService.saveAll(matches);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Match> allMatches = matchService.findAll();



    }


}
