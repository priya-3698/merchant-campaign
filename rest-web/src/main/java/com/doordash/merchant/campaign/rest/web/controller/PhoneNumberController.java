package com.doordash.merchant.campaign.rest.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doordash.merchant.campaign.model.constants.ApiPath;

@RestController
//@RequestMapping
public class PhoneNumberController {

  @PostMapping(value = ApiPath.INSERT_PHONE_NUMBER, produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public void persistPhoneNumber() {

  }
}
