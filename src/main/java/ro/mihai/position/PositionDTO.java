package ro.mihai.position;

import java.util.Date;
import lombok.Data;
import ro.mihai.terminal.TerminalDTO;

@Data
public class PositionDTO {

  private Integer id;

  private String latitude;

  private String longitude;

  private TerminalDTO terminalData;

  private Date creationDate;
}
