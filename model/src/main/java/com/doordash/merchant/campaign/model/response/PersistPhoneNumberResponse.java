package com.doordash.merchant.campaign.model.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistPhoneNumberResponse implements Serializable {

  private static final long serialVersionUID = 12345062653424861L;

  List<PhoneNumberResponse> results;
}
