package com.doordash.merchant.campaign.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.doordash.merchant.campaign.dao.api.PhoneNumberRepository;
import com.doordash.merchant.campaign.model.constants.Constants;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.enums.PhoneNumberType;

public class PersistPhoneNumberServiceImplTest {

  private static final String PHONE_NUMBER = "1234567890";

  @InjectMocks
  private PersistPhoneNumberServiceImpl persistPhoneNumberService;

  @Mock
  private PhoneNumberRepository phoneNumberRepository;

  private PhoneNumber phoneNumber;
  private PhoneNumber phoneNumber2;

  @BeforeEach
  void setUp() {
    openMocks(this);

    phoneNumber = PhoneNumber.builder().occurrences(1).phoneNumber(PHONE_NUMBER)
        .phoneType(PhoneNumberType.HOME).build();
    phoneNumber2 = PhoneNumber.builder().occurrences(1).phoneNumber(PHONE_NUMBER)
        .phoneType(PhoneNumberType.CELL).build();
  }

  @Test
  void persistPhoneNumberTest() {
    when(this.phoneNumberRepository.findByPhoneNumberAndPhoneType(PHONE_NUMBER,
        PhoneNumberType.HOME)).thenReturn(null);
    when(this.phoneNumberRepository.findByPhoneNumberAndPhoneType(PHONE_NUMBER,
        PhoneNumberType.CELL)).thenReturn(phoneNumber2);
    when(this.phoneNumberRepository.save(phoneNumber)).thenReturn(phoneNumber);
    when(this.phoneNumberRepository.save(phoneNumber2)).thenReturn(phoneNumber2);
    List<PhoneNumber> response = this.persistPhoneNumberService
        .persistPhoneNumber("(Home) 123-456-7890 (Cell) 123-456-7890");
    verify(this.phoneNumberRepository).findByPhoneNumberAndPhoneType(PHONE_NUMBER,
        PhoneNumberType.HOME);
    verify(this.phoneNumberRepository).save(phoneNumber);
    verify(this.phoneNumberRepository).findByPhoneNumberAndPhoneType(PHONE_NUMBER,
        PhoneNumberType.CELL);
    verify(this.phoneNumberRepository).save(phoneNumber2);
    assertEquals(Constants.TWO, response.size());
    assertEquals(PHONE_NUMBER, response.get(0).getPhoneNumber());
    assertEquals(PHONE_NUMBER, response.get(1).getPhoneNumber());
  }

  @Test
  void persistPhoneNumberTest_NullRawPhoneNumber() {
    List<PhoneNumber> response = this.persistPhoneNumberService.persistPhoneNumber(null);
    assertEquals(Constants.ZERO, response.size());
  }

  @Test
  void persistPhoneNumberTest_PhoneTypeInvalid_PhoneNumberInvalid() {
    List<PhoneNumber> response = this.persistPhoneNumberService
        .persistPhoneNumber("(Homee) 123-456-7890 (Cell) 123-456-789*0");
    assertEquals(Constants.ZERO, response.size());
  }

  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(this.phoneNumberRepository);
  }
}
