package bpmnproject.bpmn.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Dawid on 30.03.2018 at 19:43.
 */

@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${application.url}")
    private String applicationUrl;

    private final JavaMailSender mailSender;
    private final MailMessageFactory mailMessageFactory;

    @Async
    public void sendMessage(String to, String title, String content) {
        SimpleMailMessage mailMessage = mailMessageFactory.create(title, to, content);
        mailSender.send(mailMessage);
    }
}
