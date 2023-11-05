package ro.mihai.terminal;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TerminalDTO {

  @NotEmpty(message = "No terminal id provided")
  private String id;

  @NotEmpty(message = "Terminal must have a name")
  private String terminalName;

}
