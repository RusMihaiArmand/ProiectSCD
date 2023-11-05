package ro.mihai.position;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PositionData {

  @NotEmpty(message = "Latitude must have a value")
  private String latitude;

  @NotEmpty(message = "Longitude must have a value")
  private String longitude;

  @NotEmpty(message = "No terminal id provided")
  private String terminalId;

}
