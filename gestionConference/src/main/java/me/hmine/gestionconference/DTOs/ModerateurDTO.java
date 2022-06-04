package me.hmine.gestionconference.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.entities.Session;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
public class ModerateurDTO extends ParticipantDTO {
    private String specialite;
}
