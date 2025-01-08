# Cucumber-Gherkin
This is a poc of `cucumber-gherkin`.  

## Setup
1. Create repository on Github.  
2. Copy previous `.gitignore`.  
3. Go to `Spring Initializr` https://start.spring.io/ and generate the project.  
4. This project selects the following dependencies.  
   1. Spring Security
   2. Spring OAuth2 Client
   3. Spring OAuth2 Resource Server
   4. Spring Web
5. Copy the content from zip to this empty project.  
6. Open folder by Intellij.  
7. Load as Gradle project.  
8. Add `lombok` support to build.gradle.  
```groovy
   dependencies {
     ...  
     implementation 'org.projectlombok:lombok:1.18.30'
     annotationProcessor 'org.projectlombok:lombok:1.18.30'
     annotationProcessor 'org.projectlombok:lombok-mapstruct-binding:0.2.0'
     ...  
   }
```
9. Add `mapstruct` support to build.gradle
```groovy
   dependencies {
     ... 
     implementation "org.mapstruct:mapstruct:${mapstructVersion}"
     annotationProcessor "org.mapstruct:mapstruct-processor:${mapstructVersion}"
     ...
   }
```
10. Add `cucumber` support to build.gradle
```groovy
   dependencies {
     ... 
     testImplementation "io.cucumber:cucumber-spring:${cucumberVersion}"
     testImplementation "io.cucumber:cucumber-java:${cucumberVersion}"
     testImplementation "io.cucumber:cucumber-junit:${cucumberVersion}"
     testImplementation "io.cucumber:cucumber-junit-platform-engine:${cucumberVersion}"
     ...
   }
```
11. Add `org.junit.vintage:junit-vintage-engine` support to build.gradle.
   Mainly adding support for `@Runwith(Cucumber.class)` with `junit5`.
```groovy
   dependencies {
     ... 
     testImplementation 'org.junit.vintage:junit-vintage-engine:5.11.3'
     ...
   }
```
12. Reload the gradle project.  


## Cucumber Gherkin
### Parameter Type
Refer to doc mentioned in reference, these are some example: {string}, {int}, {long}.  

**Reference:**  
https://github.com/cucumber/cucumber-expressions?tab=readme-ov-file#parameter-types


### Scenario Outline with space
A double quote is required to add to the `.feature` file, `"<companyType>"` for `{string}`.  
```gherkin
...
When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, "<companyType>", <numberOfYearsOperated> parameters
...
```
```java
...
@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters")
public void calculate_credit_assessment_score(
   Integer numberOfEmployees, String companyType, Integer numberOfYearsOperated) {
   ...
}
...
```



## SpringBootTest
### Weird Exception
#### PlaceholderResolutionException
```text
org.springframework.util.PlaceholderResolutionException: Could not resolve placeholder 'xxx' in value.  
```

#### Solution
Adding an `oauth2-tests-local.properties` in test `resources` to temporary resolve this.  

**Reference:**  
https://github.com/spring-attic/spring-native/issues/1577  




