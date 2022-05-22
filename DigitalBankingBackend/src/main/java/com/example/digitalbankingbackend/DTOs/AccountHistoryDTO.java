package com.example.digitalbankingbackend.DTOs;

import lombok.Data;

import java.util.List;


@Data
public class AccountHistoryDTO {
    private String accoundId;
    private  double balence;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<AccountOperationDTO> accountOperationDTOS;

}
