package com.example.digitalbankingbackend.mappers;

import com.example.digitalbankingbackend.DTOs.AccountOperationDTO;
import com.example.digitalbankingbackend.DTOs.CurrentBankAccountDTO;
import com.example.digitalbankingbackend.DTOs.CustomerDTO;
import com.example.digitalbankingbackend.DTOs.SavingBankAccountDTO;
import com.example.digitalbankingbackend.entities.AccountOperation;
import com.example.digitalbankingbackend.entities.CurrentAccount;
import com.example.digitalbankingbackend.entities.Customer;
import com.example.digitalbankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

//MapStruct or ... they are frameworks than we can use to generate mapping code
@Service
public class BankAccMapperImpl {

    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    public CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currAccDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currAccDTO);
        currAccDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currAccDTO.setType(currentAccount.getClass().getSimpleName());
        return currAccDTO;
    }
    public CurrentAccount fromCurrentAccountDTO( CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount acc = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,acc);
        acc.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return acc;
    }

    public SavingBankAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingBankAccountDTO saveAccDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,saveAccDTO);
        saveAccDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        saveAccDTO.setType(savingAccount.getClass().getSimpleName());
        return saveAccDTO;
    }

    public SavingAccount fromSavingAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount saveAcc = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,saveAcc);
        saveAcc.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return saveAcc;
    }


    public AccountOperationDTO fromAccountOperation(AccountOperation accOP){
        AccountOperationDTO accOpDTO= new AccountOperationDTO();
        BeanUtils.copyProperties(accOP,accOpDTO);
        return  accOpDTO;
    }

    public AccountOperation fromAccountOperationDTO(AccountOperationDTO accOpDTO){
        AccountOperation accOP = new AccountOperation();
        BeanUtils.copyProperties(accOpDTO,accOP);
        return  accOP;
    }

}
