package com.vivetgravier.loanApproval;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoanApprovalServiceController {
    private static List<String> tests = new ArrayList<>();
    static {
        tests.add("test");
        tests.add("commande");
        tests.add("fonctionne");
    }

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getTests () {
        return new ResponseEntity<>(this.tests, HttpStatus.OK);
    }
}
