package com.example.digitalbankingbackend;

import com.example.digitalbankingbackend.DTOs.BankAccountDTO;
import com.example.digitalbankingbackend.DTOs.CurrentBankAccountDTO;
import com.example.digitalbankingbackend.DTOs.CustomerDTO;
import com.example.digitalbankingbackend.DTOs.SavingBankAccountDTO;
import com.example.digitalbankingbackend.entities.*;
import com.example.digitalbankingbackend.enums.AccountStatus;
import com.example.digitalbankingbackend.enums.OpType;
import com.example.digitalbankingbackend.exceptions.BankAccountNotFoundException;
import com.example.digitalbankingbackend.exceptions.CustomerNotFoundException;
import com.example.digitalbankingbackend.exceptions.SoldeInsufisqntException;
import com.example.digitalbankingbackend.repositories.AccOperationRepository;
import com.example.digitalbankingbackend.repositories.BankAccRepository;
import com.example.digitalbankingbackend.repositories.CustomerRepository;
import com.example.digitalbankingbackend.services.BankAccountService;
import com.example.digitalbankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BankAccountService bankServ){
        return args -> {
                Stream.of("Amine","Amal","Nadia","Ziad","riad","achraf","simo").forEach(name->{
                    CustomerDTO cust = new CustomerDTO();
                    cust.setName(name);
                    cust.setEmail(name+"@gmail.com");
                    bankServ.saveCustomer(cust);
                });


                bankServ.listCustomers().forEach(cust->{
                    try {
                        bankServ.saveCurrentBankAccount(Math.random()*90000, cust.getId(),Math.random()*900);
                        bankServ.saveSavingAccount(Math.random()*120000, cust.getId(),5.5);

                    } catch (CustomerNotFoundException e) {
                        e.printStackTrace();
                    }

                });

            List<BankAccountDTO> banks = bankServ.bankAccounts();
            for (BankAccountDTO bank:banks){
                for (int i = 0; i < 10; i++) {
                    String id;
                    if (bank instanceof CurrentBankAccountDTO){
                        id=((CurrentBankAccountDTO) bank).getId();
                    }
                    else {
                        id=((SavingBankAccountDTO) bank).getId();
                    }
                    bankServ.credit(id,10000+Math.random()*120000,"credit");
                }
                for (int i = 0; i < 10; i++) {
                    String id;
                    if (bank instanceof CurrentBankAccountDTO){
                        id=((CurrentBankAccountDTO) bank).getId();
                    }
                    else {
                        id=((SavingBankAccountDTO) bank).getId();
                    }
                    bankServ.debit(id,1000+Math.random()*9000,"debit");
                }
            }


                bankServ.ops().forEach(accountOperation -> {
                    System.out.println(accountOperation.getType() +"-"+accountOperation.getBankAccount().getId() );
                });
            };
    }
}
