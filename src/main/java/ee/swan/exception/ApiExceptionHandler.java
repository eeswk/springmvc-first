package ee.swan.exception;

import ee.swan.api.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex);
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ApiError createApiError(Exception ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setDocumentationUrl("http://localhost:8080/api/errors");
        return apiError;
    }
}
