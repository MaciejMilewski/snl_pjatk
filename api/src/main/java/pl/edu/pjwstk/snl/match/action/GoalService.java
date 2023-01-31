package pl.edu.pjwstk.snl.match.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.match.Match;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;

    public List<Goal> findByMatch(Match match) {
        List<Goal> goals = goalRepository.findByMatch(match);
        return goals;
    }

}