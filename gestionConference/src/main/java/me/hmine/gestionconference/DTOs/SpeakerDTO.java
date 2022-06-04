package me.hmine.gestionconference.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.entities.Conference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
public class SpeakerDTO extends ParticipantDTO {
    private String profileLink;
}
