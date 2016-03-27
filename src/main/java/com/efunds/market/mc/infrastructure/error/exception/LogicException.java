package com.efunds.market.mc.infrastructure.error.exception;

/**
 * Created by luoqi on 2016/3/26.
 */
public class LogicException extends RuntimeException{
    private String retCode;




    public LogicException(String msg) {
        super(msg);

    }

    public LogicException(String retCode, String msg) {
        super(msg);
        this.retCode = retCode;
    }



    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

}
