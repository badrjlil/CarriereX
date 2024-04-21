package com.iga.belvedere.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Employeur;
import com.iga.belvedere.repositories.catégorieRepo;
import com.iga.belvedere.repositories.emploiRepository;
import com.iga.belvedere.repositories.employeurRepository;

@Controller
public class EmployeurController {
	@Autowired
	private employeurRepository employeurRepo;
	@Autowired
	private emploiRepository emploiRepo;
	@Autowired
	private catégorieRepo catégorieRepo;

	@GetMapping("post-job.html")
	public String Ajoutjob(Model model) {
		Emploi newEmploi = new Emploi();
		List<Employeur> employeurs = employeurRepo.findAll();
		List<Catégorie> catégories = catégorieRepo.findAll();
		model.addAttribute("newEmploi", newEmploi);
		model.addAttribute("employeurs", employeurs);
		model.addAttribute("catégories", catégories);
		return "post-job";
	}

	@PostMapping("/saveEmploi")
	public String saveEmploi(@RequestParam Catégorie catégorie, @ModelAttribute("newEmploi") Emploi emploi) {
		Emploi emplois = new Emploi();
		Employeur employeur = employeurRepo.findById(1).orElse(null);
		emploi.setEmployeur(employeur);
		emploi.setCatégorie(catégorie);
		emploiRepo.save(emploi);

		// employeur.setId(1);
		// emplois.setEmployeur(employeur);

		return "redirect:/";
	}

	@GetMapping("/findEmploi")
	public String findEmploi(@RequestParam(defaultValue = "") String motcle, @RequestParam String location,
			Model model) {
		List<Emploi> emplois = emploiRepo.findAllByKeyword(motcle);
		model.addAttribute("emplois", emplois);

		return "job-list";
	}

	@GetMapping("/")
	public String home(Model model) {
		List<Catégorie> categories = catégorieRepo.findAll();
		model.addAttribute("categories", categories);
		List<Emploi> emplois = emploiRepo.findAll();
		model.addAttribute("emplois", emplois);
		return "index";
	}

	@GetMapping("catagories.html")
	public String catagories(Model model) {
		List<Catégorie> categories = catégorieRepo.findAll();
		model.addAttribute("categories", categories);
		return "catagories";
	}

}
