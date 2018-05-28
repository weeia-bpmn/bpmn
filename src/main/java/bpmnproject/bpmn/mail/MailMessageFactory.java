package bpmnproject.bpmn.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Created by Dawid on 30.03.2018 at 20:01.
 */
@Component
@RequiredArgsConstructor
public class MailMessageFactory {
    public SimpleMailMessage create(String title, String to, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(title);
        mailMessage.setTo(to);
        mailMessage.setText(content);
        return mailMessage;
    }

}
