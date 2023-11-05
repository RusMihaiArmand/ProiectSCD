package ro.mihai.terminal;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TerminalData {


  @NotEmpty(message = "Terminal must have a name")
  private String terminalName;

}
