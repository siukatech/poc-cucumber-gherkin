package com.siukatech.poc.cucumber.gherkin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MapperConfig {

//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = JsonMapper.builder()
//                .addModule(new JavaTimeModule())
//                .addModule(new Jdk8Module())
//                .disable(JsonGenerator.Feature.IGNORE_UNKNOWN)
//                .disable(JsonParser.Feature.IGNORE_UNDEFINED)
//                .build();
//        return objectMapper;
//    }

}
