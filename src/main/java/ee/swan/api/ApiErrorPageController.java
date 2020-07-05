package ee.swan.api;

import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiErrorPageController {
    @RequestMapping("/error")
    public ApiError handleError(HttpServletRequest request) {

        String message;
        Exception ex = (Exception) request
                .getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request
                .getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (ex != null) {
            message = ex.getMessage(); //메시지를 가져오는 방법은 후에 더 개선한다.
        } else {
            if (Arrays.asList(HttpStatus.values()).stream()
                    .anyMatch(status -> status.value() == statusCode)) {
                message = HttpStatus.valueOf(statusCode).getReasonPhrase();
            } else {
                message = "Custom error(" + statusCode + ") is occurred!";
            }
        }

        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setDocumentationUrl("http://localhost:8088/api/errors");
        return apiError;
    }
}
