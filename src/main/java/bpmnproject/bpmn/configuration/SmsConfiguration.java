package bpmnproject.bpmn.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sms-configuration.properties")
public class SmsConfiguration {

}
