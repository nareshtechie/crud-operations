package com.springboot.service;

import com.springboot.dto.LoginRequestDTO;
import com.springboot.dto.NewCustomerRegistrationRequest;
import com.springboot.dto.NewCustomerRegistrationResponse;
import com.springboot.dto.SigninResponse;
import com.springboot.models.CustomerEntity;

import java.util.List;
import java.util.Map;

public interface RegisterService {

    NewCustomerRegistrationResponse createNewUser(NewCustomerRegistrationRequest createRequest);

    SigninResponse signin(LoginRequestDTO loginRequestDTO);
     Map<String,String> deleteUserById(Long id);

     List<CustomerEntity> findUsers(Map<String,Object>filterParams);
}
