package ma.amine.jpa_app.entities;


import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date dateNaiss;
    private boolean malade;
    @OneToMany(mappedBy = "pat" , fetch = FetchType.LAZY)
    private Collection<RendezVous> renderVous;
}
