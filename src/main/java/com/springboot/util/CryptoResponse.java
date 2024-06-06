package com.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoResponse<T> implements Serializable
{

    private String status;
    private Date timestamp;
    private T  data;
    private ErrorInfo error;

    public CryptoResponse(String status,T data,ErrorInfo error)
    {
        this.timestamp=new Date();
        this.status=status;
        this.data=data;
        this.error=error;
    }
}

