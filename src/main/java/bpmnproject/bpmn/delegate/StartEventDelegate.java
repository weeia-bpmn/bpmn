package bpmnproject.bpmn.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

import java.util.UUID;

/**
 * Created by Dawid on 28.05.2018 at 21:52.
 */
public class StartEventDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String uuid = UUID.randomUUID().toString();
        System.out.println("Business key: " + uuid);
        ((ExecutionEntity) execution).setBusinessKey(uuid);
    }
}
