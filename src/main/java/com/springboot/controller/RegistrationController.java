package com.springboot.controller;

import com.springboot.dto.LoginRequestDTO;
import com.springboot.dto.NewCustomerRegistrationRequest;
import com.springboot.dto.NewCustomerRegistrationResponse;
import com.springboot.dto.SigninResponse;
import com.springboot.exception.CustomException;
import com.springboot.models.CustomerEntity;
import com.springboot.service.RegisterService;
import com.springboot.util.CommonAdaptorUtil;
import com.springboot.util.CryptoResponse;
import com.springboot.util.ErrorCode;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {

    private static final String MESSAGE="Registration Success";
    private static final String SINGIN="Login Sucessfull";

    @Autowired
    private RegisterService registerService;

    @PostMapping("/create-user")
    public ResponseEntity<CryptoResponse<NewCustomerRegistrationResponse>>  newCustomerRegister
            (@RequestBody NewCustomerRegistrationRequest customerRegistrationRequest){

        NewCustomerRegistrationResponse newUser = registerService.createNewUser(customerRegistrationRequest);
        return ResponseEntity.ok().body(CommonAdaptorUtil.generateResponse(MESSAGE,newUser,null));
    }

    @PostMapping("/login")
    public ResponseEntity<CryptoResponse<SigninResponse>> userLogin(@RequestBody LoginRequestDTO loginRequestDTO){

        SigninResponse signin = registerService.signin(loginRequestDTO);
        return ResponseEntity.ok().body(CommonAdaptorUtil.generateResponse(SINGIN,signin,null));

        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable Long id){

        Map<String, String> stringStringMap = registerService.deleteUserById(id);

        return new ResponseEntity(stringStringMap, HttpStatus.CREATED);
    }


    @PostMapping("/search")
    public ResponseEntity<List<CustomerEntity>> searchUsers(@RequestBody Map<String, Object> filterParams) {
        List<CustomerEntity> users = registerService.findUsers(filterParams);
        return ResponseEntity.ok(users);
    }


}


