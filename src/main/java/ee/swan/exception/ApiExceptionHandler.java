package ee.swan.exception;

import ee.swan.api.ApiError;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageSource messageSource;

    private Map<Class<? extends Exception>, String> messageMappings =
            Collections.unmodifiableMap(new LinkedHashMap<>() {
                {
                    put(HttpMessageNotReadableException.class, "Request body is invalid");
                }
            });

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, ex.getMessage());
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ApiError createApiError(Exception ex, String defaultMessage) {
        ApiError apiError = new ApiError();
        apiError.setMessage(resolveMessage(ex, defaultMessage));
        apiError.setDocumentationUrl("http://localhost:8080/api/errors");
        return apiError;
    }

    private String resolveMessage(Exception ex, String defaultMessage) {
        return messageMappings.entrySet().stream()
                .filter(entry -> entry.getKey().isAssignableFrom(ex.getClass()))
                .findFirst().map(Map.Entry::getValue).orElse(defaultMessage);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBookNotFoundException(BookResourceNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
        ApiError apiError = createApiError(ex, "System error is occurred");
        return super.handleExceptionInternal(ex, apiError, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, ex.getMessage());
        ex.getBindingResult().getGlobalErrors().stream()
                .forEach(e -> apiError.addDetail(e.getObjectName(), getMessage(e, request)));
        ex.getBindingResult().getFieldErrors().stream()
                .forEach(e -> apiError.addDetail(e.getField(), getMessage(e, request)));
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private String getMessage(MessageSourceResolvable resolvable, WebRequest request) {
        return messageSource.getMessage(resolvable, request.getLocale());
    }
}
