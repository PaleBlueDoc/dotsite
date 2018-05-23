package com.dotsite.common.dto;



import com.dotsite.modules.conf.entity.BaseEntity;

import java.util.List;

public class TableData<T extends BaseEntity> {
    private long code;
    private String msg;
    private long count;
    private List<T> data;


    public TableData() {
    }

    public TableData(List<T> data) {
        this.code = 0;
        this.msg = "";
        this.count = data.size();
        this.data = data;
    }

    public TableData(long count, List<T> data) {
        this.code = 0;
        this.msg = "";
        this.count = count;
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
