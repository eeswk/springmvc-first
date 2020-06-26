package ee.swan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringFormController {

    @RequestMapping("/springForm")
    public String springForm() {
        return "/spring/form";
    }

}
