package me.hmine.gestionconference.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.entities.Session;

import javax.persistence.*;

@Data

public class SalleDTO {
    private Long id;
    private String nom;
}
