package com.example.digitalbankingbackend.entities;

import com.example.digitalbankingbackend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

//---------------SINGLE_TABLE---------------------------------
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4)
//------------------------------------------------------------

//---------------TABLE_PER_CLASS------------------------------
/*@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)*/
//en ajoute abstract pour ne pas creer la table bankaccount
//------------------------------------------------------------

//---------------JOINED------------------------------
//@Inheritance(strategy = InheritanceType.JOINED)

@Data @NoArgsConstructor@AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private double balence;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;
}
