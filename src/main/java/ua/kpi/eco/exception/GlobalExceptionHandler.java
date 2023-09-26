package ua.kpi.eco.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ObjectNotFoundException.class, PollutantNotFoundException.class, PollutionNotFoundException.class})
    public String handleEntitiesNotFoundException(RuntimeException e) {
        log.error("Handling entity not found exception: " + e.getMessage());
        return e.getMessage();
    }
}
