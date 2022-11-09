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

  @Autowired
  private SpringDataSourceProperties springDataSourceProperties;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(this.springDataSourceProperties.getDriverClassName());
    dataSource.setUrl(this.springDataSourceProperties.getUrl());
    dataSource.setUsername(this.springDataSourceProperties.getUsername());
    dataSource.setPassword(this.springDataSourceProperties.getPassword());
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
    vendorAdapter.setDatabasePlatform(this.springDataSourceProperties.getDialect());
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
    properties.setProperty("hibernate.hbm2ddl.auto", this.springDataSourceProperties.getDdlAuto());
    properties.setProperty("hibernate.dialect", this.springDataSourceProperties.getDialect());
    properties.setProperty("hibernate.show_sql", this.springDataSourceProperties.getShowSql());
    return properties;
  }
}
