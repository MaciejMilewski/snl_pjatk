package pl.edu.pjwstk.snl.match.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.match.Match;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyService {
    private final PenaltyRepository penaltyRepository;

    public List<Penalty> findByMatch(Match match) {
        List<Penalty> penalties = penaltyRepository.findByMatch(match);
        return penalties;
    }

    public int getPeriod(Integer gameMinute) {
        if (gameMinute < 20) {
            return 1;
        }
        if (gameMinute < 40) {
            return 2;
        }

        if (gameMinute < 60) {
            return 3;
        }

        return 4;
    }

}
