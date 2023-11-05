package ro.mihai.position;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mihai.exceptions.CodeException;
import ro.mihai.exceptions.ErrorCode;
import ro.mihai.terminal.Terminal;
import ro.mihai.terminal.TerminalRepository;


@Service
public class PositionService {

  @Autowired
  private PositionRepository positionRepository;

  @Autowired
  private TerminalRepository terminalRepository;


  public List<PositionDTO> getPositions(String terminalIdParam, String startDateParam,
      String endDateParam) {
    //Date startDate, endDate;
    LocalDate startDate, endDate;
    try {
      startDate = LocalDate.parse(startDateParam);
    } catch (Exception e) {
      startDate = LocalDate.of(1999, 1, 1);
    }

    try {
      endDate = LocalDate.parse(endDateParam);
    } catch (Exception e) {
      endDate = LocalDate.now().plusYears(1);
    }


    List<PositionDTO> dtoList = new ArrayList<>();

    List<Position> positions = positionRepository.findAll();



    LocalDate finalEndDate = endDate, finalStartDate = startDate;
    if (terminalIdParam == null) {

      return (positions.stream()
          .filter(position -> position.getCreationDate().toInstant()
              .atZone(ZoneId.systemDefault())
              .toLocalDate().isBefore(finalEndDate) &&
              position.getCreationDate().toInstant()
                  .atZone(ZoneId.systemDefault())
                  .toLocalDate().isAfter(finalStartDate)

          )
          .map(PositionMapper.INSTANCE::mapPositionEtyToPositionDto)
          .collect(Collectors.toList())
      );

    } else {


      return (positions.stream()
          .filter(position -> position.getCreationDate().toInstant()
              .atZone(ZoneId.systemDefault())
              .toLocalDate().isBefore(finalEndDate) &&
              position.getCreationDate().toInstant()
                  .atZone(ZoneId.systemDefault())
                  .toLocalDate().isAfter(finalStartDate) &&
              position.getTerminal().getId().toString().equals(terminalIdParam)

          )
          .map(PositionMapper.INSTANCE::mapPositionEtyToPositionDto)
          .collect(Collectors.toList())
      );


//      return (positions.stream()
//          .filter(position -> position.getCreationDate().toInstant()
//              .atZone(ZoneId.systemDefault())
//              .toLocalDate().isBefore(finalEndDate) &&
//              position.getCreationDate().toInstant()
//                  .atZone(ZoneId.systemDefault())
//                  .toLocalDate().isAfter(finalStartDate) &&
//              position.getTerminalId().toString().equals(terminalIdParam)
//
//          )
//          .map(PositionMapper.INSTANCE::mapPositionEtyToPositionDto)
//          .collect(Collectors.toList())
//      );
    }


  }


  @Transactional()
  public void create(PositionData positionData) throws Exception {

    Position toSave = new Position();

    toSave.setCreationDate(Date.from(Instant.now()));
    toSave.setLatitude(positionData.getLatitude());
    toSave.setLongitude(positionData.getLongitude());

    //toSave.setTerminalId(positionData.getTerminalId());
    Terminal terminal = loadTerminalById(positionData.getTerminalId());
    toSave.setTerminal(terminal);


    PositionMapper.INSTANCE.mapPositionEtyToPositionDto(positionRepository.save(toSave));
  }

  @Transactional
  public PositionDTO edit(Integer id, PositionData updateData) throws Exception {

    Position pos = positionRepository.findById(id)
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.POSITION_NOT_FOUND).build()));

    pos.setLatitude(updateData.getLatitude());
    pos.setLongitude(updateData.getLongitude());



    Terminal terminal = loadTerminalById(updateData.getTerminalId());
    pos.setTerminal(terminal);

    return PositionMapper.INSTANCE.mapPositionEtyToPositionDto(positionRepository
        .save(pos));

  }

  @Transactional
  public void delete(Integer id) {

    positionRepository.findById(id)
        .orElseThrow(() -> new CodeException(
        CodeException.CodeExceptionElement.builder()
            .errorDescription(ErrorCode.POSITION_NOT_FOUND).build()));

    positionRepository.deleteById(id);
  }



  public Terminal loadTerminalById(String id) throws Exception {
    return terminalRepository.findById(id)
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.TERMINAL_NOT_FOUND).build()));
  }

}
