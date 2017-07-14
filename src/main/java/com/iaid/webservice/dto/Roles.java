package com.iaid.webservice.dto;

import java.util.Date;

/**
 * Created by Crawlers on 4/5/2016.
 */
public class Roles {
    private Integer id;
    private String name;
    private String displayName;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;
    private Boolean resyncOnLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getResyncOnLogin() {
        return resyncOnLogin;
    }

    public void setResyncOnLogin(Boolean resyncOnLogin) {
        this.resyncOnLogin = resyncOnLogin;
    }
}
