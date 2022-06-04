package me.hmine.gestionconference.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.enums.Sexe;

import javax.persistence.*;
import java.util.List;

@Data

public class ParticipantDTO {
    private Long id;
    private String nom;
    private String email;
    private String photo;
    private Sexe genre;
}
