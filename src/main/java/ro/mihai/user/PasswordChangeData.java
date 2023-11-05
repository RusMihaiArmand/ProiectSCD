package ro.mihai.user;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PasswordChangeData {

  @NotEmpty(message = "No username provided")
  private String username;

  @NotEmpty(message = "No password provided")
  private String password;

  @NotEmpty(message = "No new password provided")
  private String newPassword;

}
