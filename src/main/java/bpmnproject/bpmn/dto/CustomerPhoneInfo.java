package bpmnproject.bpmn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dawid on 05.06.2018 at 22:29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerPhoneInfo {
    private String brand;
    private String model;
    private String diagnosisDescription;
    private String customerName;
    private String customerSurname;
}
