package com.siukatech.poc.cucumber.gherkin.controller;

import com.siukatech.poc.cucumber.gherkin.model.CreditAssessmentScoreParam;
import com.siukatech.poc.cucumber.gherkin.model.CreditAssessmentScoreResult;
import com.siukatech.poc.cucumber.gherkin.service.CreditAssessmentScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditAssessmentScoreController {

    private final CreditAssessmentScoreService creditAssessmentScoreService;

    public CreditAssessmentScoreController(CreditAssessmentScoreService creditAssessmentScoreService) {
        this.creditAssessmentScoreService = creditAssessmentScoreService;
    }

    @PostMapping(value = "/creditservice/v1/calculator")
    public ResponseEntity<CreditAssessmentScoreResult> postCalculateCreditAssessmentScore(
            @RequestBody CreditAssessmentScoreParam creditAssessmentScoreParam) {
        Integer creditScore = this.creditAssessmentScoreService.calculateCreditAssessmentScore(creditAssessmentScoreParam);
        CreditAssessmentScoreResult creditAssessmentScoreResult = new CreditAssessmentScoreResult();
        creditAssessmentScoreResult.setCreditScore(creditScore);
        return ResponseEntity.ok(creditAssessmentScoreResult);
    }
}
