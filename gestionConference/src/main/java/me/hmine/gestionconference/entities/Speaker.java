package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("SPEAKER")
public class Speaker extends Participant{
    private String profileLink;
    @OneToOne
    private Conference conference;
}
