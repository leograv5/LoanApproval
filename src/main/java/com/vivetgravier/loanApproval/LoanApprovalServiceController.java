package com.vivetgravier.loanApproval;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LoanApprovalServiceController {

    private static final String URL_APPROVAL_MANAGER = "urlApprovalManager";
    private static final String URL_ACCOUNT_MANAGER = "urlAccountManager";
    private static final String URL_CHECK_ACCOUNT = "https://vivetgravier-check-account.herokuapp.com/checkAccount";

    @RequestMapping(value = "/loanApproval", method = RequestMethod.GET)
    public ResponseEntity<String> loanApproval(@RequestParam(name="name") String name, @RequestParam(name="value") float value) {

        String risk = "", msg = "";
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        Map<?, ?> map = null;

        if (value < 10000) {
            try {
                map = objectMapper.readValue(restTemplate.getForObject(URL_ACCOUNT_MANAGER + "/accounts/get?lastname="+name, String.class), Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(map.toString(), HttpStatus.OK);
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
        }
    }
}
