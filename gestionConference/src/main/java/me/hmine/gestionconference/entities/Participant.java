package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.enums.Sexe;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 12)

public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nom;
    private String email;
    private String photo;
    private Sexe genre;
    @OneToMany(mappedBy = "participant",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;
}
