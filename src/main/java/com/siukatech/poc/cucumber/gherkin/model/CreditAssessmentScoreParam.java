package com.siukatech.poc.cucumber.gherkin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditAssessmentScoreParam {
    private Integer numberOfEmployees;
    private CompanyType companyType;
    private Integer numberOfYearsOperated;
}
