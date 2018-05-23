package com.dotsite.modules.conf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Page implements Serializable {

    private String start;
    private String size;

    @JsonIgnore
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @JsonIgnore
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
