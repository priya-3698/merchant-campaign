package com.doordash.merchant.campaign.model.enums;

public enum PhoneNumberType {

  CELL("cell"),
  HOME("home");

  private final String type;

  PhoneNumberType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
