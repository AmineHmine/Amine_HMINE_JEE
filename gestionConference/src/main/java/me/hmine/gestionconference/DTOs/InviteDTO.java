package me.hmine.gestionconference.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.entities.Inscription;

import javax.persistence.*;
import java.util.List;

@Data

public class InviteDTO extends ParticipantDTO {
    private String affiliation;
}
