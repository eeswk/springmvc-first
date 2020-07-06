package ee.swan.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import ee.swan.validation.AccountCreateFormValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/accounts")
@SessionAttributes(types = AccountCreateForm.class)
public class AccountCreateController {

    @ModelAttribute("accountCreateForm")
    public AccountCreateForm setUpAccountCreateForm() {
        return new AccountCreateForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String registeForm(Model model){
        AccountCreateForm form = new AccountCreateForm();
        model.addAttribute(form);
        return "account/registerForm";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public String create(@Validated AccountCreateForm from,
                         BindingResult result,
                         @ModelAttribute("passowrd") String password,
                         RedirectAttributes redirectAttributes) {

        return "redirect:/accounts/create?complete";
    }

    @RequestMapping(path = "create", params = "complete", method = RequestMethod.GET)
    public String createComplete(

            SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "account/createComplete";
    }
}
