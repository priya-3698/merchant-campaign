package com.doordash.merchant.campaign.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doordash.merchant.campaign.dao.api.PhoneNumberRepository;
import com.doordash.merchant.campaign.model.constants.Constants;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.enums.PhoneNumberType;
import com.doordash.merchant.campaign.service.api.PersistPhoneNumberService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersistPhoneNumberServiceImpl implements PersistPhoneNumberService {

  @Autowired
  private PhoneNumberRepository phoneNumberRepository;

  @Override
  public List<PhoneNumber> persistPhoneNumber(String rawPhoneNumber) {
    List<PhoneNumber> phoneNumberList = new ArrayList<>();
    if (StringUtils.isBlank(rawPhoneNumber)) {
      return phoneNumberList;
    }
    String[] phoneNumbers = rawPhoneNumber.replaceAll(Constants.SPACE, Constants.BLANK)
        .split(Constants.OPENING_BRACKET);

    for (String num : phoneNumbers) {
      String[] number = num.split(Constants.CLOSING_BRACKET);
      if (number.length != Constants.TWO) {
        log.warn("Skipping to persist number: {}", num);
        continue;
      }
      PhoneNumberType phoneNumberType = PhoneNumberType.findByValue(number[Constants.ZERO]);
      String phoneNum = getPhoneNumber(number[Constants.ONE]);
      if (!Objects.nonNull(phoneNumberType) || StringUtils.isBlank(phoneNum)) {
        log.warn("Skipping to persist number: {}", num);
        continue;
      }
      PhoneNumber phoneNumber =
          this.phoneNumberRepository.findByPhoneNumberAndPhoneType(phoneNum, phoneNumberType);
      if (Objects.nonNull(phoneNumber)) {
        phoneNumber.setOccurrences(phoneNumber.getOccurrences() + Constants.ONE);
      } else {
        phoneNumber = PhoneNumber.builder().phoneNumber(phoneNum).occurrences(Constants.ONE)
            .phoneType(phoneNumberType).build();
      }
      phoneNumberList.add(this.phoneNumberRepository.save(phoneNumber));
    }
    return phoneNumberList;
  }

  private String getPhoneNumber(String number) {
    String processedNum = number.replaceAll(Constants.DASH, Constants.BLANK);
    int len = processedNum.length();
    StringBuilder num = new StringBuilder();
    for (int i = Constants.ZERO; i < len; i++) {
      char ch = processedNum.charAt(i);
      if ((Constants.ZERO_ASCII <= (int) ch) && ((int) ch <= Constants.NINE_ASCII)) {
        num.append(ch);
      } else {
        return null;
      }
    }
    return num.toString();
  }
}
