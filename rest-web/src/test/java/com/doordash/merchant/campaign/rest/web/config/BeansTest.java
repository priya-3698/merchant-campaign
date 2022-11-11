package com.doordash.merchant.campaign.rest.web.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

import org.dozer.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class BeansTest {

  @InjectMocks
  private Beans beans;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  public void dozerMapperTest() {
    Mapper result = this.beans.dozerMapper();
    assertNotNull(result);
  }
}
