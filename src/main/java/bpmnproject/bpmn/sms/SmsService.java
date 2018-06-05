package bpmnproject.bpmn.sms;

import bpmnproject.bpmn.EngineInfoService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsService {

    @Value("${twilio.accountsid}")
    private String accountSid;
    @Value("${twilio.auth_token}")
    private String authToken;
    @Value("${twilio.senderNumber}")
    private String senderNumber;

    private void sendSms(String messageBody, String receiverNumber) {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(new PhoneNumber(receiverNumber),
                new PhoneNumber(senderNumber),
                messageBody).create();
    }


    public String generateRandomCode() {
        final int randomCode = new Random().nextInt(((9999 - 1000) + 1) + 1000);
        return String.valueOf(randomCode);
    }

    @Async
    public void sendVerificationSMSCode(String customerPhoneNumber, String code) {
        sendSms("Serwis gwarancyjny, kod potwierdzenia: " + code, customerPhoneNumber);
    }
}
