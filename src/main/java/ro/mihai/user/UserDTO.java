package ro.mihai.user;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

  @NotEmpty(message = "No username provided")
  private String username;

  @NotEmpty(message = "No password provided")
  private String password;
}
