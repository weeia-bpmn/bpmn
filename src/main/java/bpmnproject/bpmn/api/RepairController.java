package bpmnproject.bpmn.api;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dawid on 29.05.2018 at 00:42.
 */
@RestController
@RequiredArgsConstructor
public class RepairController {
    private final RuntimeService runtimeService;

    @GetMapping("/repair/{businessKey}")
    public void repairResponse(@PathVariable String processId, @RequestParam Boolean pay) {
        System.out.println("Proces id: " + processId + " pay: " + pay);
        MessageCorrelationResult result = runtimeService.createMessageCorrelation("pay_message")
                .processInstanceId(processId)
                .setVariable("clientPay", pay)
                .correlateWithResult();
    }
}
