package ro.mihai.exceptions;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetail {

  private String message;
  private String errorCode;
  private Map<String, Object> contextVariables;
}