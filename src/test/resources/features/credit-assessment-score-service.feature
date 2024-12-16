Feature: Service: Credit Assessment Calculator for service
  This is a simple credit assessment calculator feature

  Scenario Outline: Service: Calculate Credit Assessment Score
    Given Service: A list of companyTypeScoreMap
      | companyType               | score |
      | Sole Proprietorship       | 1     |
      | Partnership               | 3     |
      | Limited Liability Company | 5     |
      | Others                    | 0     |
    Given Service: A list of numberOfEmployeesScoreMap
      | min | max        | score |
      | 0   | 1          | 1     |
      | 2   | 5          | 2     |
      | 6   | 10         | 3     |
      | 11  | 50         | 5     |
      | 51  | 200        | 8     |
#      | 201 | 99999999999 | 13    |
      | 201 | 2147483647 | 13    |
    Given Service: A list of numberOfYearsOperatedScoreMap
      | min | max        | score |
      | 0   | 1          | 1     |
      | 2   | 3          | 2     |
      | 4   | 6          | 3     |
      | 7   | 10         | 5     |
      | 11  | 2147483647 | 13    |
    When Service: The calculateCreditAssessmentScore API is called with <numberOfEmployees>, "<companyType>", <numberOfYearsOperated> parameters
    Then Service: The credit assessment score should match <creditScore> and "<expectedResult>"

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

