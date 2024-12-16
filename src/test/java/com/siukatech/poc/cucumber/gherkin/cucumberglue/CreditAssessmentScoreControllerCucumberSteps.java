package com.siukatech.poc.cucumber.gherkin.cucumberglue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siukatech.poc.cucumber.gherkin.controller.CreditAssessmentScoreController;
import com.siukatech.poc.cucumber.gherkin.model.CompanyType;
import com.siukatech.poc.cucumber.gherkin.model.CreditAssessmentScoreParam;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j

// this is not working, start
//@WebMvcTest(value = {CreditAssessmentScoreController.class})
//@AutoConfigureMockMvc
//@WithMockUser
// this is not working, end

public class CreditAssessmentScoreControllerCucumberSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private RequestBuilder lastRequestBuilder;

    @Given("Controller: User performs a {string} request to credit assessment score API {string}")
    public void user_performs_a_request_to_credit_assessment_score_api(String httpMethod, String apiUrl) {
//        log.debug("user_performs_a_request_to_credit_assessment_score_api - httpMethod: [{}], apiUrl: [{}]"
//                , httpMethod, apiUrl);
    }

    @When("Controller: The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters")
    public void calculate_credit_assessment_score(
            Integer numberOfEmployees, String companyTypeStr, Integer numberOfYearsOperated
    ) throws JsonProcessingException {
        CompanyType companyType = CompanyType.of(companyTypeStr);
        CreditAssessmentScoreParam creditAssessmentScoreParam = new CreditAssessmentScoreParam();
        creditAssessmentScoreParam.setCompanyType(companyType);
        creditAssessmentScoreParam.setNumberOfEmployees(numberOfEmployees);
        creditAssessmentScoreParam.setNumberOfYearsOperated(numberOfYearsOperated);
        String creditAssessmentScoreParamStr = objectMapper.writeValueAsString(creditAssessmentScoreParam);
        //
        String loginId = "user01";
        String credentials = "NIL";
        List<SimpleGrantedAuthority> authorityList = Arrays.asList(
                new SimpleGrantedAuthority("AUTHORITY_01")
        );
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginId, credentials, authorityList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/creditservice/v1/calculator")
                .content(creditAssessmentScoreParamStr)
                .with(csrf())
                .with(authentication(authenticationToken))
                .contentType(MediaType.APPLICATION_JSON);

        lastRequestBuilder = requestBuilder;
    }

    @Then("Controller: The credit assessment score should match {int} and {string}")
    public void match_credit_assessment_score(Integer expectedCreditScore, String expectedResultStr) throws Exception {
        boolean expectedResult = Boolean.parseBoolean(expectedResultStr);
        Matcher<String> expectedResultMatcher = null;
//        expectedResultMatcher = containsString(expectedCreditScore.toString());
        expectedResultMatcher = expectedResult ?
                containsString(expectedCreditScore.toString())
                :
                not(containsString(expectedCreditScore.toString()))
                ;

        // then
        MvcResult mvcResult = mockMvc
                .perform(lastRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("creditScore")))
                .andExpect(content().string(expectedResultMatcher))
                .andReturn();

    }

}
