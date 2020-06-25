package ee.swan.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

public class EqualsPropertyValuesValidator implements ConstraintValidator<EqualsPropertyValues, Object> {

    private String property;
    private String comparingProperty;
    private String message;

    public void initialize(EqualsPropertyValues constraintAnnotation) {
        this.property = constraintAnnotation.property();
        this.comparingProperty = constraintAnnotation.comparingProperty();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //두개의 프로퍼티 값을 가져와서 비교
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Object propertyValue = beanWrapper.getPropertyValue(property);
        Object comparingPropertyValue = beanWrapper.getPropertyValue(comparingProperty);
        boolean matched = ObjectUtils.nullSafeEquals(propertyValue, comparingPropertyValue);

        if (matched) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();;
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(property).addConstraintViolation();
            return false;
        }
    }

}
