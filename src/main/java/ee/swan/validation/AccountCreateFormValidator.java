package ee.swan.validation;

import ee.swan.controller.AccountCreateForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountCreateFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountCreateForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasFieldErrors("type")) {
            return;
        }
        AccountCreateForm form = AccountCreateForm.class.cast(target);
        if ("1".equals(form.getType())) {
            //일반회원
            if (StringUtils.hasLength(form.getCardNo())) {
                errors.rejectValue("cardNo", "baseAccountCardNoNot");
            }
        } else {
            //프리미엄 회원
            if (form.getCardNo() == null || form.getCardNo().length() < 14
                || form.getCardNo().length() >16 ) {
                errors.rejectValue("cardNo","primeAccountCardNoSize", new Object[] {14, 16}, null);
            }
        }
    }
}
