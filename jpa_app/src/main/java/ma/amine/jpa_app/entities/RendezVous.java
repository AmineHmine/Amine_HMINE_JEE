package ma.amine.jpa_app.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING) // pour stocker la valeur de l'entité sous format string
    private RdvS status;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient pat;
    @ManyToOne
    private Medecin medecin;
    @OneToOne(mappedBy = "rendezVous", fetch = FetchType.LAZY)
    private Consultation consultation;
}
