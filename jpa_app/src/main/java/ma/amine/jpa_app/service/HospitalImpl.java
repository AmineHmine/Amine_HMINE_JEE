package ma.amine.jpa_app.service;

import ma.amine.jpa_app.entities.Consultation;
import ma.amine.jpa_app.entities.Medecin;
import ma.amine.jpa_app.entities.Patient;
import ma.amine.jpa_app.entities.RendezVous;
import ma.amine.jpa_app.repositories.ConsultationRepository;
import ma.amine.jpa_app.repositories.MedecinRepository;
import ma.amine.jpa_app.repositories.PatientRepository;
import ma.amine.jpa_app.repositories.RdvRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HospitalImpl implements Hospital {
    private PatientRepository pRepo;
    private MedecinRepository mRepo;
    private RdvRepository rRepo;
    private ConsultationRepository cRepo;

    public HospitalImpl(PatientRepository pRepo, MedecinRepository mRepo, RdvRepository rRepo, ConsultationRepository cRepo) {
        this.pRepo = pRepo;
        this.mRepo = mRepo;
        this.rRepo = rRepo;
        this.cRepo = cRepo;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return pRepo.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return mRepo.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        return rRepo.save(rendezVous);
    }

    @Override
    public Consultation saveCon(Consultation consultation) {
        return cRepo.save(consultation);
    }
}
