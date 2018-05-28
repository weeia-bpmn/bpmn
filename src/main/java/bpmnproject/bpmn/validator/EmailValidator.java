package bpmnproject.bpmn.validator;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;

/**
 * Created by Dawid on 28.05.2018 at 20:38.
 */
public class EmailValidator implements FormFieldValidator {
    @Override
    public boolean validate(Object o, FormFieldValidatorContext formFieldValidatorContext) {
        String email = (String) o;
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
    }
}
