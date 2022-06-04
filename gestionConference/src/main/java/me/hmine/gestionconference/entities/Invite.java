package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@DiscriminatorValue("INVITE")

public class Invite extends Participant{
    private String affiliation;
    @OneToMany(mappedBy = "invite",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;
}
