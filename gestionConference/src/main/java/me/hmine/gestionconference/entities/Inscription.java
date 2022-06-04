package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date date;
    private Status statut;
    private double prix;
    @ManyToOne
    private Session session;
    @ManyToOne
    private Invite invite;
}
