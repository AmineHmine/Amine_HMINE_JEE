package ma.amine.jpa_app.service;

import ma.amine.jpa_app.entities.*;

public interface Hospital {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveCon(Consultation consultation);
}
