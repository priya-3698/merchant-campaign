package com.doordash.merchant.campaign.model.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class BaseListResponseModel<T> implements Serializable {

  private static final long serialVersionUID = 12345062653424861L;

  private List<T> results;
  private boolean success;
  private String errorCode;
  private String errorMessage;

  public BaseListResponseModel(List<T> results) {
    this.results = results;
    this.success = true;
  }

  public BaseListResponseModel(String errorCode, String errorMessage) {
    this.success = false;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
