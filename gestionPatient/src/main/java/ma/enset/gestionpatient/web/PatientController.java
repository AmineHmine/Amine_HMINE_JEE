package ma.enset.gestionpatient.web;

import lombok.AllArgsConstructor;
import ma.enset.gestionpatient.entities.Patient;
import ma.enset.gestionpatient.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patRepo;

    @GetMapping("/user/index")
    public String patient(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "6") int size,
                          @RequestParam(name = "keyword",defaultValue = "") String keyword

    ){
        Page<Patient> patients = patRepo.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatient",patients.getContent());
        model.addAttribute("nbPages",new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentKey",keyword);

        return "patients";
    }

    @GetMapping(path="/admin/delete")
    public String delete(Long id,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "") String keyword
    ){
        patRepo.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path="/")
    public String home(){
        return "home";
    }

    @GetMapping(path="/user/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patRepo.findAll();
    }

    @GetMapping(path="/admin/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }

    @PostMapping(path="/admin/save")
    public String save(Model model,@Valid Patient patient,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword
                       ){
        if (bindingResult.hasErrors()){
            return "formPatient";
        }
        patRepo.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path="/admin/edit")
    public String edit(Model model,Long id,int page,String keyword){
        Patient p = patRepo.findById(id).orElse(null);
        if(p==null)throw new RuntimeException("not exist patient");
        model.addAttribute("patEdit",p);
        model.addAttribute("page",page);
        model.addAttribute("key",keyword);
        return "editPatient";
    }

}
