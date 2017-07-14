package com.iaid.webservice.dto;

import java.util.Date;

/**
 * Created by Crawlers on 4/5/2016.
 */
public class AnotherMedia extends AbstractMetaData{
    private MediaType mediaType;
    private String media;
    private String data;
    private Boolean status;

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
