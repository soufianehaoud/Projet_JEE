package com.example.TP2_G4.Controllers;

import com.example.TP2_G4.entities.Etudiant;
import com.example.TP2_G4.repos.EtudiantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EtudiantController {
    @Autowired
    EtudiantRepo repository;
    @GetMapping("/list")
    public String Students(Model model, @RequestParam(name="page", defaultValue = "0") int page,
                          @RequestParam(name="taille", defaultValue = "4") int taille,
                           @RequestParam(name="Search", defaultValue = "") String keyword){
        //Page<Etudiant> students = repository.findAll(PageRequest.of(page,taille));
        Page<Etudiant> students = repository.findByFullnameContains(keyword,PageRequest.of(page,taille));
        model.addAttribute("listOfStudents",students.getContent());
        model.addAttribute("currentPage",page);
        int [] pages=new int[students.getTotalPages()];
        for(int i=0;i<pages.length;i++)pages[i]=i;
        model.addAttribute("pages",pages);
        model.addAttribute("keyword",keyword);
        return "liste";
    }
    @PostMapping("/save")
    public String saveStudent(Model m, @Valid Etudiant etudiant, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "Formulaire";
        repository.save(etudiant);
        return "redirect:/liste";

    }
    @GetMapping("/edit")
    public String editStudent(Model model,Integer id){
        model.addAttribute("student",repository.findById(id).get());
        return "Modification";
    }
    @GetMapping(path="/edit")
    public String DeleteStudent(Integer id,Integer page,String search){
        System.out.println("Delete " + id);
        repository.deleteById(id);
        return "redirect:/liste?page"+page+"&search="+search;
    }
}
