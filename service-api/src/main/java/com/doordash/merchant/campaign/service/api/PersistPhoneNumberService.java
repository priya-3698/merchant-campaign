package com.doordash.merchant.campaign.service.api;

import java.util.List;

import com.doordash.merchant.campaign.model.entity.PhoneNumber;

public interface PersistPhoneNumberService {

  /**
   * to persist the raw phone numbers
   * 
   * @param rawPhoneNumber raw phone number from merchant
   * @return list of persisted phone numbers
   */
  List<PhoneNumber> persistPhoneNumber(String rawPhoneNumber);
}
