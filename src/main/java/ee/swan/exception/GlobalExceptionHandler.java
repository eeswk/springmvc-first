package ee.swan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String handlerException(Exception e) {
        //오류를 처리한다.
        return "error/appError";
    }
    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public String handlerExceptionNotFound(Exception e) {
        //오류를 처리한다.
        return "error/appError";
    }

}
