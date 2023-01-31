package pl.edu.pjwstk.snl.match.dto.ActionAggregator;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionAggregator<T> {
    List<T> actions = new ArrayList<>();

    public void addActions(List<T> actions) {
        this.actions.addAll(actions);
    }
}
