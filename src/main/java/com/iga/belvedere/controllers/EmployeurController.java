package com.iga.belvedere.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Employeur;
import com.iga.belvedere.repositories.emploiRepository;

@Controller
public class EmployeurController {
	@Autowired
	private com.iga.belvedere.repositories.employeurRepository employeurRepo;
	@Autowired
	private emploiRepository emploiRepo;
	@GetMapping("post-job.html")
	public String Ajoutjob(Model model) {
		Emploi newEmploi = new Emploi();
		List<Employeur> employeurs = employeurRepo.findAll();
		model.addAttribute("newEmploi",newEmploi);
		model.addAttribute("employeurs", employeurs);
		return "post-job";
	}
	@PostMapping("/saveEmploi")
	public String saveEmploi(@ModelAttribute("newEmploi") Emploi emploi) {
		Emploi emplois = new Emploi();
		Employeur employeur = employeurRepo.findById(1).orElse(null); 
	        emploi.setEmployeur(employeur); 
	        emploiRepo.save(emploi); 
	    
		//employeur.setId(1);
		//emplois.setEmployeur(employeur);
	   // emploiRepo.save(emploi);

	    
	    return "redirect:/";
	}
}
