package bpmnproject.bpmn.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${twilio.accountsid}")
    private String accountSid;
    @Value("${twilio.auth_token}")
    private String authToken;
    @Value("${twilio.receiverNumber}")
    private String receiverNumber;
    @Value("${twilio.senderNumber}")
    private String senderNumber;

    @Async
    public void sendSMS(String messageBody){
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(new PhoneNumber(receiverNumber),
                new PhoneNumber(senderNumber),
                messageBody).create();
    }


}
