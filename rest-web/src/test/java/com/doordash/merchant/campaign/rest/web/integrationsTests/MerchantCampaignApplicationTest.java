package com.doordash.merchant.campaign.rest.web.integrationsTests;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.doordash.merchant.campaign.model.constants.ApiPath;
import com.doordash.merchant.campaign.model.request.PersistPhoneNumberRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MerchantCampaignApplicationTest extends AbstractIntegrationTest {

  private static final String RAW_PHONE_NUMBER = "(Home) 415-123-1234 (Cell) 416-746-3698";

  @Autowired
  private MockMvc mockMvc;

  private PersistPhoneNumberRequest persistPhoneNumberRequest;

  @BeforeEach
  void setUp() {
    persistPhoneNumberRequest =
        PersistPhoneNumberRequest.builder().rawPhoneNumbers(RAW_PHONE_NUMBER).build();
  }

  @Test
  public void givenRawPhoneNumber_whenPersistPhoneNumber_thenListOfPhoneNumbers() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(ApiPath.INSERT_PHONE_NUMBER)
            .accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(this.persistPhoneNumberRequest)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.size()", CoreMatchers.is(2)));
  }
}
