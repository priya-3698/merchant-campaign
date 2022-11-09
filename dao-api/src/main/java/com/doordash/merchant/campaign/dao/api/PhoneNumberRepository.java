package com.doordash.merchant.campaign.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doordash.merchant.campaign.model.entity.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, String> {

  /**
   * find by phone number
   * 
   * @param phoneNumber phone number of the user
   * @return Phone Number entity for the users phone number
   */
  PhoneNumber findByPhoneNumber(String phoneNumber);
}
