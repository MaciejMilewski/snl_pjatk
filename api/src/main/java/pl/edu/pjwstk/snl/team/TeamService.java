package pl.edu.pjwstk.snl.team;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.five.Five;
import pl.edu.pjwstk.snl.five.FiveService;
import pl.edu.pjwstk.snl.league.League;
import pl.edu.pjwstk.snl.league.LeagueRepository;
import pl.edu.pjwstk.snl.match.MatchLineup;
import pl.edu.pjwstk.snl.match.MatchLineupService;
import pl.edu.pjwstk.snl.match.MatchService;
import pl.edu.pjwstk.snl.match.action.BreakeTime;
import pl.edu.pjwstk.snl.match.action.BreakeTimeRepository;
import pl.edu.pjwstk.snl.match.action.ChangeGolkiper;
import pl.edu.pjwstk.snl.match.action.ChangeGolkiperRepository;
import pl.edu.pjwstk.snl.match.action.Goal;
import pl.edu.pjwstk.snl.match.action.GoalRepository;
import pl.edu.pjwstk.snl.match.action.Penalty;
import pl.edu.pjwstk.snl.match.action.PenaltyRepository;
import pl.edu.pjwstk.snl.match.action.PenaltyShot;
import pl.edu.pjwstk.snl.match.action.PenaltyShotMissed;
import pl.edu.pjwstk.snl.match.action.PenaltyShotMissedRepository;
import pl.edu.pjwstk.snl.match.action.PenaltyShotRepository;
import pl.edu.pjwstk.snl.player.Player;
import pl.edu.pjwstk.snl.player.PlayerRepository;

import javax.transaction.Transactional;
import java.util.*;
import pl.edu.pjwstk.snl.security.User.User;
import pl.edu.pjwstk.snl.security.User.UserRepository;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final FiveService fiveService;
    private final UserRepository userRepository;
    private final LeagueRepository leagueRepository;
    private final MatchLineupService matchLineupService;
    private final BreakeTimeRepository breakeTimeRepository;
    private final ChangeGolkiperRepository changeGolkiperRepository;
    private final PenaltyRepository penaltyRepository;
    private final PenaltyShotRepository penaltyShotRepository;
    private final PenaltyShotMissedRepository penaltyShotMissedRepository;
    private final GoalRepository goalRepository;

    public Page<Team> findAll(Pageable paging) {
        return teamRepository.findAll(paging);
    }

    public List<Team> findAllNoPaging() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(long id) {
        return this.teamRepository.findById(id);
    }

    public void save(Team team) {
        if (this.teamRepository.existsByName(team.getName())) {
            throw new RuntimeException("Team already exists");
        }
        this.teamRepository.save(team);
    }

    public void saveAll(List<Team> teams) {
        this.teamRepository.saveAll(teams);
    }

    public void update(Team team) {
        this.teamRepository.save(team);
    }

    @Transactional
    public void deleteById(long teamId) {
        if (this.teamRepository.existsById(teamId)) {
            Team team = this.teamRepository.findById(teamId).orElse(null);

            List<Player> players = new ArrayList<>(team.getPlayers());
            List<Five> fives = new ArrayList<>(team.getFives());
            List<User> trainers = new ArrayList<>(team.getTrainers());
            List<League> leagues = new ArrayList<>(team.getLeagues());

            List<BreakeTime> breaks = this.breakeTimeRepository.findByTeamId(teamId);
            List<ChangeGolkiper> changes = this.changeGolkiperRepository.findByTeamId(teamId);
            List<Penalty> penalties = this.penaltyRepository.findByTeamId(teamId);
            List<PenaltyShotMissed> missedPenalties = this.penaltyShotMissedRepository.findByTeamId(teamId);
            List<PenaltyShot> penaltyShots = this.penaltyShotRepository.findByTeamId(teamId);
            List<Goal> goals = this.goalRepository.findByTeamId(teamId);

            for (Iterator<PenaltyShot> iterator = penaltyShots.iterator(); iterator.hasNext();) {
                PenaltyShot penaltyShot = iterator.next();
                penaltyShot.setTeam(null);
                this.penaltyShotRepository.save(penaltyShot);
            }

            for (Iterator<Goal> iterator = goals.iterator(); iterator.hasNext();) {
                Goal goal = iterator.next();
                goal.setTeam(null);
                this.goalRepository.save(goal);
            }

            for (Iterator<PenaltyShotMissed> iterator = missedPenalties.iterator(); iterator.hasNext();) {
                PenaltyShotMissed penaltyShotMissed = iterator.next();
                penaltyShotMissed.setTeam(null);
                this.penaltyShotMissedRepository.save(penaltyShotMissed);
            }

            for (Iterator<Penalty> iterator = penalties.iterator(); iterator.hasNext();) {
                Penalty penalty = iterator.next();
                penalty.setTeam(null);
                this.penaltyRepository.save(penalty);
            }

            for (Iterator<ChangeGolkiper> iterator = changes.iterator(); iterator.hasNext();) {
                ChangeGolkiper change = iterator.next();
                change.setTeam(null);
                this.changeGolkiperRepository.save(change);
            }

            for (Iterator<BreakeTime> iterator = breaks.iterator(); iterator.hasNext();) {
                BreakeTime breakeTime = iterator.next();
                breakeTime.setTeam(null);
                this.breakeTimeRepository.save(breakeTime);
            }

            // Set null on team for all assigned trainers
            for (Iterator<User> iterator = trainers.iterator(); iterator.hasNext();) {
                User user = iterator.next();
                user.setTeam(null);
                this.userRepository.save(user);
            }

            // Why iterator in for loop? Have you heard the story of ConcurrentModificationException? No?
            // It's not a story python user will tell you...
            for (Iterator<Player> iterator = players.iterator(); iterator.hasNext();) {
                Player player = iterator.next();
                player.removeTeam(teamId);
                this.playerRepository.save(player);
            }

            for (Iterator<League> iterator = leagues.iterator(); iterator.hasNext();) {
                League league = iterator.next();
                league.removeTeam(teamId);
                this.leagueRepository.save(league);
            }

            // Fives removal, if team is removed all fives are removed too.
            for (Iterator<Five> iterator = fives.iterator(); iterator.hasNext();) {
                Five five = iterator.next();
                this.fiveService.deleteById(five.getId());
            }

            this.teamRepository.deleteById(teamId);
        }
    }

    public boolean existsById(long id) { return this.teamRepository.existsById(id); }

    @Transactional
    public Optional<Team> findByName(String name) {
        return this.teamRepository.findByName(name);
    }

    public List<Team> findTeamsByPlayersId(Long id) {
        return this.teamRepository.findTeamsByPlayersId(id);
    }
    public List<Team> findTeamsByLeaguesId(Long id) {
        return this.teamRepository.findTeamsByLeaguesId(id);
    }
}
