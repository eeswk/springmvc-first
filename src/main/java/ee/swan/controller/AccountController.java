package ee.swan.controller;

import ee.swan.validation.AccountCreateFormValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountCreateFormValidator accountCreateFormValidator;

/*
    @Autowired
    SmartValidator validator;
*/
    @RequestMapping(method = RequestMethod.GET)
    public String input(Model model) {
        AccountCreateForm form = new AccountCreateForm();
        model.addAttribute(form);
        return "account/input";
    }

    @RequestMapping(method = RequestMethod.POST, params="confirm")
    public String confrim(@Validated AccountCreateForm form, BindingResult result, Model model) {

        /*
        Class<?> validationGroup = null;
        //validationGroup을 결정하는 로직 구현

        validator.validate(form, result, validationGroup);
        */

        if (result.hasErrors()) {
            return "account/input";
        }
        model.addAttribute(form);
        return "account/confirm";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(accountCreateFormValidator);
    }

}
