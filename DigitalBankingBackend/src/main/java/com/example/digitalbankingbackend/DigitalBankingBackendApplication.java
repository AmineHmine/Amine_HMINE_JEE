package com.example.digitalbankingbackend;

import com.example.digitalbankingbackend.entities.*;
import com.example.digitalbankingbackend.enums.AccountStatus;
import com.example.digitalbankingbackend.enums.OpType;
import com.example.digitalbankingbackend.repositories.AccOperationRepository;
import com.example.digitalbankingbackend.repositories.BankAccRepository;
import com.example.digitalbankingbackend.repositories.CustomerRepository;
import com.example.digitalbankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingBackendApplication.class, args);
    }

    //@Bean
    public CommandLineRunner start(CustomerRepository customerRepo, BankAccRepository bankRepo, AccOperationRepository accOpRepo){
        return args -> {
            Stream.of("Hamza","Amine","Soufiane").forEach(name->{
                Customer cus=new Customer();
                cus.setName(name);
                cus.setEmail(name+"@gmail.com");
                customerRepo.save(cus);
            });
            customerRepo.findAll().forEach(cust-> {
                CurrentAccount ca= new CurrentAccount();
                ca.setId(UUID.randomUUID().toString());
                ca.setBalence(Math.random()*90000);
                ca.setCreatedAt(new Date());
                ca.setStatus(AccountStatus.CREATED);
                ca.setCustomer(cust);
                ca.setOverdraft(9000);
                bankRepo.save(ca);
                System.out.println(ca.getCustomer().getName());
            });

            customerRepo.findAll().forEach(cust-> {
                SavingAccount sa= new SavingAccount();
                sa.setId(UUID.randomUUID().toString());
                sa.setBalence(Math.random()*90000);
                sa.setCreatedAt(new Date());
                sa.setStatus(AccountStatus.CREATED);
                sa.setCustomer(cust);
                sa.setInterestRate(5.5);
                bankRepo.save(sa);
                System.out.println(sa.getCustomer().getName());
            });

            bankRepo.findAll().forEach(acc->{
                for (int i = 0; i < 5; i++) {
                    AccountOperation op=new AccountOperation();
                    op.setOperationDate(new Date());
                    op.setAmount(Math.random()*12000);
                    op.setType(Math.random()>0.5? OpType.CREDIT:OpType.DEBIT);
                    op.setBankAccount(acc);
                    accOpRepo.save(op);
                }
            });
        };


    }

    @Bean
    public CommandLineRunner commandLineRunner(BankService BankServ){
        return args -> {
                BankServ.Consulter();
            };
    }
}
