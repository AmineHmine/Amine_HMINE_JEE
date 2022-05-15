package ma.enset.gestionpatient.web;

import lombok.AllArgsConstructor;
import ma.enset.gestionpatient.entities.Patient;
import ma.enset.gestionpatient.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patRepo;

    @GetMapping("/index")
    public String patient(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "10") int size,
                          @RequestParam(name = "keyword",defaultValue = "") String keyword

    ){
        Page<Patient> patients = patRepo.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatient",patients.getContent());
        model.addAttribute("nbPages",new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentKey",keyword);

        return "patients";
    }

    @GetMapping(path="/delete")
    public String delete(Long id,int page,String keyword){
        patRepo.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path="/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping(path="/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patRepo.findAll();
    }


}
