package com.doordash.merchant.campaign.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doordash.merchant.campaign.dao.api.PhoneNumberRepository;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.enums.ErrorCode;
import com.doordash.merchant.campaign.model.enums.PhoneNumberType;
import com.doordash.merchant.campaign.model.exceptions.BusinessException;
import com.doordash.merchant.campaign.service.api.PersistPhoneNumberService;

@Service
public class PersistPhoneNumberServiceImpl implements PersistPhoneNumberService {

  @Autowired
  private PhoneNumberRepository phoneNumberRepository;

  @Override
  public List<PhoneNumber> persistPhoneNumber(String rawPhoneNumber) throws BusinessException {
    List<PhoneNumber> phoneNumberList = new ArrayList<>();

    String[] phoneNumbers = rawPhoneNumber.replaceAll(" ", "").split("\\(");

    for (String num : phoneNumbers) {
      if(StringUtils.isBlank(num)){
        continue;
      }
      String[] number = num.split("\\)");
      if (number.length != 2) {
//        throw new BusinessException(ErrorCode.INVALID_INPUT);
        continue;
      }
      PhoneNumberType phoneNumberType = PhoneNumberType.findByValue(number[0]);
      String phoneNum = getPhoneNumber(number[1]);
      if (phoneNumberType == null || StringUtils.isBlank(phoneNum)) {
//        throw new BusinessException(ErrorCode.INVALID_INPUT);
        continue;
      }
      PhoneNumber phoneNumber =
          this.phoneNumberRepository.findByPhoneNumberAndPhoneType(phoneNum, phoneNumberType);
      if (phoneNumber != null) {
        phoneNumber.setOccurrences(phoneNumber.getOccurrences() + 1);
      } else {
        phoneNumber = PhoneNumber.builder().phoneNumber(phoneNum).occurrences(1)
            .phoneType(phoneNumberType).build();
      }
      phoneNumberList.add(this.phoneNumberRepository.save(phoneNumber));
    }
//
//     List<PhoneNumber> phoneNumberList = new ArrayList<>();
//     int rawNumberLength = rawPhoneNumber.length();
//
//     // Navigate to first (
//     int curr = 0;
//     while (curr < rawNumberLength && rawPhoneNumber.charAt(curr) != '(')
//     curr++;
//
//     for (curr = curr + 1; curr < rawNumberLength; curr++) {
//     // Get Type
//     StringBuilder type = new StringBuilder();
//     while (curr < rawNumberLength && rawPhoneNumber.charAt(curr) != ')') {
//     type.append(rawPhoneNumber.charAt(curr));
//     curr++;
//     }
//     // Get Number
//     StringBuilder number = new StringBuilder();
//     for (curr = curr + 1; curr < rawNumberLength && rawPhoneNumber.charAt(curr) != '('; curr++) {
//     char ch = rawPhoneNumber.charAt(curr);
//     if ((49 <= (int) ch) && ((int) ch <= 57)) {
//     number.append(ch);
//     }
//     }
//
//     PhoneNumberType phoneNumberType = PhoneNumberType.findByValue(type.toString());
//     if (phoneNumberType == null || StringUtils.isBlank(number.toString())) {
//     continue;
//     }
//
//     PhoneNumber phoneNumber = this.phoneNumberRepository
//     .findByPhoneNumberAndPhoneType(number.toString(), phoneNumberType);
//     if (phoneNumber != null) {
//     phoneNumber.setOccurrences(phoneNumber.getOccurrences() + 1);
//     } else {
//     phoneNumber = PhoneNumber.builder().phoneNumber(number.toString()).occurrences(1)
//     .phoneType(phoneNumberType).build();
//     }
//     phoneNumberList.add(this.phoneNumberRepository.save(phoneNumber));
//     }
    return phoneNumberList;
  }

  private String getPhoneNumber(String number) {
    String processedNum = number.replaceAll("-", "");
    int len = processedNum.length();
    StringBuilder num = new StringBuilder();
    for (int i = 0; i < len; i++) {
      char ch = processedNum.charAt(i);
      if ((49 <= (int) ch) && ((int) ch <= 57)) {
        num.append(ch);
      } else {
        return null;
      }
    }
    return num.toString();
  }
}
