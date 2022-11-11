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

  public static PhoneNumberType findByValue(String value) {
    PhoneNumberType phoneNumberType = null;
    for (PhoneNumberType type : values()) {
      if (type.getType().equalsIgnoreCase(value)) {
        phoneNumberType = type;
        break;
      }
    }
    return phoneNumberType;
  }
}
