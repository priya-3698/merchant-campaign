package com.doordash.merchant.campaign.rest.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doordash.merchant.campaign.model.constants.ApiPath;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.request.PersistPhoneNumberRequest;
import com.doordash.merchant.campaign.model.response.PersistPhoneNumberResponse;
import com.doordash.merchant.campaign.model.response.PhoneNumberResponse;
import com.doordash.merchant.campaign.service.api.PersistPhoneNumberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class PhoneNumberController {

  @Autowired
  private Mapper mapper;

  @Autowired
  private PersistPhoneNumberService persistPhoneNumberService;

  @PostMapping(value = ApiPath.INSERT_PHONE_NUMBER, produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public PersistPhoneNumberResponse persistPhoneNumber(
      PersistPhoneNumberRequest persistPhoneNumberRequest) {
    try {
      List<PhoneNumber> phoneNumberList = this.persistPhoneNumberService
          .persistPhoneNumber(persistPhoneNumberRequest.getRawPhoneNumbers());
      List<PhoneNumberResponse> phoneNumberResponseList = new ArrayList<>();
      for (PhoneNumber phoneNumber : phoneNumberList) {
        phoneNumberResponseList.add(this.mapper.map(phoneNumber, PhoneNumberResponse.class));
      }
      return PersistPhoneNumberResponse.builder().results(phoneNumberResponseList).build();
    } catch (Exception e) {
      log.error("Error in persisting raw phone number", e);
      return null;
    }
  }
}
