package com.doordash.merchant.campaign.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doordash.merchant.campaign.dao.api.PhoneNumberRepository;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.enums.PhoneNumberType;
import com.doordash.merchant.campaign.service.api.PersistPhoneNumberService;

@Service
public class PersistPhoneNumberServiceImpl implements PersistPhoneNumberService {

  @Autowired
  private PhoneNumberRepository phoneNumberRepository;

  @Override
  public List<PhoneNumber> persistPhoneNumber(String rawPhoneNumber) {
    List<PhoneNumber> phoneNumberList = new ArrayList<>();

    int n = rawPhoneNumber.length();

    // Navigate to first (
    int i = 0;
    while (i < n && rawPhoneNumber.charAt(i) != '(')
      i++;

    // String s = rawPhoneNumber.replaceAll("\\s|-", "");
    // String[] phoneNumbers = s.split("\\(|\\)");

    for (i = i + 1; i < n; i++) {

      // Get Type
      StringBuilder type = new StringBuilder();
      while (i < n && rawPhoneNumber.charAt(i) != ')') {
        type.append(rawPhoneNumber.charAt(i));
        i++;
      }

      // Get Number
      StringBuilder number = new StringBuilder();
      for (i = i + 1; i < n && rawPhoneNumber.charAt(i) != '('; i++) {
        char ch = rawPhoneNumber.charAt(i);
        if ((49 <= (int) ch) && ((int) ch <= 57)) {
          number.append(ch);
        }
      }

      if (StringUtils.isBlank(number.toString())) {
        continue;
      }

      PhoneNumberType phoneNumberType = PhoneNumberType.findByValue(type.toString());

      if (phoneNumberType == null) {
        continue;
      }

      // Have number and type pair by this point. We are fetching from DB to increment or saving it
      // in the database
      PhoneNumber phoneNumber = this.phoneNumberRepository
          .findByPhoneNumberAndPhoneType(number.toString(), phoneNumberType);
      if (phoneNumber != null) {
        phoneNumber.setOccurrences(phoneNumber.getOccurrences() + 1);
      } else {
        phoneNumber = PhoneNumber.builder().phoneNumber(number.toString()).occurrences(1)
            .phoneType(phoneNumberType).build();
      }

      // Save the newly created phone number entry or existing phone number entry with updated
      // occurrence into the DB
      phoneNumberList.add(this.phoneNumberRepository.save(phoneNumber));
    }

    return phoneNumberList;
  }
}
