package com.springboot.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ErrorInfo {

    private String errorCode;
    private String errorDescription;
    List<String> fieldErrors;

    /**
     * @param errorCode
     * @param errorDescription
     * @param fieldErrors
     */
    public ErrorInfo(String errorCode, String errorDescription, List<String> fieldErrors) {

        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.fieldErrors = fieldErrors;
    }
}
