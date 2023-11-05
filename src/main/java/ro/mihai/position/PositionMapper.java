package ro.mihai.position;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ro.mihai.terminal.Terminal;
import ro.mihai.terminal.TerminalDTO;
import ro.mihai.terminal.TerminalMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PositionMapper {

  PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

  @Mapping(source = "terminal", target = "terminalData")
  PositionDTO mapPositionEtyToPositionDto(Position position);


  default Terminal terminalDtoToTerminalEty(TerminalDTO terminalDTO) {
    return TerminalMapper.INSTANCE.mapTerminalDtoToTerminalEty(terminalDTO);
  }
}
