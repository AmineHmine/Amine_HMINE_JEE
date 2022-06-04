package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date heureDeb;
    @Temporal(TemporalType.TIME)
    private Date heureFin ;
    private String description;

    @OneToOne
    private Speaker speaker;
    @OneToMany(mappedBy = "conference",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;
}
