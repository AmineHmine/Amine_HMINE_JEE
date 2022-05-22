package com.example.digitalbankingbackend.DTOs;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountIdDTO;
    private double amountDTO;
    private String descriptionDTO;
}
