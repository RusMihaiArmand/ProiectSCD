package ro.mihai.exceptions;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeException extends RuntimeException {
  private final CodeExceptionElement error;
  public CodeException(Throwable cause, CodeExceptionElement error) {
    super(cause);
    this.error = error;
  }

  @Getter
  @AllArgsConstructor
  @Builder
  public static class CodeExceptionElement {

    private final ErrorCode errorDescription;
    private final Map<String, Object> contextVariables;
  }
}
