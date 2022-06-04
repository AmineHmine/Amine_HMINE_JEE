package me.hmine.gestionconference.DTOs;

import lombok.Data;
import java.util.Date;

@Data

public class ConferenceDTO {
    private Long id;
    private String titre;
    private Date date;
    private Date heureDeb;
    private Date heureFin ;
    private String description;
}
