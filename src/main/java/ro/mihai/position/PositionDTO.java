package ro.mihai.position;

import java.util.Date;
import lombok.Data;

@Data
public class PositionDTO {


  private Integer id;

  private String latitude;

  private String longitude;

  private String terminalId;

  private Date creationDate;
}
