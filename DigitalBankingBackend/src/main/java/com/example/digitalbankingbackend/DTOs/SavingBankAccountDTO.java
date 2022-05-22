package com.example.digitalbankingbackend.DTOs;

import com.example.digitalbankingbackend.entities.AccountOperation;
import com.example.digitalbankingbackend.entities.Customer;
import com.example.digitalbankingbackend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class SavingBankAccountDTO extends BankAccountDTO{
    private String id;
    private double balence;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
