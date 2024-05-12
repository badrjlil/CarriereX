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
import com.iga.belvedere.entities.Keyword;
import com.iga.belvedere.entities.Langue;
import com.iga.belvedere.entities.Ville;
import com.iga.belvedere.repositories.LangueRepository;
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.emploiRepository;
import com.iga.belvedere.repositories.employeurRepository;

import com.iga.belvedere.repositories.villeRepository;

import com.iga.belvedere.repositories.keywordRepository;


@Controller
public class EmployeurController {
	@Autowired
	private employeurRepository employeurRepo;
	@Autowired
	private emploiRepository emploiRepo;
	@Autowired
	private categorieRepository catégorieRepo;
	@Autowired
	private LangueRepository langueRepo;
	@Autowired
	private villeRepository villeRepo;
	@Autowired
	private keywordRepository keywordRepo;


	@GetMapping("/postJob")
	public String Ajoutjob(Model model) {
		Emploi newEmploi = new Emploi();
		List<Employeur> employeurs = employeurRepo.findAll();
		List<Catégorie> catégories = catégorieRepo.findAll();
		List<Langue> langues = langueRepo.findAll();
		List<Ville> villes = villeRepo.findAll();
		model.addAttribute("newEmploi", newEmploi);
		model.addAttribute("employeurs", employeurs);
		model.addAttribute("catégories", catégories);
		model.addAttribute("langues", langues);
		model.addAttribute("villes",villes);
		return "dashboard/employer-dashboard-post-job.html";
	}

	@PostMapping("/saveEmploi")

	public String saveEmploi(@RequestParam Catégorie catégorie,@RequestParam Langue langue,@RequestParam Ville ville, @ModelAttribute("newEmploi") Emploi emploi,
			@RequestParam String keywordsInput) {

		Employeur employeur = employeurRepo.findById(1).orElse(null);
		emploi.setEmployeur(employeur);
		emploi.setCatégorie(catégorie);
		emploi.setLangue(langue);

		emploi.setVille(ville);

		String[] keywords = keywordsInput.split(",");
		for (String keywordStr : keywords) {
		    Keyword keyword = new Keyword(keywordStr.trim());
		    keyword.setEmploi(emploi);
		    keywordRepo.save(keyword);
		}

		emploiRepo.save(emploi);

		return "redirect:dashboard/employer-dashboard.html";
	
	}

	@GetMapping("/catagories")
	public String catagories(Model model) {
		List<Catégorie> categories = catégorieRepo.findAll();
		model.addAttribute("categories", categories);
		return "catagories";
	}
	
	

}
