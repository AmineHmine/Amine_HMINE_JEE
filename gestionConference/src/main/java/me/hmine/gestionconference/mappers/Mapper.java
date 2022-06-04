package me.hmine.gestionconference.mappers;


import me.hmine.gestionconference.DTOs.ConferenceDTO;
import me.hmine.gestionconference.entities.Conference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public ConferenceDTO fromConference(Conference conference){
        ConferenceDTO conferenceDTO = new ConferenceDTO();
        BeanUtils.copyProperties(conference,conferenceDTO);
        return conferenceDTO;
    }

    public Conference fromConferenceDTO(ConferenceDTO conferenceDTO){
        Conference conference = new Conference();
        BeanUtils.copyProperties(conferenceDTO,conference);
        return conference;
    }

}
