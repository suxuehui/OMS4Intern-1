package com.arvato.oms.model;

import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/14.
 */
public class ErrorModel {
    private String oId;
    private String cause;
    private List<String> aa;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public List<String> getAa() {
        return aa;
    }

    public void setAa(List<String> aa) {
        this.aa = aa;
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "oId='" + oId + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }
}
