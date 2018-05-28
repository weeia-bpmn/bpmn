package bpmnproject.bpmn.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by Dawid on 28.05.2018 at 22:46.
 */

public class CreateRepairIsNotPossibleMessageDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String diagnosisDescription = (String) execution.getVariable("diagnosisDescription");
        execution.setVariable("clientMessage",
                "Telefonu nie można naprawic, powstała awaria jest z winy klienta.\n" +
                        "Diagnoza: \n" + diagnosisDescription + "\n" + "Telefon dostępny do odbioru od dnia: "
                        + LocalDate.now().plus(2L, ChronoUnit.DAYS).toString()
        );
    }
}
