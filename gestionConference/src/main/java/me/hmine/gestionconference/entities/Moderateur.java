package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("MODERATEUR")
public class Moderateur extends Participant{
    private String specialite;
    @OneToOne
    private Session session;
}
