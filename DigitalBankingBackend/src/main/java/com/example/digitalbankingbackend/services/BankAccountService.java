package com.example.digitalbankingbackend.services;

import com.example.digitalbankingbackend.DTOs.*;
import com.example.digitalbankingbackend.entities.*;
import com.example.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.example.digitalbankingbackend.exceptions.CustomerNotFoundException;
import com.example.digitalbankingbackend.exceptions.SoldeInsufisqntException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double solde, Long customerId, double overDraft) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingAccount(double solde, Long customerId, double interestRate) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId,double amount,String description) throws BankAccountNotFoundException, SoldeInsufisqntException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundException;
    void transfer(String srcId,String destId,double amount) throws BankAccountNotFoundException, SoldeInsufisqntException;
    List<BankAccountDTO> bankAccounts();
    List<AccountOperation> ops();
    CustomerDTO getCustomer(Long custId) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long custId);
    List<AccountOperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
