package com.bcanon.employee.controller.config;

import com.bcanon.employee.model.entity.Business;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${api.url.employeelist}")
    private String employeeList;

    @Value("${api.url.employeeid}")
    private String employeeid;
    
    @Bean
    public String employeeList(){
        return employeeList;
    }
    
    @Bean
    public String employeeid(){
        return employeeid;
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public Business business(){
        return new Business();
    }
}