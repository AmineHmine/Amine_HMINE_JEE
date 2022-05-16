package ma.amine.jpa_app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // si on a mis string il faut utiliser UUID dans la couche hospital (metier)
    private String name;
    private String email;
    private String specialite;
    @OneToMany(mappedBy = "medecin" , fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<RendezVous> renderVous;
}
