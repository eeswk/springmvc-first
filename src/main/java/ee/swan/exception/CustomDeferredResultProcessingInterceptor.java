package ee.swan.exception;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptorAdapter;

public class CustomDeferredResultProcessingInterceptor extends DeferredResultProcessingInterceptorAdapter {

    @Override
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        deferredResult.setResult((T) "error/timeoutError");
        return false;
    }
}
