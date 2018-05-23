package com.dotsite.modules.conf.entity;

/**
 * @Author: QNS
 * @Description: BaseBean
 * @Data: 2017/11/20 17:01
 **/
public abstract class BaseEntity extends Page {

    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void preInsert() {

    }


    public void preUpdate() {

    }

}
