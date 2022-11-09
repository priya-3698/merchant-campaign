package com.doordash.merchant.campaign.service.impl;

import java.util.ArrayList;
import java.util.List;

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

    String s = rawPhoneNumber.replaceAll("\\s","");
    String s1 = s.replaceAll("-","");

    System.out.println("-------------------------------");
    System.out.println(s1);
    System.out.println("-------------------------------");
    String[] phoneNumbers = s1.split("\\(|\\)");

    for (int i = 1; i < phoneNumbers.length; i = i + 2) {
      PhoneNumberType phoneNumberType = null;
      for (PhoneNumberType type : PhoneNumberType.values()) {
        if (type.getType().equalsIgnoreCase(phoneNumbers[i])) {
          phoneNumberType = type;
          break;
        }
      }
      if (phoneNumberType == null) {
        continue;
      }
      PhoneNumber phoneNumber = this.phoneNumberRepository.findByPhoneNumber(phoneNumbers[i + 1]);
      if (phoneNumber != null) {
        phoneNumber.setOccurrences(phoneNumber.getOccurrences() + 1);
      } else {
        phoneNumber = PhoneNumber.builder().phoneNumber(phoneNumbers[i + 1]).occurrences(1)
            .phoneType(phoneNumberType).build();
      }
      phoneNumberList.add(this.phoneNumberRepository.save(phoneNumber));
    }

    return phoneNumberList;
  }
}
