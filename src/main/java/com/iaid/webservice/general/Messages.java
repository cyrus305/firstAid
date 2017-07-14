package com.iaid.webservice.general;

/**
 * Created by Mukesh Maharjan on 12/28/2015.
 */
public enum Messages {
  ONE_OR_MORE_FIELDS_ARE_NULL("One or more field is null"),
  NO_DATA_FOUND("No Data Found"),
  PHONE_ALREADY_REGISTERED("Phone already registered"),
  EMAIL_ALREADY_REGISTERED("Email already registered"),
  CITIZENSHIP_ALREADY_REGISTERED("Citizenship already registered"),
  USER_IS_NOT_REGISTERED("User is not registered"),
  ONE_OR_MORE_FIELDS_ARE_EMPTY("One or more field are empty"),
  USERNAME_AND_PASSWORD_DIDNT_MATCH("Username or Password didnt match");
  private String message;

  Messages(String msg) {
    this.setMessage(msg);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
