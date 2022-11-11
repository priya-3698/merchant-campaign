package com.doordash.merchant.campaign.model.enums;

public enum ErrorCode {

  UNSPECIFIED("Sorry, there is an error in our system"),
  INVALID_INPUT("Invalid input data");

  private String message;

  ErrorCode(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public String getCode() {
    return this.name();
  }
}
