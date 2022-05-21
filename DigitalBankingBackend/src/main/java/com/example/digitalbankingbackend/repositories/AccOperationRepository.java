package com.example.digitalbankingbackend.repositories;

import com.example.digitalbankingbackend.entities.AccountOperation;
import com.example.digitalbankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccOperationRepository extends JpaRepository<AccountOperation,Long> {
    
}
