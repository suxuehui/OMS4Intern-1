package com.arvato.oms.ExceptionModel;

/**
 * Created by ZHAN545 on 2016/12/30.
 */
public class UpdateSqlException extends RuntimeException{
    public UpdateSqlException(String message) {
        super(message);
    }
}
