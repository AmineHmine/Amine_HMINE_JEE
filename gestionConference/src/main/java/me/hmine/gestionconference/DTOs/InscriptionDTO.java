package me.hmine.gestionconference.DTOs;

import lombok.Data;
import me.hmine.gestionconference.enums.Status;
import java.util.Date;


@Data


public class InscriptionDTO {
    private String id;
    private Date date;
    private Status statut;
    private double prix;
}
