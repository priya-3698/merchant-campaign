package com.doordash.merchant.campaign.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.response.PhoneNumberResponse;

public class MapperServiceImplTest {

  private static final String PHONE_NUMBER = "phone_number";

  @InjectMocks
  private MapperServiceImpl mapperService;

  @Mock
  private Mapper mapper;

  private PhoneNumber phoneNumber;
  private List<PhoneNumber> phoneNumberList;
  private PhoneNumberResponse phoneNumberResponse;

  @BeforeEach
  void setUp() {
    openMocks(this);

    this.phoneNumber = PhoneNumber.builder().phoneNumber(PHONE_NUMBER).build();
    this.phoneNumberList = new ArrayList<>();
    this.phoneNumberList.add(phoneNumber);
    this.phoneNumberResponse = PhoneNumberResponse.builder().phoneNumber(PHONE_NUMBER).build();
  }

  @Test
  void mapEntityToListTest() {
    when(this.mapper.map(this.phoneNumber, PhoneNumberResponse.class))
        .thenReturn(this.phoneNumberResponse);

    List<PhoneNumberResponse> phoneNumberResponseList =
        this.mapperService.mapEntityToList(this.phoneNumberList, PhoneNumberResponse.class);

    verify(this.mapper, times(this.phoneNumberList.size())).map(any(PhoneNumber.class),
        eq(PhoneNumberResponse.class));

    assertEquals(this.phoneNumberList.size(), phoneNumberResponseList.size());
    assertEquals(PHONE_NUMBER, phoneNumberResponseList.get(0).getPhoneNumber());
  }

  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(this.mapper);
  }
}
