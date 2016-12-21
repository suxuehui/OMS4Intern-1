package com.arvato.oms.model;

import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/14.
 */
public class ErrorModel {
    private String oId;
    private String cause;

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


    @Override
    public String toString() {
        return "ErrorModel{" +
                "oId='" + oId + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }
}
