package com.example.digitalbankingbackend.services;

import com.example.digitalbankingbackend.entities.BankAccount;
import com.example.digitalbankingbackend.entities.CurrentAccount;
import com.example.digitalbankingbackend.entities.SavingAccount;
import com.example.digitalbankingbackend.repositories.BankAccRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BankService {

    private BankAccRepository bankRepo;
    public void Consulter(){
        BankAccount bank = bankRepo.findById("020dedf3-1ca2-487a-88de-f8a381ca71b0").orElse(null);
        if (bank!=null){
            System.out.println(" -> "+bank.getCustomer().getName()+" ------------- >");
            System.out.println(bank.getBalence());
            System.out.println(bank.getCreatedAt());
            System.out.println(bank.getStatus());
            if (bank instanceof CurrentAccount){
                System.out.println("overDraft => "+((CurrentAccount) bank).getOverdraft());
            }
            else if (bank instanceof SavingAccount){
                System.out.println("Rate => "+((SavingAccount) bank).getInterestRate());
            }
            bank.getAccountOperations().forEach(bk->{
                System.out.println(bk.getType()+" - "+bk.getOperationDate()+" - "+bk.getAmount());
            });
        }
    }
}
