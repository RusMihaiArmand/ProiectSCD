package ro.mihai.security;

import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class CredentialsDTO {

  @NotEmpty(message = "No username provided")
  private String username;

  @NotEmpty(message = "No password provided")
  private String password;
}
