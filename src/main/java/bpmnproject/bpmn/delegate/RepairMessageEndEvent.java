package bpmnproject.bpmn.delegate;

import bpmnproject.bpmn.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by Dawid on 29.05.2018 at 17:57.
 */

@Component("repairMessageEndEvent")
@RequiredArgsConstructor
public class RepairMessageEndEvent implements JavaDelegate {
    private final MailService mailService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String customerEmail = (String) execution.getVariable("customerEmail");

        String content = createContent(execution);

        mailService.sendMessage(customerEmail, "Naprawa telefonu - serwis gwarancyjny", content);

    }

    private String createContent(DelegateExecution execution) {
        Date date = (Date) execution.getVariable("phoneDeliveryDate");
        StringBuilder content = new StringBuilder();
        content.append("Telefon zostnie naprawiony. Będzie gotowy od odbioru od dnia: ")
                .append(date.toInstant().atOffset(ZoneOffset.UTC).toLocalDate().toString());

        String infoForClient = (String) execution.getVariable("infoForClient");

        if (infoForClient != null) {
            content.append("\n\n").append(infoForClient);
        }
        return content.toString();
    }
}
