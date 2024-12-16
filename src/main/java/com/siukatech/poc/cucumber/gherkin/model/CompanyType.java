package com.siukatech.poc.cucumber.gherkin.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;


@Slf4j
public enum CompanyType {

    SOLE_PROPRIETORSHIP("Sole Proprietorship")
    , PARTNERSHIP("Partnership")
    , LIMITED_LIABILITY_COMPANY("Limited Liability Company")
    , OTHERS("Others")
    ;

    CompanyType(String value) {
        this.value = value;
    }

    private final String value;

    @JsonValue
    public String getValue() { return this.value; }

    /**
     * If the JsonCreator method contains single parameter, mode MUST be Mode.DELEGATING
     * Otherwise mode is Mode.PROPERTIES
     * @param value
     * @return
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static CompanyType of(String value) {
        log.debug("of - value: [{}]", value);
        CompanyType companyType = Arrays.stream(values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("%s".formatted(value)))
                ;
        return companyType;
    }

}
