package com.iaid.webservice.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

/**
 * Created by Crawlers on 8/30/2016.
 */
public class BloodDonation {
  private Long id;
  private Long userId;
  private String bg;
  private String fullName;
  private String location;
  private String dateFor;
  private String isEmergency;
  private String contactNumber;
  private String email;
  private Boolean status;
  private String remarks;
  private Boolean received;
  private String createdAt;
  private String updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getBg() {
    return bg;
  }

  public void setBg(String bg) {
    this.bg = bg;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDateFor() {
    return dateFor;
  }

  public void setDateFor(String dateFor) {
    this.dateFor = dateFor;
  }

  public String getIsEmergency() {
    return isEmergency;
  }

  public void setIsEmergency(String isEmergency) {
    this.isEmergency = isEmergency;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Boolean getReceived() {
    return received;
  }

  public void setReceived(Boolean received) {
    this.received = received;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
