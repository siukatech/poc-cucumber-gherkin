Feature: Controller: Credit Assessment Calculator
  This is a simple credit assessment calculator feature

  Scenario Outline: Calculate Credit Assessment Score
    Given Controller: User performs a "POST" request to credit assessment score API "/creditservice/v1/calculator"
    When Controller: The calculateCreditAssessmentScore API is called with <numberOfEmployees>, "<companyType>", <numberOfYearsOperated> parameters
    Then Controller: The credit assessment score should match <creditScore> and "<expectedResult>"

  Examples:
    | numberOfEmployees | companyType               | numberOfYearsOperated | creditScore | expectedResult |
    | 6                 | Sole Proprietorship       | 5                     | 7           | true           |
    | 10                | Limited Liability Company | 8                     | 13          | true           |
    | 4                 | Partnership               | 10                    | 10          | true           |
    | 55                | Others                    | 25                    | 21          | true           |
    | 21                | Limited Liability Company | 2                     | 12          | true           |
    | 6                 | Sole Proprietorship       | 5                     | 9           | false          |
    | 10                | Limited Liability Company | 8                     | 15          | false          |
    | 4                 | Partnership               | 10                    | 18          | false          |
    | 55                | Others                    | 25                    | 24          | false          |
    | 21                | Limited Liability Company | 2                     | 15          | false          |

#    .... other scenarios to be updated by you including some negative test cases and exception scenarios...

