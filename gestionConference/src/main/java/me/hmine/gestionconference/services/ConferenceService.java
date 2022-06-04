package me.hmine.gestionconference.services;

import me.hmine.gestionconference.DTOs.ConferenceDTO;
import me.hmine.gestionconference.exceptions.ConferenceNotFoundException;

import java.util.List;

public interface ConferenceService{
    List<ConferenceDTO> getConferences();
    ConferenceDTO getConference(Long conferenceId) throws ConferenceNotFoundException;
    void deleteConference(Long conferenceId);
    ConferenceDTO updateConference(ConferenceDTO conferenceDTO);
    ConferenceDTO saveConference(ConferenceDTO conferenceDTO);

}
