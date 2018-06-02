package bpmnproject.bpmn.delegate;

import bpmnproject.bpmn.mail.MailService;
import bpmnproject.bpmn.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Dawid on 29.05.2018 at 00:29.
 */

@Component("customerPayForRepairQuestionMessageDelegate")
@RequiredArgsConstructor
public class CustomerPayForRepairQuestionMessageDelegate implements JavaDelegate {
    private final MailService mailService;
    private final SmsService smsService;

    @Value("${application.url}")
    private String appUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String customerEmail = (String) execution.getVariable("customerEmail");

        String content = createContent(execution);

        mailService.sendMessage(customerEmail, "Naprawa niemożliwa - Serwis gwarancyjny", content);
        smsService.sendSMS(content);
    }

    private String createContent(DelegateExecution execution) {
        String diagnosisDescription = (String) execution.getVariable("diagnosisDescription");
        String content = "Naprawa gwarancyjna niemożliwa, powstała awaria jest z winy klienta.\n" +
                "Diagnoza: \n" + diagnosisDescription + "\n" + "\n\n Jeżeli chcesz naprawić na swój koszt kliknij tu -> "
                + getPayForRepair(true, execution) + "\n Jeżeli nie chcesz naprawić na swój koszt kliknij tutaj -> " + getPayForRepair(false, execution);
        return content;
    }

    private String getPayForRepair(Boolean repair, DelegateExecution execution) {
        return appUrl + "/repair/" + execution.getBusinessKey() + "?pay=" + repair.toString();
    }
}
