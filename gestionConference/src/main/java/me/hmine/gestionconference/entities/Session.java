package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany
    private List<Conference> conferences;

    @OneToOne
    private Moderateur moderateur;

    @OneToOne
    private Salle salle;

    @OneToMany(mappedBy = "session",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;
}
