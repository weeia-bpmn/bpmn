package bpmnproject.bpmn;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableProcessApplication
@EnableAsync
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	private RuntimeService runtimeService;

	@EventListener
	public void processPostDeploy(PostDeployEvent event) {
		runtimeService.startProcessInstanceByKey("warrantyService");
	}
}