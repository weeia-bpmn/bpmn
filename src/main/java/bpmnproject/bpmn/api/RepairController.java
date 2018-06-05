package bpmnproject.bpmn.api;

import bpmnproject.bpmn.EngineInfoService;
import bpmnproject.bpmn.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by Dawid on 29.05.2018 at 00:42.
 */
@Controller
@RequiredArgsConstructor
public class RepairController {
    private final HttpSession session;
    private final RuntimeService runtimeService;
    private final EngineInfoService engineInfoService;
    private final SmsService smsService;


    @PostMapping("/web/repair/{processId}")
    public String repairResponse(@PathVariable String processId, @RequestParam("code") String code,
                               @RequestParam("agreement") Boolean agreement, Model model) {

        String verifyCode = (String) session.getAttribute("code");
        if (verifyCode == null || !verifyCode.equals(code)) {
//            model.addAttribute("invalidCode", true);
            return showCustomerForm(processId, model, true);
        }

        System.out.println("Proces id: " + processId + " pay: " + agreement);
        MessageCorrelationResult result = runtimeService.createMessageCorrelation("pay_message")
                .processInstanceId(processId)
                .setVariable("clientPay", agreement)
                .correlateWithResult();
        return "acceptation";
    }

    @GetMapping("/web/repair/{processId}")
    public String showCustomerForm(@PathVariable("processId") String processId, Model model, boolean invalid) {
        model.addAttribute("customerPhoneInfo", engineInfoService.getCustomerPhoneInf(processId));
        model.addAttribute("processId", processId);
        model.addAttribute("invalidCode", invalid);
        generateCode(processId);
        return "customer-form";
    }

    private void generateCode(String processId) {
        String code = smsService.generateRandomCode();
        smsService.sendVerificationSMSCode(engineInfoService.getCustomerPhoneNumber(processId), code);
        session.setAttribute("code", code);
    }
}
