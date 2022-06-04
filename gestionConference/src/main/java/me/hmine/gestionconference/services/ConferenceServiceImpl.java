package me.hmine.gestionconference.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hmine.gestionconference.DTOs.ConferenceDTO;
import me.hmine.gestionconference.entities.Conference;
import me.hmine.gestionconference.exceptions.ConferenceNotFoundException;
import me.hmine.gestionconference.mappers.Mapper;
import me.hmine.gestionconference.repositories.ConferenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
@Slf4j //pour logger nos messages
public class ConferenceServiceImpl implements ConferenceService {

    private Mapper mapper;
    private ConferenceRepository conferenceRepository;

    @Override
    public List<ConferenceDTO> getConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        List<ConferenceDTO> custDTOs = conferences.stream().map(
                conference -> mapper.fromConference(conference))
                .collect(Collectors.toList());
        return custDTOs;
    }

    @Override
    public ConferenceDTO getConference(Long conferenceId) throws ConferenceNotFoundException {
        Conference conference = conferenceRepository.findById(conferenceId).orElseThrow(
                ()-> new ConferenceNotFoundException("bank account not found"));
        return mapper.fromConference(conference);
    }

    @Override
    public void deleteConference(Long conferenceId) {
        conferenceRepository.deleteById(conferenceId);
    }

    @Override
    public ConferenceDTO updateConference(ConferenceDTO conferenceDTO) {
        log.info("update Conference");
        Conference conference=mapper.fromConferenceDTO(conferenceDTO);
        conferenceRepository.save(conference);
        return mapper.fromConference(conference);
    }

    @Override
    public ConferenceDTO saveConference(ConferenceDTO conferenceDTO) {
        log.info("saving new Conference");
        Conference conference=mapper.fromConferenceDTO(conferenceDTO);
        conferenceRepository.save(conference);
        return mapper.fromConference(conference);
    }
}
