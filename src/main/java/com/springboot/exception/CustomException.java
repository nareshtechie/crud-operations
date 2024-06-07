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
        System.out.println(" new  Line Added");

        System.out.println(" hi iam naresh explain the conflicts");
        System.out.println("Hi lets check now whether the conflicts will come or not");

    }
    public CustomException(ErrorCode errorCode, Class<?> exceptionOccuredClass, Map<String, Object> additionalProperties) {
        this.errorCode = errorCode;
        this.exceptionOccuredClass = exceptionOccuredClass;
        this.additionalProperties = additionalProperties;
    }
}
