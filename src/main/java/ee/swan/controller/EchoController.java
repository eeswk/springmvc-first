package ee.swan.controller;

import javax.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("echo")
public class EchoController {

    @RequestMapping(method = RequestMethod.GET)
    public String viewInput(Model model) {
        EchoForm form = new EchoForm();
        model.addAttribute(form);
        return "echo/input";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String echo(@Validated EchoForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "echo/input";
        }
        model.addAttribute(form);
        return "echo/output";
    }
/*
    @InitBinder("validMonth")
    public void initBiner(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyyMMdd"));
    }


    @InitBinder("no")
    public void initBiner(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

 */

}
