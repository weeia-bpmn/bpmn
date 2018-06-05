package bpmnproject.bpmn.delegate;

import bpmnproject.bpmn.mail.MailService;
import bpmnproject.bpmn.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Created by Dawid on 28.05.2018 at 22:56.
 */

@Component("endInvalidRepairMessageEventDelegate")
@RequiredArgsConstructor
public class EndInvalidRepairMessageEventDelegate implements JavaDelegate {
    private final MailService mailService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String customerEmail = (String) execution.getVariable("customerEmail");
        String message = (String) execution.getVariable("clientMessage");
        mailService.sendMessage(customerEmail, "Serwis gwarancyjny - naprawa niemożliwa", message);
    }
}
