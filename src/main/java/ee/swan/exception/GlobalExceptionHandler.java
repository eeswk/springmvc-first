package ee.swan.exception;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String handlerException(Exception e) {
        //오류를 처리한다.
        logger.error("System Error occurred.", e);
        return "error/appError";
    }
    
/*
    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
        ModelAndView mav = new ModelAndView("/error/notFoundError");
        mav.addObject("exception", e);
        //mav.addObject("errorcode", "404");
        return mav;
    }
*/

}
