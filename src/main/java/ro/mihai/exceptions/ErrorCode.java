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
  POSITION_NOT_FOUND("ERR-004", "Position does not exist.", HttpStatus.BAD_REQUEST);

  private final String errorCode;
  private final String devMsg;
  private final HttpStatus status;
}
