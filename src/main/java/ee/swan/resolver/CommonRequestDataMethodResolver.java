package ee.swan.resolver;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CommonRequestDataMethodResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return  CommonRequestData.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpSession session = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getSession(false);

        String userAgent = nativeWebRequest.getHeader(HttpHeaders.USER_AGENT);
        String sessionId = Optional.ofNullable(session).map(HttpSession::getId).orElse(null);

        CommonRequestData commonRequestData = new CommonRequestData();
        commonRequestData.setUserAgent(userAgent);
        commonRequestData.setSessionId(sessionId);
        return commonRequestData;
    }
}
