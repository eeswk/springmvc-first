package ee.swan.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SuccessLoggingInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SuccessLoggingInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (logger.isInfoEnabled()) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = ((HandlerMethod) handler).getMethod();
            logger.info("[SUCCESS CONTROLLER] {}, {}"
                    , method.getDeclaringClass().getSimpleName()
                    , method.getName());
        }
    }
}
