package pl.edu.pjwstk.snl.match_user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.security.User.User;

public interface MatchUserRepository extends JpaRepository<MatchUser, Long> {
    List<MatchUser> findAllByMatch(Match match);
    List<MatchUser> findAllByUser(User user);

    Optional<MatchUser> findMatchUserByUserAndRole(User user, MatchRole role);

    Optional<MatchUser> findMatchUserByUserAndMatch(User user, Match match);

    boolean existsByUserAndMatch(User user, Match match);
}
