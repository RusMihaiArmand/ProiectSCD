package ro.mihai.terminal;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TerminalMapper {

  TerminalMapper INSTANCE = Mappers.getMapper(TerminalMapper.class);

  TerminalDTO mapTerminalEtyToTerminalDto(Terminal terminalEty);
  @Mapping(target = "positions", ignore = true)
  Terminal mapTerminalDtoToTerminalEty(TerminalDTO terminalDTO);
}
