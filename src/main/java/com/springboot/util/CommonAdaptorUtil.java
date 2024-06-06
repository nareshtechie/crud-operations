package com.springboot.util;

import org.springframework.stereotype.Component;

@Component
public class CommonAdaptorUtil {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static CryptoResponse generateResponse(String Status, Object data, ErrorInfo errorInfo) {
        return new CryptoResponse(Status, data, errorInfo);

    }
    public static boolean isObjectNull(Object obj) {
        return obj == null;
    }
}

