package com.springboot.repository;

import com.springboot.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<CustomerEntity,Long> , JpaSpecificationExecutor<CustomerEntity> {

   Optional<CustomerEntity> findByEmail(String email);

    void deleteById(Long id);
}
