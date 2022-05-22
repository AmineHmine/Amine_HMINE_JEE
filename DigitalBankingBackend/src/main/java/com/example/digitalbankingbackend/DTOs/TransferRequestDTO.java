package com.example.digitalbankingbackend.DTOs;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private String accountIdSRC;
    private String accountIdDEST;
    private double amountDTO;
}
