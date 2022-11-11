package com.doordash.merchant.campaign.service.impl;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.doordash.merchant.campaign.dao.api.PhoneNumberRepository;

public class PersistPhoneNumberServiceImplTest {

  @InjectMocks
  private PersistPhoneNumberServiceImpl persistPhoneNumberService;

  @Mock
  private PhoneNumberRepository phoneNumberRepository;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void persistPhoneNumberTest() {

  }

  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(this.phoneNumberRepository);
  }
}
