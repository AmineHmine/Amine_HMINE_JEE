package me.hmine.gestionconference.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hmine.gestionconference.DTOs.ConferenceDTO;
import me.hmine.gestionconference.exceptions.ConferenceNotFoundException;
import me.hmine.gestionconference.services.ConferenceService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ConferenceRestController {
    private ConferenceService conferenceService;

    @GetMapping("/conferences")
    public String getConferences(Model model){

       List<ConferenceDTO>  conferences = conferenceService.getConferences();
        model.addAttribute("conferencesList",conferences);
        return "template";
    }

    @GetMapping("/conferences/{confId}")
    public ConferenceDTO getConference(@PathVariable Long confId) throws ConferenceNotFoundException {
        return conferenceService.getConference(confId);
    }

    @PostMapping("/conferences")
    public ConferenceDTO saveConferences(@RequestBody ConferenceDTO conferenceDTO){
        return conferenceService.saveConference(conferenceDTO);
    }

    @PutMapping("/conferences/{confId}")
    public ConferenceDTO updateConferences(@RequestBody ConferenceDTO conferenceDTO,@PathVariable Long confId){
        conferenceDTO.setId(confId);
        return conferenceService.updateConference(conferenceDTO);
    }

    @DeleteMapping("/conferences/{confId}")
    public void deleteConference(@PathVariable Long confId){
        conferenceService.deleteConference(confId);
    }
}
