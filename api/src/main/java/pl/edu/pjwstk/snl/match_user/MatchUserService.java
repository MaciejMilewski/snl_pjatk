package pl.edu.pjwstk.snl.match_user;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.security.User.User;

@Service
@RequiredArgsConstructor
public class MatchUserService {
    private final MatchUserRepository matchUserRepository;

    public void save(MatchUser matchUser) {
        this.matchUserRepository.save(matchUser);
    }

    public boolean existsById(long id) {
        return this.matchUserRepository.existsById(id);
    }

    public List<MatchUser> findAll() {
        return this.matchUserRepository.findAll();
    }

    public Optional<MatchUser> findById(long id) {
        return this.matchUserRepository.findById(id);
    }

    public void deleteById(long id) {
        this.matchUserRepository.deleteById(id);
    }

    public void delete(MatchUser matchUser) {
        this.matchUserRepository.delete(matchUser);
    }

    public List<MatchUser> findAllByMatch(Match match) {
        return this.matchUserRepository.findAllByMatch(match);
    }

    public boolean existsByUserAndMatch(User user, Match match) {
        return this.matchUserRepository.existsByUserAndMatch(user, match);
    }

    public List<MatchUser> findAllByUser(User user) {
        return this.matchUserRepository.findAllByUser(user);
    }
    public Optional<MatchUser> findMatchUserByUserAndRole(User user, MatchRole role) {
        return this.matchUserRepository.findMatchUserByUserAndRole(user, role);
    }

    public Optional<MatchUser> findMatchUserByUserAndMatch(User user, Match match) {
        return this.matchUserRepository.findMatchUserByUserAndMatch(user, match);
    }
}
