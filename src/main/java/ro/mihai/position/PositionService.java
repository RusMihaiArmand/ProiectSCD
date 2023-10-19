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

/**
 * @author Radu Miron
 * @version 1
 */
@Service
public class PositionService {

  @Autowired
  private PositionRepository positionRepository;

//    @Transactional(rollbackFor = Exception.class)
//    public Position create(PositionDTO position) {
//
//        Position toSave = new Position();
//
//        toSave.setId(position.getId());
//        toSave.setCreationDate(position.getCreationDate());
//        toSave.setLatitude(position.getLatitude());
//        toSave.setLongitude(position.getLongitude());
//        toSave.setTerminalId(position.getTerminalId());
//
//        Position savedPos = positionRepository.save(toSave);
//
//        return PositionMapper.INSTANCE.mapPositionEtyToPositionDto(savedPos);
//        //return positionRepository.save(position);
//    }

//    public List<PositionDTO> getAll()
//    {
//        List<PositionDTO> dtoList = new ArrayList<>();
//
//        List<Position> positions = positionRepository.findAll();
//
//        dtoList = (positions.stream()
//            .map(PositionMapper.INSTANCE::mapPositionEtyToPositionDto)
//            .collect(Collectors.toList())
//        );
//
//
//        return dtoList;
//    }


  public List<PositionDTO> getPositions(String terminalIdParam, String startDateParam,
      String endDateParam) {
    //Date startDate, endDate;
    LocalDate startDate, endDate;
    try {
      startDate = LocalDate.parse(startDateParam);
      //startDate = new SimpleDateFormat("yyyy/MM/dd").parse(startDateParam);
    } catch (Exception e) {
      startDate = LocalDate.of(1999, 1, 1);
      //startDate =new Date();
    }

    try {
      endDate = LocalDate.parse(endDateParam);
      //endDate = new SimpleDateFormat("yyyy/MM/dd").parse(endDateParam);
    } catch (Exception e) {
      endDate = LocalDate.now().plusYears(1);
      //endDate =new Date().;
    }

    //System.out.println("START ----- " + startDate + "  ;   END ---- " + endDate);

    List<PositionDTO> dtoList = new ArrayList<>();

    List<Position> positions = positionRepository.findAll();

//        dtoList = (positions.stream()
//            .map(PositionMapper.INSTANCE::mapPositionEtyToPositionDto)
//            .collect(Collectors.toList())
//        );

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
              position.getTerminalId().toString().equals(terminalIdParam)

          )
          .map(PositionMapper.INSTANCE::mapPositionEtyToPositionDto)
          .collect(Collectors.toList())
      );
    }


  }


  @Transactional()
  public void create(PositionData positionData) {

    Position toSave = new Position();

    toSave.setCreationDate(Date.from(Instant.now()));
    toSave.setLatitude(positionData.getLatitude());
    toSave.setLongitude(positionData.getLongitude());
    toSave.setTerminalId(positionData.getTerminalId());

    //Position savedPos = positionRepository.save(toSave);

    PositionMapper.INSTANCE.mapPositionEtyToPositionDto(positionRepository.save(toSave));
    //return positionRepository.save(position);
  }

  @Transactional
  public PositionDTO edit(Integer id, PositionData updateData) {

    Position pos = positionRepository.findById(id).
        orElseThrow();

    System.out.println("CONTINUE-----------");

    pos.setLatitude(updateData.getLatitude());
    pos.setLongitude(updateData.getLongitude());
    pos.setTerminalId(updateData.getTerminalId());

    return PositionMapper.INSTANCE.mapPositionEtyToPositionDto(positionRepository
        .save(pos));

        /*

        EmployeeEty employee = loadEmployeeById(employeeId);

    LeaveRequestEty leaveRequest = checkLeaveRequestExists(employee, requestId);

    validateLeaveRequest(leaveRequest, editLeaveRequestDetails);

    if (isPendingOrApprovedLeaveRequest(leaveRequest)) {

      int countedDaysOff = checkCountedDaysOff(editLeaveRequestDetails, employee);

      setLeaveRequestFromDTO(leaveRequest, editLeaveRequestDetails, countedDaysOff);
    }

    return LeaveRequestMapper.INSTANCE.mapLeaveRequestEtyToLeaveRequestDto(leaveRequestRepository
        .save(leaveRequest));

         */

  }

  @Transactional
  public void delete(Integer id) {

    positionRepository.deleteById(id);


  }
}
