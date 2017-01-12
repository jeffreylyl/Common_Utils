package com.app.utils.model;

public class UException extends Exception{
    String detailMessage;
    public UException(UExceptionType exceptionType) {
        super(exceptionType.getStringValue());
        this.detailMessage=exceptionType.getStringValue();
    }
    public String getDetailMessage() {
        return detailMessage;
    }
}
