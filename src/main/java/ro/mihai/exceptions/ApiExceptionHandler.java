package ro.mihai.exceptions;

import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(CodeException.class)
  public ResponseEntity<ErrorDetail> handleBusinessException(CodeException exception) {
    ErrorDetail errorDetail = ErrorDetail.builder()
        .errorCode(exception.getError().getErrorDescription().getErrorCode())
        .message(exception.getError().getErrorDescription().getDevMsg())
        .contextVariables(exception.getError().getContextVariables())
        .build();
    return ResponseEntity.status(exception.getError().getErrorDescription().getStatus())
        .body(errorDetail);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetail> handleException(MethodArgumentNotValidException exception) {

    ErrorDetail errorDetail = ErrorDetail.builder()
        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
        .message(exception.getBindingResult().getAllErrors()
            .stream()
            .map(error -> Objects.requireNonNull(error.getDefaultMessage()))
            .collect(Collectors.joining(","))
        )
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
  }

}
