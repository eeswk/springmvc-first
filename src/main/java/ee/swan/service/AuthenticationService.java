package ee.swan.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Value("${auth.failureCountToLock:5}")
    private String failureCountToLock;

    public String getFailureCountToLock() {
        return failureCountToLock;
    }
}
