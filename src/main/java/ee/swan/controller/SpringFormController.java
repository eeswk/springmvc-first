package ee.swan.controller;

import ee.swan.resolver.CommonRequestData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringFormController {

    @RequestMapping("/springForm")
    public String springForm(CommonRequestData commonRequestData) {
        System.out.println("userAgent: " + commonRequestData.getUserAgent());
        System.out.println("sessionId: " + commonRequestData.getSessionId());
        return "/spring/form";
    }

}
