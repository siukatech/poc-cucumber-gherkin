package com.siukatech.poc.cucumber.gherkin.cucumberglue;

import com.siukatech.poc.cucumber.gherkin.model.CompanyType;
import com.siukatech.poc.cucumber.gherkin.model.CreditAssessmentScoreParam;
import com.siukatech.poc.cucumber.gherkin.service.CreditAssessmentScoreService;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.javatuples.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Reference:
 * https://cucumber.io/docs/cucumber/configuration/?lang=java
 *
 * DataTableType
 */
//@Slf4j
public class CreditAssessmentScoreServiceCucumberSteps {

    private final CreditAssessmentScoreService creditAssessmentScoreService;
    private Integer lastCreditScore;

    public CreditAssessmentScoreServiceCucumberSteps(CreditAssessmentScoreService creditAssessmentScoreService) {
        this.creditAssessmentScoreService = creditAssessmentScoreService;
    }

    @DataTableType
    public Pair<CompanyType, Integer> pairCompanyTypeInteger(Map<String, String> entry) {
        String companyTypeStr = entry.get("companyType");
        CompanyType companyType = CompanyType.of(companyTypeStr);
        Integer score = Integer.parseInt(entry.get("score"));
//        log.debug("pairCompanyTypeScore - companyTypeStr: [{}], companyType: [{}], score: [{}]"
//                , companyTypeStr, companyType, score);
        return Pair.with(CompanyType.of(companyTypeStr), score);
    }

    @Given("Service: A list of companyTypeScoreMap")
    public void a_list_of_companyTypeScoreMap(List<Pair<CompanyType, Integer>> pairCompanyTypeIntegerList) {
        Map<CompanyType, Integer> map = pairCompanyTypeIntegerList.stream()
                .collect(Collectors.toMap(k -> (k.getValue0()), v -> (v.getValue1())))
                ;
        this.creditAssessmentScoreService.setCompanyTypeScoreMap(map);
    }

    @DataTableType
    public Pair<Pair<Integer, Integer>, Integer> pairPairInteger(Map<String, String> entry) {
        Integer min = Integer.parseInt(entry.get("min"));
        Integer max = Integer.parseInt(entry.get("max"));
        Integer score = Integer.parseInt(entry.get("score"));
//        log.debug("pairPairInteger - min: [{}], max: [{}], score: [{}]"
//                , min, max, score);
        return Pair.with(Pair.with(min, max), score);
    }

    @Given("Service: A list of numberOfEmployeesScoreMap")
    public void a_list_of_numberOfEmployeesScoreMap(List<Pair<Pair<Integer, Integer>, Integer>> pairPairIntegerList) {
        Map<Pair<Integer, Integer>, Integer> map = pairPairIntegerList.stream()
                .collect(Collectors.toMap(Pair::getValue0, Pair::getValue1))
                ;
        this.creditAssessmentScoreService.setNumberOfEmployeesScoreMap(map);
    }

//    @DataTableType
//    public Pair<Pair<Integer, Integer>, Integer> pairNumberOfYearsOperatedScore(Map<String, String> entry) {
//        return Pair.with(
//                Pair.with(Integer.parseInt(entry.get("min")), Integer.parseInt(entry.get("max")))
//                , Integer.parseInt(entry.get("score"))
//        );
//    }

    @Given("Service: A list of numberOfYearsOperatedScoreMap")
    public void a_list_of_numberOfYearsOperatedScoreMap(List<Pair<Pair<Integer, Integer>, Integer>> pairList) {
        Map<Pair<Integer, Integer>, Integer> map = pairList.stream()
                .collect(Collectors.toMap(Pair::getValue0, Pair::getValue1))
                ;
        this.creditAssessmentScoreService.setNumberOfYearsOperatedScoreMap(map);
    }

    /**
     * Reference:
     * https://github.com/cucumber/cucumber-expressions?tab=readme-ov-file#parameter-types
     *
     * @param numberOfEmployees
     * @param companyTypeStr
     * @param numberOfYearsOperated
     */
    @When("Service: The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters")
    public void calculate_credit_assessment_score(
            Integer numberOfEmployees, String companyTypeStr, Integer numberOfYearsOperated) {
        CompanyType companyType = CompanyType.of(companyTypeStr);
        Integer creditScore = creditAssessmentScoreService.calculateCreditAssessmentScore(
                new CreditAssessmentScoreParam(numberOfEmployees, companyType, numberOfYearsOperated)
        );
        lastCreditScore = creditScore;
    }

    @Then("Service: The credit assessment score should match {int} and {string}")
    public void match_credit_assessment_score(Integer expectedCreditScore, String expectedResultStr) {
        boolean expectedResult = Boolean.parseBoolean(expectedResultStr);
        if (expectedResult) {
            assertEquals(expectedCreditScore, lastCreditScore);
        }
        else {
            assertNotEquals(expectedCreditScore, lastCreditScore);
        }
    }

}
