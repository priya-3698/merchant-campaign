package com.doordash.merchant.campaign.service.api;

import java.util.List;

import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.exceptions.BusinessException;

public interface PersistPhoneNumberService {

  /**
   * to persist raw phone numbers
   * 
   * @param rawPhoneNumber raw phone number from merchant
   * @return list of persisted phone numbers
   */
  List<PhoneNumber> persistPhoneNumber(String rawPhoneNumber) throws BusinessException;
}
