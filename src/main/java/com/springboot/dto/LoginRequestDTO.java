package com.springboot.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequestDTO {

    private String email;

    private String password;
}
