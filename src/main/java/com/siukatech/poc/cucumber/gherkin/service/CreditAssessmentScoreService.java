package com.siukatech.poc.cucumber.gherkin.service;

import com.siukatech.poc.cucumber.gherkin.model.CompanyType;
import com.siukatech.poc.cucumber.gherkin.model.CreditAssessmentScoreParam;
import lombok.Data;
import org.javatuples.Pair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class CreditAssessmentScoreService implements InitializingBean {

    private static final Map<CompanyType, Integer> DEFAULT_COMPANY_TYPE_SCORE_MAP = Map.of(
            CompanyType.SOLE_PROPRIETORSHIP, 1
            , CompanyType.PARTNERSHIP, 3
            , CompanyType.LIMITED_LIABILITY_COMPANY, 5
            , CompanyType.OTHERS, 0
    );

    private static final Map<Pair<Integer, Integer>, Integer> DEFAULT_NUMBER_OF_EMPLOYEES_SCORE_MAP = Map.of(
            Pair.with(0, 1), 1
            , Pair.with(2, 5), 2
            , Pair.with(6, 10), 3
            , Pair.with(11, 50), 5
            , Pair.with(51, 200), 8
            , Pair.with(201, Integer.MAX_VALUE), 13
    );

    private static final Map<Pair<Integer, Integer>, Integer> DEFAULT_NUMBER_OF_YEARS_OPERATED_SCORE_MAP = Map.of(
            Pair.with(0, 1), 1
            , Pair.with(2, 3), 2
            , Pair.with(4, 6), 3
            , Pair.with(7, 10), 5
            , Pair.with(11, Integer.MAX_VALUE), 13
    );

    private Map<CompanyType, Integer> companyTypeScoreMap;
    private Map<Pair<Integer, Integer>, Integer> numberOfEmployeesScoreMap;
    private Map<Pair<Integer, Integer>, Integer> numberOfYearsOperatedScoreMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setCompanyTypeScoreMap(DEFAULT_COMPANY_TYPE_SCORE_MAP);
        this.setNumberOfEmployeesScoreMap(DEFAULT_NUMBER_OF_EMPLOYEES_SCORE_MAP);
        this.setNumberOfYearsOperatedScoreMap(DEFAULT_NUMBER_OF_YEARS_OPERATED_SCORE_MAP);
    }

    public Integer calculateCreditAssessmentScore(CreditAssessmentScoreParam creditAssessmentScoreParam) {
        Integer companyTypeScore = this.getCompanyTypeScoreMap().get(creditAssessmentScoreParam.getCompanyType());
        Integer numberOfEmployeesScore = this.getNumberOfEmployeesScoreMap().entrySet().stream()
                .filter(es -> {
                    Integer number = creditAssessmentScoreParam.getNumberOfEmployees();
                    Integer min = es.getKey().getValue0();
                    Integer max = es.getKey().getValue1();
                    boolean result = number >= min && number <= max;
                    return result;
                })
                .findFirst()
                .get().getValue();
        Integer numberOfYearsOperatedScore = this.getNumberOfYearsOperatedScoreMap().entrySet().stream()
                .filter(es -> {
                    Integer number = creditAssessmentScoreParam.getNumberOfYearsOperated();
                    Integer min = es.getKey().getValue0();
                    Integer max = es.getKey().getValue1();
                    boolean result = number >= min && number <= max;
                    return result;
                })
                .findFirst()
                .get().getValue();
        Integer creditAssessmentScore = companyTypeScore + numberOfEmployeesScore + numberOfYearsOperatedScore;
        return creditAssessmentScore;
    }

}
