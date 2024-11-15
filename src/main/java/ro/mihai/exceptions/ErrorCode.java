package ro.mihai.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  TERMINAL_NOT_FOUND("ERR-001", "Terminal not found.", HttpStatus.BAD_REQUEST),
  PASSWORD_NOT_MATCHING("ERR-002", "Password does not match.", HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND("ERR-003", "User does not exist.", HttpStatus.BAD_REQUEST),
  POSITION_NOT_FOUND("ERR-004", "Position does not exist.", HttpStatus.BAD_REQUEST),
  CANNOT_DELETE_TERMINAL("ERR-005", "Terminal has positions. Cannot delete.", HttpStatus.BAD_REQUEST),
  USERNAME_IN_USE("ERR-006", "Username already exists.", HttpStatus.BAD_REQUEST),
  SAME_PASSWORD("ERR-007", "New password cannot be old password.", HttpStatus.BAD_REQUEST),
  TERMINAL_ID_EXISTS("ERR-008", "This terminal id is already in use.", HttpStatus.BAD_REQUEST);

  private final String errorCode;
  private final String devMsg;
  private final HttpStatus status;
}
