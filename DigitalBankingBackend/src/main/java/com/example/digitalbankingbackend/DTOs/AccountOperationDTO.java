package com.example.digitalbankingbackend.DTOs;

import com.example.digitalbankingbackend.entities.BankAccount;
import com.example.digitalbankingbackend.enums.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OpType type;
    private String description;
}
