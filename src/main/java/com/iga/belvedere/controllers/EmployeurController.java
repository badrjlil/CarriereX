package com.iga.belvedere.controllers;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Compétence;
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
		model.addAttribute("villes", villes);
		return "dashboard/post-job";
	}

	@PostMapping("/saveEmploi")

	public String saveEmploi(@RequestParam Catégorie catégorie, @RequestParam Langue langue, @RequestParam Ville ville,
			@ModelAttribute("newEmploi") Emploi emploi, @RequestParam String keywordsInput) {

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

		return "redirect:/dashboard";

	}

	@GetMapping("/catagories")
	public String catagories(Model model) {
		List<Catégorie> categories = catégorieRepo.findAll();
		model.addAttribute("categories", categories);
		return "catagories";
	}

	@GetMapping("/gererEmplois")
	public String gererEmplois(Model model) {
		Employeur emp = new Employeur();
		emp.setId(1);
		List<Emploi> emplois = emploiRepo.findAllByEmployeur(emp);
		int totalApplications = 0;
		for(Emploi e : emplois) {
			totalApplications += e.getApplications().size();
		}
		model.addAttribute("emplois", emplois);
		model.addAttribute("totalApplications", totalApplications);
		return "dashboard/manage-jobs";
	}

	@GetMapping("/modifyJob")
	public String modifyJob(Model model, @RequestParam int id) {
		Emploi emp = emploiRepo.getById(id);
		model.addAttribute("emploi", emp);
		List<Catégorie> catégories = catégorieRepo.findAll();
		List<Langue> langues = langueRepo.findAll();
		List<Ville> villes = villeRepo.findAll();
		List<Keyword> keywords = emp.getKeywords();
		String keywordsString = keywords.stream().map(Keyword::getNom).collect(Collectors.joining(", "));
		model.addAttribute("keywordsString", keywordsString);
		model.addAttribute("catégories", catégories);
		model.addAttribute("langues", langues);
		model.addAttribute("villes", villes);
		return "dashboard/modify-job";
	}

	@PostMapping("/saveModifEmploi")

	public String saveModifEmploi(@RequestParam int id, @RequestParam String titre, @RequestParam String nomEntreprise,
			@RequestParam String website, @RequestParam String emailEntreprise, @RequestParam String type,
			@RequestParam Langue langue, @RequestParam Ville ville, @RequestParam Catégorie catégorie,
			@RequestParam String salaire_min, @RequestParam String salaire_max, @RequestParam int experience,
			@RequestParam Date deadline, @RequestParam String keywordsInput, @RequestParam String exigences,
			@RequestParam String description) {
		Emploi emploi = emploiRepo.getById(id);
		Employeur employeur = employeurRepo.findById(1).orElse(null);
		emploi.setEmployeur(employeur);
		emploi.setTitre(titre);
		emploi.setNomEntreprise(nomEntreprise);
		emploi.setWebsite(website);
		emploi.setEmailEntreprise(emailEntreprise);
		emploi.setType(type);
		emploi.setSalaire_min(Double.parseDouble(salaire_min));
		emploi.setSalaire_max(Double.parseDouble(salaire_max));
		emploi.setExperience(experience);
		emploi.setDeadline(deadline);
		emploi.setExigences(exigences);
		emploi.setDescription(description);
		
		emploi.setLangue(langue);
		emploi.setVille(ville);
		emploi.setCatégorie(catégorie);
		
		String[] keywords = keywordsInput.split(",");
		for (String keywordStr : keywords) {
			Keyword keyword = new Keyword(keywordStr.trim());
			keyword.setEmploi(emploi);
			keywordRepo.save(keyword);
		}
		emploiRepo.save(emploi);

		return "redirect:/gererEmplois";
	}
	
	@GetMapping("/deleteJob")
	public String deleteJob(@RequestParam int id) {
		Emploi emp = emploiRepo.getById(id);
		emploiRepo.delete(emp);
		return "redirect:/gererEmplois";
	}

}
