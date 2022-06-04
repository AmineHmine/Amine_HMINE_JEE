package com.example.digitalbankingbackend.web;

import com.example.digitalbankingbackend.DTOs.*;
import com.example.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.example.digitalbankingbackend.exceptions.SoldeInsufisqntException;
import com.example.digitalbankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@Slf4j
public class BankAccRestController {

    private BankAccountService bankServ;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankServ.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> getBankAccount(){
        return bankServ.bankAccounts();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankServ.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "2")int size) throws BankAccountNotFoundException {
        return bankServ.getAccountHistory(accountId,page,size);
    }
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, SoldeInsufisqntException {
        this.bankServ.debit(debitDTO.getAccountIdDTO(),debitDTO.getAmountDTO(),debitDTO.getDescriptionDTO());
        return debitDTO;
    }

    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException, SoldeInsufisqntException {
        this.bankServ.credit(creditDTO.getAccountIdDTO(),creditDTO.getAmountDTO(),creditDTO.getDescriptionDTO());
        return creditDTO;
    }

    @PostMapping("/accounts/transfer")
    public TransferRequestDTO transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, SoldeInsufisqntException {
        this.bankServ.transfer(
                transferRequestDTO.getAccountIdSRC(),
                transferRequestDTO.getAccountIdDEST(),
                transferRequestDTO.getAmountDTO()
        );
        return transferRequestDTO;
    }


}
