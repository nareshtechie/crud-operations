package com.springboot.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SigninResponse {
    
    private String message;
    
    private String token;
}
