package com.springboot.service.impl;

import com.springboot.dto.LoginRequestDTO;
import com.springboot.dto.NewCustomerRegistrationRequest;
import com.springboot.dto.NewCustomerRegistrationResponse;

import com.springboot.dto.SigninResponse;
import com.springboot.exception.CustomException;
import com.springboot.models.CustomerEntity;
import com.springboot.repository.RegisterRepository;
import com.springboot.service.RegisterService;
import com.springboot.specification.SpecificationBuilder;
import com.springboot.util.ErrorCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
   private RegisterRepository registerRepository;

    @Override
    public NewCustomerRegistrationResponse createNewUser(NewCustomerRegistrationRequest createRequest) {
        CustomerEntity customer=new CustomerEntity();
        BeanUtils.copyProperties(createRequest,customer);
        CustomerEntity saved = registerRepository.save(customer);
        return  NewCustomerRegistrationResponse.builder()
                .message("Created Successfully")
                .name(saved.getName())
                .email(saved.getEmail())
                .password(saved.getPassword())
                .city(saved.getCity())
                .state(saved.getState())
                .country(saved.getCountry())
                .build();
    }
    @Override
    public SigninResponse signin(LoginRequestDTO loginRequestDTO) {
       CustomerEntity loginUser = validateCustomerByEmail(loginRequestDTO.getEmail());

        return SigninResponse.builder().message("Login Successful").token(UUID.randomUUID().toString()).build();
    }

    private CustomerEntity validateCustomerByEmail(String email) {
        Optional<CustomerEntity> optionalUser = registerRepository.findByEmail(email);
        if(!optionalUser.isPresent()){
            throw new CustomException(ErrorCode.USER_NOT_FOUND,this.getClass());
        }
        return optionalUser.get();
    }

    @Override
    public Map<String, String> deleteUserById(Long id) {

        Optional<CustomerEntity> byId = registerRepository.findById(id);
        if (!byId.isPresent()){
            throw  new RuntimeException("User Not Found With That Id");
        }
         registerRepository.deleteById(id);
        Map<String,String>map=new HashMap<>();
        map.put("Message","User DeletedSucessfully");
        return map;
    }

    public List<CustomerEntity> findUsers(Map<String, Object> filters) {
        Specification<CustomerEntity> spec = buildSpecification(filters);
        return registerRepository.findAll(spec);
    }

    private Specification<CustomerEntity> buildSpecification(Map<String, Object> filters) {
        SpecificationBuilder<CustomerEntity> builder = new SpecificationBuilder<>();
        Specification<CustomerEntity> spec = Specification.where(null);

        if (filters.containsKey("name")) {
            spec = spec.and(builder.containsIgnoreCase("name", (String) filters.get("name")));
        }
        if (filters.containsKey("email")) {
            spec = spec.and(builder.containsIgnoreCase("email", (String) filters.get("email")));
        }
        if (filters.containsKey("city")) {
            spec = spec.and(builder.containsIgnoreCase("city", (String) filters.get("city")));
        }
        if (filters.containsKey("state")) {
            spec = spec.and(builder.containsIgnoreCase("state", (String) filters.get("state")));
        }
        if (filters.containsKey("country")) {
            spec = spec.and(builder.containsIgnoreCase("country", (String) filters.get("country")));
        }
        // Add more filters as needed

        return spec;
    }
}



