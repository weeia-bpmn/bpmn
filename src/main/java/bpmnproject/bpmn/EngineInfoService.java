package bpmnproject.bpmn;

import bpmnproject.bpmn.dto.CustomerPhoneInfo;
import bpmnproject.bpmn.utils.MapUtils;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Dawid on 05.06.2018 at 22:35.
 */
@Service
@RequiredArgsConstructor
public class EngineInfoService {
    private final RuntimeService service;

    public CustomerPhoneInfo getCustomerPhoneInf(String processId) {
        Map<String, Object> variables = service.getVariables(processId);
        return mapToCustomerInfo(variables);
    }

    public String getCustomerPhoneNumber(String processId) {
        return (String) service.getVariable(processId, "customerPhone");
    }

    private CustomerPhoneInfo mapToCustomerInfo(Map<String, Object> variables) {
        MapUtils mapUtils = new MapUtils(variables);
        return CustomerPhoneInfo
                .builder()
                .brand(mapUtils.getValue("phoneBrand"))
                .model(mapUtils.getValue("phoneModel"))
                .customerName(mapUtils.getValue("customerName"))
                .customerSurname(mapUtils.getValue("customerSurname"))
                .diagnosisDescription(mapUtils.getValue("diagnosisDescription"))
                .build();
    }

    public boolean clientPayExist(String processId) {
        return service.getVariable(processId, "clientPay") != null;
    }
}
