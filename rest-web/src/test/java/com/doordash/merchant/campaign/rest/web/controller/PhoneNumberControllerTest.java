package com.doordash.merchant.campaign.rest.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.doordash.merchant.campaign.model.constants.ApiPath;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.request.PersistPhoneNumberRequest;
import com.doordash.merchant.campaign.model.response.PhoneNumberResponse;
import com.doordash.merchant.campaign.service.api.MapperService;
import com.doordash.merchant.campaign.service.api.PersistPhoneNumberService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PhoneNumberControllerTest {

  private static final String RAW_PHONE_NUMBER = "(Home) 415-415-1234 (Cell) 415-746-7891";

  @InjectMocks
  private PhoneNumberController phoneNumberController;

  @Mock
  private MapperService mapper;

  @Mock
  private PersistPhoneNumberService persistPhoneNumberService;

  private MockMvc mockMvc;
  private PersistPhoneNumberRequest persistPhoneNumberRequest;
  private List<PhoneNumber> phoneNumberList;
  private List<PhoneNumberResponse> phoneNumberResponseList;

  @BeforeEach
  void setUp() {
    openMocks(this);

    this.mockMvc = MockMvcBuilders.standaloneSetup(this.phoneNumberController).build();

    this.persistPhoneNumberRequest =
        PersistPhoneNumberRequest.builder().rawPhoneNumbers(RAW_PHONE_NUMBER).build();
    this.phoneNumberList = new ArrayList<>();
    this.phoneNumberResponseList = new ArrayList<>();
  }

  @Test
  void persistPhoneNumberTest_Success() throws Exception {
    when(this.persistPhoneNumberService.persistPhoneNumber(RAW_PHONE_NUMBER))
        .thenReturn(phoneNumberList);
    when(this.mapper.mapEntityToList(phoneNumberList, PhoneNumberResponse.class))
        .thenReturn(phoneNumberResponseList);

    this.mockMvc
        .perform(post(ApiPath.INSERT_PHONE_NUMBER).accept(APPLICATION_JSON_VALUE)
            .contentType(APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(this.persistPhoneNumberRequest)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(true)));

    verify(this.persistPhoneNumberService).persistPhoneNumber(RAW_PHONE_NUMBER);
    verify(this.mapper).mapEntityToList(phoneNumberList, PhoneNumberResponse.class);
  }

  @Test
  void persistPhoneNumberTest_Exception() throws Exception {
    when(this.persistPhoneNumberService.persistPhoneNumber(RAW_PHONE_NUMBER))
        .thenThrow(new RuntimeException());

    this.mockMvc
        .perform(post(ApiPath.INSERT_PHONE_NUMBER).accept(APPLICATION_JSON_VALUE)
            .contentType(APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(this.persistPhoneNumberRequest)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(false)));

    verify(this.persistPhoneNumberService).persistPhoneNumber(RAW_PHONE_NUMBER);
  }

  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(this.mapper, this.persistPhoneNumberService);
  }
}
