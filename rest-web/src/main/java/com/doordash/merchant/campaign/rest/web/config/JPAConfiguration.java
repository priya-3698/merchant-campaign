package com.doordash.merchant.campaign.rest.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.doordash.merchant.campaign.rest.web.properties.SpringDataSourceProperties;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"com.doordash.merchant.campaign"})
@EnableJpaRepositories(basePackages = {"com.doordash.merchant.campaign"})
public class JPAConfiguration {

//  @Autowired
//  private SpringDataSourceProperties springDataSourceProperties;

  @Bean
  public DataSource dataSource() {
    Properties properties = new Properties();
    properties.setProperty("spring.datasource.initial-size", "10");
    properties.setProperty("spring.datasource.max-active", "40");
    properties.setProperty("spring.datasource.max-wait", "0");
    properties.setProperty("spring.datasource.min-idle", "0");
    properties.setProperty("spring.datasource.max-idle", "20");
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://localhost:5432/merchant_campaign");
    dataSource.setUsername("postgres");
    dataSource.setPassword("reactions");
    dataSource.setConnectionProperties(properties);
    return dataSource;
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan("com.doordash.merchant.campaign");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(false);
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
    entityManagerFactoryBean.setJpaProperties(jpaProperties());

    entityManagerFactoryBean.afterPropertiesSet();

    return entityManagerFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setDataSource(dataSource());
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }


  Properties jpaProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.ddl-auto", "create");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    properties.setProperty("hibernate.show_sql", "true");
    return properties;
  }
}
