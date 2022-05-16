package me.hmine.studentmanagement.web;

import lombok.AllArgsConstructor;
import me.hmine.studentmanagement.entities.Student;
import me.hmine.studentmanagement.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentRepository studentRepo;

    @GetMapping("/user/index")
    public String student(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "7") int size,
                          @RequestParam(name = "keyword",defaultValue = "") String keyword

    ){
        Page<Student> students = studentRepo.findStudentByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("studentsList",students.getContent());
        model.addAttribute("nbPages",new int[students.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentKey",keyword);

        return "students";
    }

    @GetMapping(path="/admin/delete")
    public String delete(Long id,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "") String keyword
    ){
        studentRepo.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path="/")
    public String home(){
        return "home";
    }

    @GetMapping(path="/user/students")
    @ResponseBody
    public List<Student> listPatients(){
        return studentRepo.findAll();
    }

    @GetMapping(path="/admin/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "addStudentForm";
    }

    @PostMapping(path="/admin/save")
    public String save(Model model,@Valid Student patient,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword
    ){
        if (bindingResult.hasErrors()){
            return "addStudentForm";
        }
        studentRepo.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path="/admin/edit")
    public String edit(Model model,Long id,int page,String keyword){
        Student std = studentRepo.findById(id).orElse(null);
        if(std==null)throw new RuntimeException("not exist student");
        model.addAttribute("editStudent",std);
        model.addAttribute("page",page);
        model.addAttribute("key",keyword);
        return "editStudent";
    }
}
