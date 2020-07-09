package ee.swan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
public class WebApplicationIntegrationTest {

    @Autowired
    MockServletContext mockServletContext; //테스트 케이스의 메서드 간에 공유됨

    @Autowired
    MockHttpSession mockHttpSession;

    @Autowired
    MockHttpServletRequest mockHttpServletRequest;


}