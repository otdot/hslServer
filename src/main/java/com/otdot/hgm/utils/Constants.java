package com.otdot.hgm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constants {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
