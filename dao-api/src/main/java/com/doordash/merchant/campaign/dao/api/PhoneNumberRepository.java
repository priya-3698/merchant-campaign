package com.doordash.merchant.campaign.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.enums.PhoneNumberType;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, String> {

  /**
   * find by phone number and phone number type
   * 
   * @param phoneNumber phone number of the user
   * @param phoneType type of the phone number
   * @return Phone Number entity for the users given phone number
   */
  PhoneNumber findByPhoneNumberAndPhoneType(String phoneNumber, PhoneNumberType phoneType);
}
