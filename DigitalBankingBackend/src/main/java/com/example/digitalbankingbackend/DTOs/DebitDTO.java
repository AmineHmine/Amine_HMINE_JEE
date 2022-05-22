package com.example.digitalbankingbackend.DTOs;

import lombok.Data;

@Data
public class DebitDTO {
    private String accountIdDTO;
    private double amountDTO;
    private String descriptionDTO;
}
