package com.doordash.merchant.campaign.rest.web.integrationsTests;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    private static void startContainers() {
      Startables.deepStart(Stream.of(postgres)).join();
    }

    private static Map<String, String> createConnectionConfiguration() {
      Map<String, String> map = new HashMap<>();
      map.put("spring.datasource.url", postgres.getJdbcUrl());
      map.put("spring.datasource.username", postgres.getUsername());
      map.put("spring.datasource.password", postgres.getPassword());
      map.put("spring.datasource.ddlAuto", "validate");
      map.put("spring.datasource.dialect", "org.hibernate.dialect.PostgreSQLDialect");
      map.put("spring.datasource.showSql", "true");
      return map;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      startContainers();
      ConfigurableEnvironment environment = applicationContext.getEnvironment();
      MapPropertySource testcontainers =
          new MapPropertySource("testcontainers", (Map) createConnectionConfiguration());
      environment.getPropertySources().addFirst(testcontainers);
    }
  }
}
