package com.example.digitalbankingbackend.services;

import com.example.digitalbankingbackend.DTOs.BankAccountDTO;
import com.example.digitalbankingbackend.DTOs.CurrentBankAccountDTO;
import com.example.digitalbankingbackend.DTOs.CustomerDTO;
import com.example.digitalbankingbackend.DTOs.SavingBankAccountDTO;
import com.example.digitalbankingbackend.entities.*;
import com.example.digitalbankingbackend.enums.OpType;
import com.example.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.example.digitalbankingbackend.exceptions.CustomerNotFoundException;
import com.example.digitalbankingbackend.exceptions.SoldeInsufisqntException;
import com.example.digitalbankingbackend.mappers.BankAccMapperImpl;
import com.example.digitalbankingbackend.repositories.AccOperationRepository;
import com.example.digitalbankingbackend.repositories.BankAccRepository;
import com.example.digitalbankingbackend.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j //pour logger nos messages
public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository customerRepo;
    private BankAccRepository bankAccRepo;
    private AccOperationRepository accOpRepo;
    private BankAccMapperImpl mapper;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("saving new customer");
        Customer cust=mapper.fromCustomerDTO(customerDTO);
        customerRepo.save(cust);
        CustomerDTO savedCust = mapper.fromCustomer(cust);
        return savedCust;
    }

    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double solde, Long customerId, double overDraft) throws CustomerNotFoundException {

        Customer cust=customerRepo.findById(customerId).orElse(null);
        if(cust==null){
            throw new CustomerNotFoundException("Customer Not Found");
        }
        CurrentAccount bank = new CurrentAccount();
        bank.setId(UUID.randomUUID().toString());
        bank.setCreatedAt(new Date());
        bank.setBalence(solde);
        bank.setCustomer(cust);
        bank.setOverdraft(overDraft);
        CurrentAccount savedBankAccount = bankAccRepo.save(bank);
        return mapper.fromCurrentAccount(savedBankAccount);
    }

    @Override
    public SavingBankAccountDTO saveSavingAccount(double solde, Long customerId, double interestRate) throws CustomerNotFoundException {
        Customer cust=customerRepo.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
        SavingAccount bank = new SavingAccount();
        bank.setId(UUID.randomUUID().toString());
        bank.setCreatedAt(new Date());
        bank.setBalence(solde);
        bank.setCustomer(cust);
        bank.setInterestRate(interestRate);
        SavingAccount savedBankAccount = bankAccRepo.save(bank);
        return mapper.fromSavingAccount(savedBankAccount);
    }


    @Override
    public List<CustomerDTO> listCustomers() {

        List<Customer> customers = customerRepo.findAll();
        /*
        List<CustomerDTO> custDTOs = new ArrayList<>();
        for(Customer cust:customers){
            custDTOs.add(mapper.fromCustomer(cust));
        }
        */
        // its the same code that we have below
        List<CustomerDTO> custDTOs = customers.stream().map(customer -> mapper.fromCustomer(customer)).collect(Collectors.toList());
        return custDTOs;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bank = bankAccRepo.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
        if (bank instanceof CurrentAccount){
            CurrentAccount currentAccount = (CurrentAccount) bank;
            return mapper.fromCurrentAccount(currentAccount);
        }
        else {
            SavingAccount savingAccount = (SavingAccount) bank;
            return mapper.fromSavingAccount(savingAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, SoldeInsufisqntException {
        BankAccount bank = bankAccRepo.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
        if(bank.getBalence()<amount){
            throw new SoldeInsufisqntException("your solde n'est pas sufisant pour effectuer cette operation !");
        }
        AccountOperation op=new AccountOperation();
        op.setBankAccount(bank);
        op.setType(OpType.DEBIT);
        op.setAmount(amount);
        op.setDescription(description);
        op.setOperationDate(new Date());
        accOpRepo.save(op);
        bank.setBalence(bank.getBalence()-amount);
        bankAccRepo.save(bank);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bank = bankAccRepo.findById(accountId).orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
        AccountOperation op=new AccountOperation();
        op.setBankAccount(bank);
        op.setType(OpType.CREDIT);
        op.setAmount(amount);
        op.setDescription(description);
        op.setOperationDate(new Date());
        accOpRepo.save(op);
        bank.setBalence(bank.getBalence()+amount);
        bankAccRepo.save(bank);
    }

    @Override
    public void transfer(String srcId, String destId, double amount) throws BankAccountNotFoundException, SoldeInsufisqntException {
        BankAccount srcBank = bankAccRepo.findById(srcId).orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
        BankAccount destBank = bankAccRepo.findById(destId).orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
        debit(srcId,amount,"Transfer To "+destBank.getCustomer().getName());
        credit(srcId,amount,"Transfer From "+srcBank.getCustomer().getName());
    }

    @Override
    public List<BankAccountDTO> bankAccounts() {
        List<BankAccount> listBankAccounts = bankAccRepo.findAll();
        List<BankAccountDTO> listBankAccountDTO = listBankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof CurrentAccount){
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return mapper.fromCurrentAccount(currentAccount);
            }
            else{
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return mapper.fromSavingAccount(savingAccount);
            }
        }).collect(Collectors.toList());

        return listBankAccountDTO;
    }

    @Override
    public List<AccountOperation> ops() {
        return accOpRepo.findAll();
    }

    @Override
    public CustomerDTO getCustomer(Long custId) throws CustomerNotFoundException {
        Customer customer = customerRepo.findById(custId).orElseThrow(()-> new CustomerNotFoundException("bank account not found"));
        return mapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("update customer");
        Customer cust=mapper.fromCustomerDTO(customerDTO);
        customerRepo.save(cust);
        CustomerDTO savedCust = mapper.fromCustomer(cust);
        return savedCust;
    }

    @Override
    public void deleteCustomer(Long custId) {
        customerRepo.deleteById(custId);
    }


}
