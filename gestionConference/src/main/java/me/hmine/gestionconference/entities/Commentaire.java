package me.hmine.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String contenu;
    private int likes;
    @ManyToOne
    private Conference conference;
    @ManyToOne
    private Participant participant;
}
