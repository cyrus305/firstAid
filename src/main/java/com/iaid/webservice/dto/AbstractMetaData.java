package com.iaid.webservice.dto;

import java.util.Date;

/**
 * Created by Crawlers on 8/15/2016.
 */
public abstract class AbstractMetaData {
  private Integer id;
  private Date createdAt;
  private Date updatedAt;
  private Date createdBy;
  private Date updatedBy;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Date createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Date updatedBy) {
    this.updatedBy = updatedBy;
  }
}
