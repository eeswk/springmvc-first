package ee.swan.filter;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ClientInfoMdcPutFilter extends OncePerRequestFilter {

    @Autowired
    MessageSource messageSource;

    private static final String FORWARD_FOR_HEADER_NAME = "X-Forwarded-For";
    private String mdcKey = FORWARD_FOR_HEADER_NAME;

    public String getMdcKey() {
        return mdcKey;
    }

    public void setMdcKey(String mdcKey) {
        this.mdcKey = mdcKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String remoteIp = Optional.ofNullable(httpServletRequest.getHeader(FORWARD_FOR_HEADER_NAME))
                .orElse(httpServletRequest.getRemoteAddr());
        MDC.put(mdcKey, remoteIp);
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            MDC.remove(mdcKey);
        }


    }
}
