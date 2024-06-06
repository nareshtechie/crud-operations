package com.springboot.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NewCustomerRegistrationResponse {

    private String message;
    private String name;
    private String email;
    private String password;
    private String city;
    private String state;
    private String country;
}
