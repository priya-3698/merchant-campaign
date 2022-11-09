package com.doordash.merchant.campaign.rest.web.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public Mapper dozerMapper() {
    return new DozerBeanMapper();
  }
}
