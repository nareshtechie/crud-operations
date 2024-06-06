package com.springboot.exception;

import com.springboot.util.ErrorCode;

import java.util.Map;

public class CustomException  extends  RuntimeException{

    private ErrorCode errorCode;
    private Class<?> exceptionOccuredClass;

    private Map<String,Object> additionalProperties;

    public CustomException(ErrorCode errorCode, Class<?> exceptionOccuredClass) {
        this.errorCode = errorCode;
        this.exceptionOccuredClass = exceptionOccuredClass;
    }
    public CustomException(ErrorCode errorCode, Class<?> exceptionOccuredClass, Map<String, Object> additionalProperties) {
        this.errorCode = errorCode;
        this.exceptionOccuredClass = exceptionOccuredClass;
        this.additionalProperties = additionalProperties;
    }
}