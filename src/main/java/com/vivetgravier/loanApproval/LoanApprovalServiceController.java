package com.vivetgravier.loanApproval;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoanApprovalServiceController {

    private static final String URL_APPROVAL_MANAGER = "urlApprovalManager";
    private static final String URL_ACCOUNT_MANAGER = "urlAccountManager";
    private static final String URL_CHECK_ACCOUNT = "https://vivetgravier-check-account.herokuapp.com/checkAccount";

    @RequestMapping(value = "/loanApproval", method = RequestMethod.GET)
    public String loanApproval(@RequestParam(name="name") String name, @RequestParam(name="value") float value) {

        RestTemplate restTemplate = new RestTemplate();
        String risk = restTemplate.getForObject(URL_CHECK_ACCOUNT+"?name="+name+"&value="+value, String.class);
        return risk;
        /*String risk = "", msg = "";
        RestTemplate restTemplate = new RestTemplate();

        if (value < 10000) {
            risk = restTemplate.getForObject(URL_ACCOUNT_MANAGER + "/getRisk/"+name, String.class);
        }

        if (risk == "LOW") {
            restTemplate.put(URL_ACCOUNT_MANAGER+"/addMoney/"+name+value, null);
            return new ResponseEntity<>("Approved", HttpStatus.OK);
        }

        boolean approval = false;
        if (risk == "HIGH" || value >= 10000) {
             approval = (boolean) restTemplate.getForObject(URL_APPROVAL_MANAGER, Boolean.class);
        }

        if (approval) {
            return new ResponseEntity<>("Approved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Refused", HttpStatus.OK);
        }*/
    }
}
