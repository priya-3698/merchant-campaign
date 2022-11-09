package com.doordash.merchant.campaign.rest.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class SpringDataSourceProperties {

  private String driverClassName;
  private String url;
  private String username;
  private String password;
  private String dialect;
  private String showSql;
  private String ddlAuto;
  private String minIdle;
  private String maxIdle;
  private String initialSize;
  private String maxActive;
  private String maxWait;
}
