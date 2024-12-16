package com.siukatech.poc.cucumber.gherkin;

import com.siukatech.poc.cucumber.gherkin.controller.CreditAssessmentScoreController;
import com.siukatech.poc.cucumber.gherkin.service.CreditAssessmentScoreService;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"}
//        , glue = {"com.siukatech.poc.cucumber.gherkin.cucumberglue"}
)
@CucumberContextConfiguration
//@SpringBootTest(classes = {CreditAssessmentScoreService.class, CreditAssessmentScoreController.class})
@SpringBootTest
@ContextConfiguration(classes = App.class)
@TestPropertySource(
        locations = {"classpath:oauth2-tests-local.properties"}
)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class CreditAssessmentScoreCucumberTests {
}
