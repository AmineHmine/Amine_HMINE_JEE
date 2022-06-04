package me.hmine.gestionconference.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hmine.gestionconference.entities.Conference;

import javax.persistence.*;
import java.util.Date;

@Data
public class CommentaireDTO {
    private Long id;
    private Date date;
    private String contenu;
    private int likes;
}
