package com.doordash.merchant.campaign.rest.web.integrationsTests.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.doordash.merchant.campaign.dao.api.PhoneNumberRepository;
import com.doordash.merchant.campaign.model.entity.PhoneNumber;
import com.doordash.merchant.campaign.model.enums.PhoneNumberType;
import com.doordash.merchant.campaign.rest.web.integrationsTests.AbstractIntegrationTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PhoneNumberRepositoryTest extends AbstractIntegrationTest {

  private static final String PHONE_NUMBER = "4151360987";

  @Autowired
  private PhoneNumberRepository phoneNumberRepository;

  @Test
  public void savePhoneNumberTest() {
    PhoneNumber phoneNumber = PhoneNumber.builder().id(1L).phoneNumber(PHONE_NUMBER)
        .phoneType(PhoneNumberType.HOME).occurrences(2).build();

    PhoneNumber num = phoneNumberRepository.save(phoneNumber);

    assertEquals(PHONE_NUMBER, num.getPhoneNumber());
    assertEquals(PhoneNumberType.HOME, num.getPhoneType());
  }

  @Test
  public void getPhoneNumberByNumberAndTypeTest() {
    PhoneNumber phoneNumber = PhoneNumber.builder().id(1L).phoneNumber(PHONE_NUMBER)
        .phoneType(PhoneNumberType.HOME).occurrences(2).build();
    phoneNumberRepository.save(phoneNumber);

    PhoneNumber num =
        phoneNumberRepository.findByPhoneNumberAndPhoneType(PHONE_NUMBER, PhoneNumberType.HOME);
    assertEquals(PHONE_NUMBER, num.getPhoneNumber());
    assertEquals(PhoneNumberType.HOME, num.getPhoneType());
  }
}
