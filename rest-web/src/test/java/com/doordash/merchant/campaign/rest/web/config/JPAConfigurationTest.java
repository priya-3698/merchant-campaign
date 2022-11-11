package com.doordash.merchant.campaign.rest.web.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.transaction.PlatformTransactionManager;

import com.doordash.merchant.campaign.rest.web.properties.SpringDataSourceProperties;

public class JPAConfigurationTest {

  @InjectMocks
  private JPAConfiguration jpaConfiguration;

  @Mock
  private SpringDataSourceProperties springDataSourceProperties;;

  @BeforeEach
  void setUp() {
    openMocks(this);

    when(this.springDataSourceProperties.getDriverClassName()).thenReturn("org.postgresql.Driver");
    when(this.springDataSourceProperties.getUrl()).thenReturn("URL");
    when(this.springDataSourceProperties.getUsername()).thenReturn("Username");
    when(this.springDataSourceProperties.getPassword()).thenReturn("Password");
    when(this.springDataSourceProperties.getDialect())
        .thenReturn("org.hibernate.dialect.PostgreSQLDialect");
    when(springDataSourceProperties.getDdlAuto()).thenReturn("update");
    when(springDataSourceProperties.getShowSql()).thenReturn("true");
  }

  @Test
  void dataSourceTest() {
    DataSource dataSource = this.jpaConfiguration.dataSource();
    verify(this.springDataSourceProperties).getDriverClassName();
    verify(this.springDataSourceProperties).getUrl();
    verify(this.springDataSourceProperties).getUsername();
    verify(this.springDataSourceProperties).getPassword();
    assertNotNull(dataSource);
  }
  //
  // @Test
  // void entityManagerFactoryTest() {
  // EntityManagerFactory entityManagerFactory = this.jpaConfiguration.entityManagerFactory();
  // verify(this.springDataSourceProperties).getDriverClassName();
  // verify(this.springDataSourceProperties).getUrl();
  // verify(this.springDataSourceProperties).getUsername();
  // verify(this.springDataSourceProperties).getPassword();
  // verify(this.springDataSourceProperties, times(2)).getDialect();
  // verify(this.springDataSourceProperties).getDdlAuto();
  // verify(this.springDataSourceProperties).getShowSql();
  // assertNotNull(entityManagerFactory);
  // }


  @Test
  void transactionManagerTest() {
    PlatformTransactionManager platformTransactionManager =
        this.jpaConfiguration.transactionManager(null);
    verify(this.springDataSourceProperties).getDriverClassName();
    verify(this.springDataSourceProperties).getUrl();
    verify(this.springDataSourceProperties).getUsername();
    verify(this.springDataSourceProperties).getPassword();
    assertNotNull(platformTransactionManager);
  }

  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(this.springDataSourceProperties);
  }
}
