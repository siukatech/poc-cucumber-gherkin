package com.siukatech.poc.cucumber.gherkin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siukatech.poc.cucumber.gherkin.controller.CreditAssessmentScoreController;
import com.siukatech.poc.cucumber.gherkin.model.CompanyType;
import com.siukatech.poc.cucumber.gherkin.model.CreditAssessmentScoreParam;
import com.siukatech.poc.cucumber.gherkin.service.CreditAssessmentScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CreditAssessmentScoreController.class})
@AutoConfigureMockMvc
//@SpringBootTest
@TestPropertySource(
        locations = {"classpath:oauth2-tests-local.properties"}
)
public class CreditAssessmentScoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext context;

    @MockitoBean
    private CreditAssessmentScoreService creditAssessmentScoreService;

    @Test
    public void contextLoads() {
    }

    @Test
    @WithMockUser
    public void postCalculateCreditAssessmentScore_basic() throws Exception {
        // given
        CreditAssessmentScoreParam creditAssessmentScoreParam = new CreditAssessmentScoreParam();
        creditAssessmentScoreParam.setCompanyType(CompanyType.LIMITED_LIABILITY_COMPANY);
        creditAssessmentScoreParam.setNumberOfEmployees(5);
        creditAssessmentScoreParam.setNumberOfYearsOperated(8);
        String creditAssessmentScoreParamStr = objectMapper.writeValueAsString(creditAssessmentScoreParam);
//        String loginId = "user01";
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, loginId);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/creditservice/v1/calculator")
                .content(creditAssessmentScoreParamStr)
                .with(csrf())
//                .with(authentication(authenticationToken))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        // then
        MvcResult mvcResult = mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("creditScore")))
                .andReturn();
    }
}
