package com.doordash.merchant.campaign.model.exceptions;

import com.doordash.merchant.campaign.model.enums.ErrorCode;

public class BusinessException extends Exception {

  private static final long serialVersionUID = 403386668859972L;

  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public String getCode() {
    return this.errorCode.getCode();
  }

  public String getMessage() {
    return this.errorCode.getMessage();
  }
}
