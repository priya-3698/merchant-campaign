package com.doordash.merchant.campaign.rest.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doordash.merchant.campaign.model.constants.ApiPath;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.enums.ErrorCode;
import com.doordash.merchant.campaign.model.request.PersistPhoneNumberRequest;
import com.doordash.merchant.campaign.model.response.BaseResponseModel;
import com.doordash.merchant.campaign.model.response.PhoneNumberResponse;
import com.doordash.merchant.campaign.service.api.MapperService;
import com.doordash.merchant.campaign.service.api.PersistPhoneNumberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class PhoneNumberController {

  @Autowired
  private MapperService mapper;

  @Autowired
  private PersistPhoneNumberService persistPhoneNumberService;

  @PostMapping(value = ApiPath.INSERT_PHONE_NUMBER, produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public BaseResponseModel<PhoneNumberResponse> persistPhoneNumber(
      @RequestBody PersistPhoneNumberRequest persistPhoneNumberRequest) {
    try {
      List<PhoneNumber> phoneNumberList = this.persistPhoneNumberService
          .persistPhoneNumber(persistPhoneNumberRequest.getRawPhoneNumbers());
      return new BaseResponseModel<>(
          this.mapper.mapEntityToList(phoneNumberList, PhoneNumberResponse.class));
    } catch (Exception e) {
      log.error("Error in persisting raw phone number", e);
      return new BaseResponseModel<>(ErrorCode.UNSPECIFIED.getCode(), e.getMessage());
    }
  }
}
