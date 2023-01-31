package pl.edu.pjwstk.snl.match;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchPlayerService  {

    private final MatchPlayerRepository matchPlayerRepository;

    public Optional<MatchPlayer> findById(Long id) {
        return this.matchPlayerRepository.findById(id);
    }

}
