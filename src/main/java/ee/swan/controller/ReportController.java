package ee.swan.controller;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report")
public class ReportController {

    @RequestMapping(path = "make", params = "scope=daily")
    public String makeDailyReport(Locale locale) {

        return "report/complete";
    }
}
