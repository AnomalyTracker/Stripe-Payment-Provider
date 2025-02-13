package com.StripeProject.stripe_payment_provider_start.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }@Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Gson getGson(){
        return new Gson();
    }
}
