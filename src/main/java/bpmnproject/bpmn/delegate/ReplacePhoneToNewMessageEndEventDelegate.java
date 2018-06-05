package bpmnproject.bpmn.delegate;

import bpmnproject.bpmn.mail.MailService;
import bpmnproject.bpmn.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by Dawid on 28.05.2018 at 23:54.
 */

@Component("replacePhoneToNewMessageEndEventDelegate")
@RequiredArgsConstructor
public class ReplacePhoneToNewMessageEndEventDelegate implements JavaDelegate {
    private final MailService mailService;
    private final SmsService smsService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String customerEmail = (String) execution.getVariable("customerEmail");
        String customerPhone = (String) execution.getVariable("customerPhone");
        String content = createContent(execution);

        mailService.sendMessage(customerEmail, "Wymiana telefonu na nowy - serwis gwarancyjny", content);
        smsService.sendSMS(content,customerPhone);
    }

    private String createContent(DelegateExecution execution) {
        Date date = (Date) execution.getVariable("phoneDeliveryDate");
        StringBuilder content = new StringBuilder();
        content.append("Telefon zostnie wymieniony na nowy. Będzie gotowy od odbioru od dnia: ")
                .append(date.toInstant().atOffset(ZoneOffset.UTC).toLocalDate().toString());

        String infoForClient = (String) execution.getVariable("infoForClient");

        if (infoForClient != null) {
            content.append("\n\n").append(infoForClient);
        }
        return content.toString();
    }
}
