package pl.edu.pjwstk.snl.strategy;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StrategyService {
    private final StrategyRepository strategyRepository;

    public List<Strategy> findAll() { return this.strategyRepository.findAll(); }

    public Optional<Strategy> findById(long id) {
        return this.strategyRepository.findById(id);
    }

    public void save(Strategy strategy) { this.strategyRepository.save(strategy); }

    public void deleteById(long id) {
        this.strategyRepository.deleteById(id);
    }

    public boolean existsById(long id) { return this.strategyRepository.existsById(id); }

    public List<Strategy> findByTeamId(long id) { return this.strategyRepository.findByTeamId(id); }

    public void deleteByTeamId(long id) {
        this.strategyRepository.deleteByTeamId(id);
    }
}
