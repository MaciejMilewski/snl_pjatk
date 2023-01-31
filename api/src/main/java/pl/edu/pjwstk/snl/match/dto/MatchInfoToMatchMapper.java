package pl.edu.pjwstk.snl.match.dto;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pl.edu.pjwstk.snl.match.Match;

@Mapper(componentModel = "spring")
public interface MatchInfoToMatchMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mappedMatchInfoToMatch(MatchInfo dto, @MappingTarget Match entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mappedCreateMatchInfoToMatch(CreateMatchInfoDto dto, @MappingTarget Match entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mappedMatchToMatchInfo(Match entity, @MappingTarget MatchInfo dto);
}