package pl.edu.pjwstk.snl.match;


import java.time.LocalDateTime;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.match.action.*;
import pl.edu.pjwstk.snl.match.dto.*;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.ActionAggregator;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.BreakTimeToAggregationDTO;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.ChangeGoalkeeperToAggregationDTO;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.GoalToAggregationDTO;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.PenaltyShotMissedToAggregationDTO;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.PenaltyShotToAggregationDTO;
import pl.edu.pjwstk.snl.match.dto.ActionAggregator.PenaltyToAggregationDTO;
import pl.edu.pjwstk.snl.match_user.Comment.Comment;
import pl.edu.pjwstk.snl.match_user.Comment.CommentRepository;
import pl.edu.pjwstk.snl.match_user.MatchRole;
import pl.edu.pjwstk.snl.match_user.MatchUser;
import pl.edu.pjwstk.snl.match_user.MatchUserService;
import pl.edu.pjwstk.snl.player.PlayerService;
import pl.edu.pjwstk.snl.security.User.User;
import pl.edu.pjwstk.snl.security.User.UserService;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchLineupService matchLineupService;
    private final GoalRepository goalRepository;
    private final MatchInfoToMatchMapper mapper;
    private final TeamService teamService;
    private final PenaltyRepository penaltyRepository;
    private final PenaltyShotMissedRepository penaltyShotMissedRepository;
    private final PenaltyShotRepository penaltyShotRepository;
    private final ChangeGolkiperRepository changeGolkiperRepository;
    private final BreakeTimeRepository breakeTimeRepository;
    private final PlayerService playerService;
    private final GoalService goalService;
    private final PenaltyService penaltyService;
    private final MatchUserService matchUserService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final MatchPlayerService matchPlayerService;

    public Page<Match> findAllWithPagination(Pageable paging) {
        return this.matchRepository.findAll(paging);
    }

    public List<Match> findAll() {
        return this.matchRepository.findAll();
    }

    public Optional<Match> findById(Long id) {
        return this.matchRepository.findById(id);
    }

    public List<Match> saveAll(List<Match> matches) {
        return matchRepository.saveAll(matches);
    }

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public Match insertMatchInfo(CreateMatchInfoDto createMatchInfoDto) {
        Team homeTeam = teamService.findById(createMatchInfoDto.getHomeTeamId()).get();
        Team visitingTeam = teamService.findById(createMatchInfoDto.getVisitingTeamId()).get();

        Match match = new Match();
        mapper.mappedCreateMatchInfoToMatch(createMatchInfoDto, match);

        match.setHomeTeam(homeTeam);
        match.setVisitingTeam(visitingTeam);

        match.setStatus(MatchStatus.CREATED);

        MatchUser secretary = new MatchUser();
        secretary.setMatch(match);
        User secretaryUser = this.userService.findById(createMatchInfoDto.getSecretaryId()).orElse(null);
        secretary.setUser(secretaryUser);

        if (secretaryUser != null) {
            match.setSecretary(secretaryUser.getUsername());
        }

        secretary.setRole(MatchRole.SECRETARY);
        this.matchUserService.save(secretary);

        MatchUser medic = new MatchUser();
        medic.setMatch(match);
        medic.setUser(this.userService.findById(createMatchInfoDto.getMedicId()).orElse(null));
        medic.setRole(MatchRole.MEDIC);
        this.matchUserService.save(medic);

        MatchUser referee1 = new MatchUser();
        referee1.setMatch(match);
        referee1.setUser(this.userService.findById(createMatchInfoDto.getReferee1Id()).orElse(null));
        referee1.setRole(MatchRole.REFEREE);
        this.matchUserService.save(referee1);

        MatchUser referee2 = new MatchUser();
        referee2.setMatch(match);
        referee2.setUser(this.userService.findById(createMatchInfoDto.getReferee2Id()).orElse(null));
        referee2.setRole(MatchRole.REFEREE);
        this.matchUserService.save(referee2);

        return this.save(match);
    }

    public Match updateMatchInfo(Long id, MatchInfo matchInfo) {
        Match match = this.findById(id).get();
        mapper.mappedMatchInfoToMatch(matchInfo, match);

        return this.save(match);
    }


    public boolean existsById(Long id) {
        return matchRepository.existsById(id);
    }

    @Transactional
    public List<MatchPlayer> getTeamLineupForMatch(Long matchId, Long teamId) throws Exception {
        Match match = this.findById(matchId).orElse(null);

        try {
            if (match.getHomeTeam().getId() == teamId) {
                List<MatchPlayer> matchLineup = match.getHomeTeamMatchLineup().getMatchPlayers();
                return matchLineup;
            } else if ((match.getVisitingTeam().getId() == teamId)) {
                List<MatchPlayer> matchLineup = match.getVisitingTeamMatchLineup().getMatchPlayers();
                return matchLineup;
            }
        } catch (Exception e) {
            throw new Exception("Team not assigned to this match");
        }
        return null;
    }

    @Transactional
    public void addHomeTeamPlayers(Long matchId, Long teamId, List<MatchPlayerPositionDto> matchPlayerPositions) {
        Match match = this.findById(matchId).orElse(null);

        match.setHomeTeamMatchLineup(getMatchLineup(match, teamId, matchPlayerPositions));

        isMatchReady(match);

        this.save(match);
    }

    @Transactional
    public void addVisitingTeamPlayers(Long matchId, Long teamId, List<MatchPlayerPositionDto> matchPlayerPositions) {
        Match match = this.findById(matchId).orElse(null);

        match.setVisitingTeamMatchLineup(getMatchLineup(match, teamId, matchPlayerPositions));

        isMatchReady(match);

        this.save(match);
    }

    private void isMatchReady(Match match) {
        if (match.getVisitingTeamMatchLineup() != null && match.getHomeTeamMatchLineup() != null) {
            if (match.getVisitingTeamMatchLineup().isLineupComplete() && match.getHomeTeamMatchLineup().isLineupComplete()) {
                match.setStatus(MatchStatus.READY);
            } else {
                match.setStatus(MatchStatus.CREATED);
            }
        }
    }

    private MatchLineup getMatchLineup(Match match, Long teamId, List<MatchPlayerPositionDto> matchPlayerPositions) {

        Team team = teamService.findById(teamId).get();

        List<MatchPlayer> matchPlayers = new ArrayList<>();

        for (MatchPlayerPositionDto matchPlayerPosition : matchPlayerPositions) {
            MatchPlayer matchPlayer = MatchPlayer.builder()
                    .number(matchPlayerPosition.getNumber())
                    .position(matchPlayerPosition.getPosition())
                    .player(playerService.findById(matchPlayerPosition.getPlayerId()).get())
                    .build();
            matchPlayers.add(matchPlayer);
        }

        MatchLineup matchLineup = MatchLineup.builder()
                .matchPlayers(matchPlayers)
                .build();

        return matchLineup;
    }


    public void addGoal(Long matchId, Long teamId, GoalCreationDTO goalCreation) {
        Match match = this.findById(matchId).get();
        Team team = teamService.findById(teamId).get();

        if (penaltyService.getPeriod(goalCreation.getGameMinute()) == 4) {
            if (existGoalInPeriod4(match)) {
                throw new RuntimeException("Istnieje już zarejestrowany gol w fazie karnych");
            }
        }

        MatchPlayer scorer = matchPlayerService.findById(goalCreation.getScorer().getId()).get();

        MatchPlayer assistant1 = null;
        if (goalCreation.getAssistant1().getId() != null) {
            assistant1 = matchPlayerService.findById(goalCreation.getAssistant1().getId()).get();
        }

        MatchPlayer assistant2 = null;
        if (goalCreation.getAssistant2().getId() != null) {
            assistant2 = matchPlayerService.findById(goalCreation.getAssistant2().getId()).get();
        }

        Goal goal = Goal
                .builder()
                .match(match)
                .team(team)
                .gameMinute(goalCreation.getGameMinute())
                .gameSecond(goalCreation.getGameSecond())
                .scorer(scorer)
                .assistant1(assistant1)
                .assistant2(assistant2)
                .situation(goalCreation.getSituation())
                .build();

        match.getGoals().add(goal);

        this.save(match);
    }

    private boolean existGoalInPeriod4(Match match) {
        for (Goal goal : match.getGoals()) {
            if (penaltyService.getPeriod(goal.getGameMinute()) == 4) {
                return true;
            }
        }
        return false;
    }

    public void addBreakes(Long matchId, Long teamId, BreakeTimeCreationDTO breakeTimeCreation) {
        Match match = this.findById(matchId).get();
        Team team = teamService.findById(teamId).get();

        BreakeTime breakeTime = BreakeTime
                .builder()
                .match(match)
                .team(team)
                .gameMinute(breakeTimeCreation.getGameMinute())
                .gameSecond(breakeTimeCreation.getGameSecond())
                .build();

        breakeTime.setMatch(match);
        breakeTime.setTeam(team);

        List<BreakeTime> breakeTimes = match.getBreakeTimes();

        long count = breakeTimes.stream().map(BreakeTime::getTeam).filter(t -> teamId.equals(t.getId())).count();

        if (count > 0) {
            throw new RuntimeException("Drużyna o id " + teamId + " wzięła już czas");
        }

        breakeTimes.add(breakeTime);

        this.save(match);
    }

    public void addPenalties(Long matchId, Long teamId, PenaltyCreationDTO penaltyCreation) {
        Match match = this.findById(matchId).get();
        Team team = teamService.findById(teamId).get();

        MatchPlayer penalized = matchPlayerService.findById(penaltyCreation.getPenalized().getId()).get();
        Penalty penalty = Penalty
                .builder()
                .match(match)
                .team(team)
                .gameMinute(penaltyCreation.getGameMinute())
                .gameSecond(penaltyCreation.getGameSecond())
                .penalized(penalized)
                .typePenalty(penaltyCreation.getTypePenalty())
                .timeLength(penaltyCreation.getTimeLength())
                .build();

        match.getPenalties().add(penalty);

        this.save(match);
    }

    public void addChangeGolkipers(Long matchId, Long teamId, ChangeGolkiperCreationDTO changeGolkiperCreation) {
        Match match = this.findById(matchId).get();
        Team team = teamService.findById(teamId).get();

        MatchPlayer golkiperIn = matchPlayerService.findById(changeGolkiperCreation.getGolkiperIn().getId()).get();
        MatchPlayer golkiperOut = matchPlayerService.findById(changeGolkiperCreation.getGolkiperOut().getId()).get();
        ChangeGolkiper changeGolkiper = ChangeGolkiper
                .builder()
                .match(match)
                .team(team)
                .gameMinute(changeGolkiperCreation.getGameMinute())
                .gameSecond(changeGolkiperCreation.getGameSecond())
                .golkiperIn(golkiperIn)
                .golkiperOut(golkiperOut)
                .build();
        match.getChangeGolkipers().add(changeGolkiper);

        this.save(match);
    }

    public void addPenaltyShotMissed(Long matchId, Long teamId, PenaltyShotMissedCreationDTO penaltyShotMissedCreation) {
        Match match = this.findById(matchId).get();
        Team team = teamService.findById(teamId).get();

        MatchPlayer goalkiper = matchPlayerService.findById(penaltyShotMissedCreation.getGoalkiper().getId()).get();
        MatchPlayer shooter = matchPlayerService.findById(penaltyShotMissedCreation.getShooter().getId()).get();
        PenaltyShotMissed penaltyShotMissed = PenaltyShotMissed
                .builder()
                .match(match)
                .team(team)
                .gameMinute(penaltyShotMissedCreation.getGameMinute())
                .gameSecond(penaltyShotMissedCreation.getGameSecond())
                .goalkiper(goalkiper)
                .shooter(shooter)
                .build();
        match.getPenaltyShotsMissed().add(penaltyShotMissed);

        this.save(match);
    }

    public void addPenaltyShot(Long matchId, Long teamId, PenaltyShotCreationDTO penaltyShotCreation) {
        Match match = this.findById(matchId).get();
        Team team = teamService.findById(teamId).get();

        MatchPlayer schooter = matchPlayerService.findById(penaltyShotCreation.getSchooter().getId()).get();
        MatchPlayer goalkiper = matchPlayerService.findById(penaltyShotCreation.getGoalkiper().getId()).get();
        PenaltyShot penaltyShot = PenaltyShot
                .builder()
                .match(match)
                .team(team)
                .schooter(schooter)
                .goalkiper(goalkiper)
                .gameMinute(penaltyShotCreation.getGameMinute())
                .gameSecond(penaltyShotCreation.getGameSecond())
                .build();
        match.getPenaltyShots().add(penaltyShot);

        this.save(match);
    }

    public List<Match> getMatchesByTeamId(Long teamId) {
        Team team = teamService.findById(teamId).get();
        List<Match> homeMatches = matchRepository.findByHomeTeam(team);
        List<Match> visitingMatches = matchRepository.findByVisitingTeam(team);

        return Stream.concat(homeMatches.stream(), visitingMatches.stream()).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        matchRepository.deleteById(id);
    }

    public void deleteGoal(Long goalId) {
        Goal goal = this.goalRepository.findById(goalId).orElse(null);
        Match match = goal.getMatch();

        List<Goal> goals = match.getGoals();
        for (Iterator<Goal> iterator = goals.iterator(); iterator.hasNext(); ) {
            Goal goal1 = iterator.next();
            if (goal1.getId() == goal.getId()) {
                goals.remove(goal1);
                break;
            }
        }
        match.setGoals(goals);

        this.goalRepository.deleteById(goalId);
        this.matchRepository.save(match);
        this.matchRepository.save(match);
    }

    public void deletePenalty(Long penaltyId) {
        Penalty penalty = this.penaltyRepository.findById(penaltyId).orElse(null);
        Match match = penalty.getMatch();

        List<Penalty> penalties = match.getPenalties();
        for (Iterator<Penalty> iterator = penalties.iterator(); iterator.hasNext(); ) {
            Penalty penalty1 = iterator.next();
            if (penalty1.getId() == penalty.getId()) {
                penalties.remove(penalty1);
                break;
            }
        }
        match.setPenalties(penalties);

        this.penaltyRepository.deleteById(penaltyId);
        this.matchRepository.save(match);
    }

    public void deletePenaltyShotMissed(Long penaltyShotMissedId) {
        PenaltyShotMissed penaltyShotMissed = this.penaltyShotMissedRepository.findById(penaltyShotMissedId).orElse(null);
        Match match = penaltyShotMissed.getMatch();

        List<PenaltyShotMissed> penaltyShotsMissed = match.getPenaltyShotsMissed();
        for (Iterator<PenaltyShotMissed> iterator = penaltyShotsMissed.iterator(); iterator.hasNext(); ) {
            PenaltyShotMissed penaltyShotMissed1 = iterator.next();
            if (penaltyShotMissed1.getId() == penaltyShotMissed.getId()) {
                penaltyShotsMissed.remove(penaltyShotMissed1);
                break;
            }
        }
        match.setPenaltyShotsMissed(penaltyShotsMissed);

        this.penaltyShotMissedRepository.deleteById(penaltyShotMissedId);
        this.matchRepository.save(match);
    }

    public void deletePenaltyShot(Long penaltyShotId) {
        PenaltyShot penaltyShot = this.penaltyShotRepository.findById(penaltyShotId).orElse(null);
        Match match = penaltyShot.getMatch();

        List<PenaltyShot> penaltyShots = match.getPenaltyShots();
        for (Iterator<PenaltyShot> iterator = penaltyShots.iterator(); iterator.hasNext(); ) {
            PenaltyShot penaltyShot1 = iterator.next();
            if (penaltyShot1.getId() == penaltyShot.getId()) {
                penaltyShots.remove(penaltyShot1);
                break;
            }
        }
        match.setPenaltyShots(penaltyShots);

        this.penaltyShotRepository.deleteById(penaltyShotId);
        this.matchRepository.save(match);
    }

    public void deleteChangeGolkiper(Long changeGolkipersId) {
        ChangeGolkiper changeGolkiper = this.changeGolkiperRepository.findById(changeGolkipersId).orElse(null);
        Match match = changeGolkiper.getMatch();

        List<ChangeGolkiper> changeGolkipers = match.getChangeGolkipers();
        for (Iterator<ChangeGolkiper> iterator = changeGolkipers.iterator(); iterator.hasNext(); ) {
            ChangeGolkiper changeGolkiper1 = iterator.next();
            if (changeGolkiper1.getId() == changeGolkiper.getId()) {
                changeGolkipers.remove(changeGolkiper1);
                break;
            }
        }
        match.setChangeGolkipers(changeGolkipers);

        this.changeGolkiperRepository.deleteById(changeGolkipersId);
        this.matchRepository.save(match);
    }

    public void deleteBreakeTime(Long breakeTimeId) {
        BreakeTime breakeTime = this.breakeTimeRepository.findById(breakeTimeId).orElse(null);
        Match match = breakeTime.getMatch();

        List<BreakeTime> breakeTimes = match.getBreakeTimes();
        for (Iterator<BreakeTime> iterator = breakeTimes.iterator(); iterator.hasNext(); ) {
            BreakeTime breakeTime1 = iterator.next();
            if (breakeTime1.getId() == breakeTime.getId()) {
                breakeTimes.remove(breakeTime1);
                break;
            }
        }
        match.setBreakeTimes(breakeTimes);

        this.breakeTimeRepository.deleteById(breakeTimeId);
        this.matchRepository.save(match);
    }

    public PenaltiesTable getPenaltiesTable(Long matchId) {

        Match match = findById(matchId).get();
        List<Penalty> penalties = penaltyService.findByMatch(match);

        int[] homeTeamPenaltiesTime = new int[3];
        homeTeamPenaltiesTime[0] = 0;
        homeTeamPenaltiesTime[1] = 0;
        homeTeamPenaltiesTime[2] = 0;

        int[] visitingTeamPenaltiesTime = new int[3];
        visitingTeamPenaltiesTime[0] = 0;
        visitingTeamPenaltiesTime[1] = 0;
        visitingTeamPenaltiesTime[2] = 0;


        for (Penalty penalty : penalties) {
            int period = penaltyService.getPeriod(penalty.getGameMinute());

            if (isHomeTeamPenalty(penalty, match)) {
                homeTeamPenaltiesTime[period - 1] += penalty.getTimeLength().getMinute();
            } else {
                visitingTeamPenaltiesTime[period - 1] += penalty.getTimeLength().getMinute();
            }
        }

        int finalPenaltyHome = homeTeamPenaltiesTime[0] + homeTeamPenaltiesTime[1] + homeTeamPenaltiesTime[2];
        int finalPenaltyVisiting = visitingTeamPenaltiesTime[0] + visitingTeamPenaltiesTime[1] + visitingTeamPenaltiesTime[2];

        String p1 = homeTeamPenaltiesTime[0] + ":" + visitingTeamPenaltiesTime[0];
        String p2 = homeTeamPenaltiesTime[1] + ":" + visitingTeamPenaltiesTime[1];
        String p3 = homeTeamPenaltiesTime[2] + ":" + visitingTeamPenaltiesTime[2];
        String finalMatchPenaltiesTable = finalPenaltyHome + ":" + finalPenaltyVisiting;

        PenaltiesTable penaltiesTable = PenaltiesTable.builder()
                .p1Penalties(p1)
                .p2Penalties(p2)
                .p3Penalties(p3)
                .finalPenalties(finalMatchPenaltiesTable)
                .build();

        return penaltiesTable;
    }



    private boolean isHomeTeamPenalty(Penalty penalty, Match match) {
        Team homeTeam = match.getHomeTeam();
        return penalty.getTeam().equals(homeTeam);
    }

    private boolean isHomeTeamGoal(Goal goal, Match match) {
        Team homeTeam = match.getHomeTeam();
        return goal.getTeam().equals(homeTeam);
    }


    public ScoreContainer getScore(Long matchId) {
        Match match = findById(matchId).get();
        List<Goal> goals = goalService.findByMatch(match);

        int[] homeTeamRegularGoals = new int[4];
        homeTeamRegularGoals[0] = 0;
        homeTeamRegularGoals[1] = 0;
        homeTeamRegularGoals[2] = 0;
        homeTeamRegularGoals[3] = 0;

        int[] homeTeamPowerPlayGoals = new int[4];
        homeTeamPowerPlayGoals[0] = 0;
        homeTeamPowerPlayGoals[1] = 0;
        homeTeamPowerPlayGoals[2] = 0;
        homeTeamPowerPlayGoals[3] = 0;

        int[] homeTeamShortHandedGoals = new int[4];
        homeTeamShortHandedGoals[0] = 0;
        homeTeamShortHandedGoals[1] = 0;
        homeTeamShortHandedGoals[2] = 0;
        homeTeamShortHandedGoals[3] = 0;

        int[] visitingTeamRegularGoals = new int[4];
        visitingTeamRegularGoals[0] = 0;
        visitingTeamRegularGoals[1] = 0;
        visitingTeamRegularGoals[2] = 0;
        visitingTeamRegularGoals[3] = 0;

        int[] visitingTeamPowerPlayGoals = new int[4];
        visitingTeamPowerPlayGoals[0] = 0;
        visitingTeamPowerPlayGoals[1] = 0;
        visitingTeamPowerPlayGoals[2] = 0;
        visitingTeamPowerPlayGoals[3] = 0;

        int[] visitingTeamShortHandedGoals = new int[4];
        visitingTeamShortHandedGoals[0] = 0;
        visitingTeamShortHandedGoals[1] = 0;
        visitingTeamShortHandedGoals[2] = 0;
        visitingTeamShortHandedGoals[3] = 0;

        for (Goal goal : goals) {
            int period = penaltyService.getPeriod(goal.getGameMinute());
            if (isHomeTeamGoal(goal, match)) {
                homeTeamRegularGoals[period - 1]++;
                if(goal.getSituation().isPowerplay()){
                    homeTeamPowerPlayGoals[period - 1]++;
                }else if(goal.getSituation().isShortHanded()){
                    homeTeamShortHandedGoals[period - 1]++;
                }
            } else {
                visitingTeamRegularGoals[period - 1]++;
                if(goal.getSituation().isPowerplay()){
                    visitingTeamPowerPlayGoals[period - 1]++;
                }else if(goal.getSituation().isShortHanded()){
                    visitingTeamShortHandedGoals[period - 1]++;
                }
            }
        }
        Score regularScore = getRegularScore(homeTeamRegularGoals, visitingTeamRegularGoals);
        Score powerPlayScore = getRegularScore(homeTeamPowerPlayGoals, visitingTeamPowerPlayGoals);
        Score shortHandedScore = getRegularScore(homeTeamShortHandedGoals, visitingTeamShortHandedGoals);

        return ScoreContainer.builder()
                .regular(regularScore)
                .powerPlay(powerPlayScore)
                .shortHanded(shortHandedScore)
                .build();
    }

    private Score getRegularScore(int[] homeTeamGoals, int[] visitingTeamGoals) {
        Score regularScore;
        int finalScore = homeTeamGoals[0] + homeTeamGoals[1] + homeTeamGoals[2] + homeTeamGoals[3];
        int finalLost = visitingTeamGoals[0] + visitingTeamGoals[1] + visitingTeamGoals[2] + visitingTeamGoals[3];

        String p1 = homeTeamGoals[0] + ":" + visitingTeamGoals[0];
        String p2 = homeTeamGoals[1] + ":" + visitingTeamGoals[1];
        String p3 = homeTeamGoals[2] + ":" + visitingTeamGoals[2];
        String p4 = homeTeamGoals[3] + ":" + visitingTeamGoals[3];
        String finalMatchScore = finalScore + ":" + finalLost;

        regularScore = Score.builder()
                .p1Score(p1)
                .p2Score(p2)
                .p3Score(p3)
                .p4Score(p4)
                .finalScore(finalMatchScore)
                .build();
        return regularScore;
    }

    public void updateMatchStatus(Long id, MatchStatus status) throws Exception {
        if (this.matchRepository.existsById(id)) {
            Match match = this.matchRepository.findById(id).orElse(null);

            // TODO:
            if (status == MatchStatus.IN_PROGRESS) {
//                if (match.getStatus() == MatchStatus.READY && (match.getStartTime().minusMinutes(5)).isBefore(LocalDateTime.now())) {
                if (match.getStatus() == MatchStatus.READY) {
                    match.setStatus(status);
                } else {
                    throw new Exception("Match is not ready");
                }
            }
            else if (status == MatchStatus.FINISHED) {
                if (match.getStatus() == MatchStatus.IN_PROGRESS) {
                    match.setStatus(status);
                } else {
                    throw new Exception("Match is not in progress");
                }
            }
//            else if (status == MatchStatus.READY){
//                match.setStatus(status);
//            }

            this.save(match);
        } else {
            throw new Exception("Match not found");
        }
    }

    public void updateMatchStatusAndChangeUserAcceptance(
            Long matchId,
            MatchStatus status,
            Long userId,
            boolean isAccepted
    ) throws Exception {
        if (this.matchRepository.existsById(matchId)) {
            Match match = this.matchRepository.findById(matchId).orElse(null);

            if (this.userService.existsById(userId)) {

                User user = this.userService.findById(userId).orElse(null);
                if (this.matchUserService.existsByUserAndMatch(user, match)) {
                    MatchUser matchUser = this.matchUserService.findMatchUserByUserAndMatch(user, match).orElse(null);

                    if (status == MatchStatus.FINISHED) {
                        if (match.getStatus() == MatchStatus.IN_PROGRESS) {
                            match.setStatus(status);
                        } else {
                            throw new Exception("Match is not in progress");
                        }
                    }

                    if (status == MatchStatus.DISAPPROVAL) {
                        if (match.getStatus() == MatchStatus.FINISHED || match.getStatus() == MatchStatus.CORRECTED) {
                            match.setStatus(status);
                        } else {
                            throw new Exception("Match is not finished or not corrected");
                        }
                    }

                    if (status == MatchStatus.CORRECTED) {
                        if (match.getStatus() == MatchStatus.DISAPPROVAL) {
                            resetUsersApprovalOnMatch(match);
                            match.setStatus(status);
                        } else {
                            throw new Exception("Match is not disapproved");
                        }
                    }

                    if (status == MatchStatus.CONFLICT) {
                        if (match.getStatus() == MatchStatus.DISAPPROVAL) {
                            match.setStatus(status);
                        } else {
                            throw new Exception("Match is not disapproved");
                        }
                    }

                    if (status == null) {
                        status = match.getStatus();
                    }

                    matchUser.setAccepted(isAccepted);
                    this.matchUserService.save(matchUser);
                    match.setStatus(status);
                    this.save(match);
                    checkIfEveryUserAccepted(match);

                } else if (userService.isAdmin(user)) {
                    match.setStatus(status);
                    this.save(match);
                } else {
                    throw new Exception("User not assigned to this match");
                }
            } else {
                throw new Exception("User not found");
            }
        } else {
            throw new Exception("Match not found");
        }
    }

    private void resetUsersApprovalOnMatch(Match match) {
        List<MatchUser> matchUsers = this.matchUserService.findAllByMatch(match);

        for (MatchUser matchUser : matchUsers) {
            matchUser.setAccepted(false);
            this.matchUserService.save(matchUser);
        }
    }

    private void checkIfEveryUserAccepted(Match match) {
        List<MatchUser> matchUsers = this.matchUserService.findAllByMatch(match);
        boolean isMatchAcceptedByAll = true;

        for (MatchUser matchUser : matchUsers) {
            if (!matchUser.isAccepted()) {
                isMatchAcceptedByAll = false;
            }
        }

        if (isMatchAcceptedByAll) {
            match.setStatus(MatchStatus.PROTOCOL_FINALIZED);
            this.save(match);
        }
    }

    public void addComment(Long matchId, Long userId, String message, LocalDateTime createdAt) throws Exception {
        if (this.matchRepository.existsById(matchId) && this.userService.existsById(userId)) {
            Match match = this.findById(matchId).orElse(null);
            User user = this.userService.findById(userId).orElse(null);

            MatchUser matchUser = this.matchUserService.findMatchUserByUserAndMatch(user, match).orElse(null);
            if (matchUser == null) {
                throw new Exception("User is not assigned to this match");
            }

            this.commentRepository.save(new Comment(message, createdAt, matchUser));
        } else {
            throw new Exception("Match or user not found");
        }
    }

    public List<Comment> findAllCommentsByMatchId(Long matchId) {
        Match match = this.findById(matchId).orElse(null);
        List<Comment> comments = this.commentRepository.findAllByMatchUser_Match(match);
        return comments;
    }

    public UserMatchesByStatusDTO getMatchesByUserId(Long userId) throws Exception {
        if (this.userService.existsById(userId)) {

            User user = this.userService.findById(userId).orElse(null);
            List<MatchUser> matchUsers = this.matchUserService.findAllByUser(user);
            List<UserMatchDTO> matchesDTO = new ArrayList<>();

            for (MatchUser matchUser : matchUsers) {
                matchesDTO.add(new UserMatchDTO(matchUser.getMatch()));
            }

            UserMatchesByStatusDTO matchesByStatus = new UserMatchesByStatusDTO();

            matchesByStatus.setCreated(matchesDTO.stream().filter(match -> match.getStatus().equals(MatchStatus.CREATED)).toList());
            matchesByStatus.setReady(matchesDTO.stream().filter(match -> match.getStatus().equals(MatchStatus.READY)).toList());
            matchesByStatus.setInProgress(matchesDTO.stream().filter(match -> match.getStatus().equals(MatchStatus.IN_PROGRESS)).toList());
            matchesByStatus.setFinished(matchesDTO.stream().filter(match -> match.getStatus().equals(MatchStatus.FINISHED)).toList());
            matchesByStatus.setProtocolFinalized(matchesDTO.stream().filter(match -> match.getStatus().equals(MatchStatus.PROTOCOL_FINALIZED)).toList());
            matchesByStatus.setDisapproval(matchesDTO.stream().filter(match -> match.getStatus().equals(MatchStatus.DISAPPROVAL)).toList());
            matchesByStatus.setCorrected(matchesDTO.stream().filter(match -> match.getStatus().equals(MatchStatus.CORRECTED)).toList());

            return matchesByStatus;
        } else {
            throw new Exception("User not found");
        }
    }

    public ActionAggregator findActionsByMatch(Match match) {

        ActionAggregator actions = new ActionAggregator();

        List<GoalToAggregationDTO> goals = getGoalsAggregation(match);
        actions.addActions(goals);

        List<BreakTimeToAggregationDTO> breakTimes = new ArrayList<>();
        for (BreakeTime breakeTime : match.getBreakeTimes()) {
            breakTimes.add(new BreakTimeToAggregationDTO(breakeTime));
        }
        actions.addActions(breakTimes);

        List<PenaltyToAggregationDTO> penalties = new ArrayList<>();
        for (Penalty penalty : match.getPenalties()) {
            penalties.add(new PenaltyToAggregationDTO(penalty));
        }
        actions.addActions(penalties);

        List<PenaltyShotToAggregationDTO> penaltyShots = new ArrayList<>();
        for (PenaltyShot penaltyShot : match.getPenaltyShots()) {
            penaltyShots.add(new PenaltyShotToAggregationDTO(penaltyShot));
        }
        actions.addActions(penaltyShots);


        List<PenaltyShotMissedToAggregationDTO> penaltyShotsMissed = new ArrayList<>();
        for (PenaltyShotMissed penaltyShotMissed : match.getPenaltyShotsMissed()) {
            penaltyShotsMissed.add(new PenaltyShotMissedToAggregationDTO(penaltyShotMissed));
        }
        actions.addActions(penaltyShotsMissed);

        List<ChangeGoalkeeperToAggregationDTO> changeGoalkeepers = new ArrayList<>();
        for (ChangeGolkiper changeGolkiper : match.getChangeGolkipers()) {
            changeGoalkeepers.add(new ChangeGoalkeeperToAggregationDTO(changeGolkiper));
        }
        actions.addActions(changeGoalkeepers);

        return actions;
    }

    private List<GoalToAggregationDTO> getGoalsAggregation(Match match) {
        Comparator<Goal> compareByGameMinuteAndSecond = Comparator
                .comparing(Goal::getGameMinute)
                .thenComparing(Goal::getGameSecond);

        List<GoalToAggregationDTO> goals = new ArrayList<>();
        Map<String, List<Goal>> sortedGoalsGroupByTeamName = match.getGoals()
                .stream()
                .sorted(compareByGameMinuteAndSecond)
                .collect(groupingBy(x -> x.getTeam().getName()));

        for (Map.Entry<String, List<Goal>> entry : sortedGoalsGroupByTeamName.entrySet()) {
            List<Goal> teamGoals = entry.getValue();
            int goalNo = 1;
            for (Goal teamGoal : teamGoals) {
                goals.add(new GoalToAggregationDTO(teamGoal, goalNo));
                goalNo++;
            }
        }

        return goals;
    }

    public Match findNearestUpcomingMatch() {
        List<Match> matches = this.matchRepository.findAll();
        LocalDateTime now = LocalDateTime.now();


        NavigableSet<LocalDateTime> dates = new TreeSet<LocalDateTime>();
        for (Match match : matches) {
            if (match.getStatus() == MatchStatus.CREATED || match.getStatus() == MatchStatus.READY) {
                dates.add(match.getStartTime());
            }
        }
        if (dates.size() == 1) {
            return this.matchRepository.findFirstByStartTime(dates.first()).orElse(null);
        }
        LocalDateTime highestDateUpUntilNow = dates.higher(now);
        Match nearestMatch = this.matchRepository.findFirstByStartTime(highestDateUpUntilNow).orElse(null);

        return nearestMatch;
    }

}

